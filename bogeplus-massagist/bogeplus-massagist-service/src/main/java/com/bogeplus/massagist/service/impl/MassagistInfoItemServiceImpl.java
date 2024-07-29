package com.bogeplus.massagist.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.common.constant.massagist.AssignmentConstant;
import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.util.Result;

import com.bogeplus.common.util.UserUtil;
import com.bogeplus.massagist.controller.requestBody.OperationRequest;
import com.bogeplus.massagist.dto.AssignmentDTO;
import com.bogeplus.massagist.dto.CancelAssignmentDTO;
import com.bogeplus.massagist.entity.MassagistInfoItem;
import com.bogeplus.massagist.mapper.MassagistInfoItemMapper;
import com.bogeplus.massagist.service.MassagistInfoItemService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @Author bin
 * @Date 2021/10/8 15:00
 * @Description 实现技师与项目关系的分配和取消分配
 */
@Service
@Slf4j
public class MassagistInfoItemServiceImpl extends ServiceImpl<MassagistInfoItemMapper, MassagistInfoItem> implements MassagistInfoItemService {

    @Autowired
    private MassagistInfoItemMapper massagistInfoItemMapper;

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 查询列表
     * @param type 传入对象类型 1:技师 2:项目 ps：传入对象则查询的是技师，反之亦然
     * @param status 查询类型 1:已分配 2:未分配
     * @param objId 对象id（项目或技师）
     * @return
     */
    @Override
    public Result getList(int type, int status, long objId) {
        List<?> objList = new ArrayList();                        //返回的对象列表,初始化
        if (type == AssignmentConstant.MASSAGIST) {    //传入对象为技师，查询项目列表
            if (status == AssignmentConstant.ASSIGNED) { //查询已分配项目列表
                objList = massagistInfoItemMapper.getAssignedItems(objId);
            } else {
                objList = massagistInfoItemMapper.getUnassignedItems(objId);
            }
        } else {  //传入对象为项目，查询技师列表
            if (status == AssignmentConstant.ASSIGNED) { //查询已分配项目列表
                objList = massagistInfoItemMapper.getAssignedMassagists(objId);
            } else {  //查询未分配项目列表
                objList = massagistInfoItemMapper.getUnassignedMassagists(objId);
            }
        }
        return Result.success(objList); //封装响应体
    }


    /**
     * 操作分配关系
     *
     * @param request 请求体
     * @return
     */
    @Transactional
    @Override
    public Result changeAssignment(OperationRequest request) {
        int operation = request.getOperation();     //操作类型 1:分配 2:取消分配
        int type = request.getType();               //传入对象类型 1:技师 2:项目 ps：传入对象则查询的是技师，反之亦然
        long objId = request.getObjId();            //对象id
        List<Long> objIdList = request.getObjIdList();  //操作对象id列表
        RLock lock = redissonClient.getLock(String.valueOf(request.getObjId()));
        try {
            if (lock.tryLock(10, 30, TimeUnit.SECONDS)) {
                if (operation == AssignmentConstant.ASSIGN) {
                    List<AssignmentDTO> dtos = objIdList.stream()
                            .map(id -> new AssignmentDTO(IdUtil.getSnowflake(1, 1).nextId(), objId, id))
                            .collect(Collectors.toList());

                    if (type == AssignmentConstant.MASSAGIST) {
                        if (isExisted(type,dtos)) return Result.faild(ServiceCode.MASSAGIST_EXPIRED_DATA);
                        massagistInfoItemMapper.assignItems(dtos);
                    } else {
                        if (isExisted(type,dtos)) return Result.faild(ServiceCode.MASSAGIST_EXPIRED_DATA);
                        massagistInfoItemMapper.assignMassagists(dtos);
                    }
                } else {
                    List<CancelAssignmentDTO> dtos = objIdList.stream()
                            .map(id -> new CancelAssignmentDTO(objId, id))
                            .collect(Collectors.toList());

                    if (type == AssignmentConstant.MASSAGIST) {
                        if (isDeleted(type,dtos)) return Result.faild(ServiceCode.MASSAGIST_EXPIRED_DATA);
                        massagistInfoItemMapper.unassignItems(dtos);
                    } else {
                        if (isDeleted(type,dtos)) return Result.faild(ServiceCode.MASSAGIST_EXPIRED_DATA);
                        massagistInfoItemMapper.unassignMassagists(dtos);
                    }
                }
                return Result.success();
            } else {
                return Result.faild("获取锁失败", 500);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.faild("操作失败，发生异常", 500);
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    private boolean isExisted(int type, List<AssignmentDTO> dtos) {
        if (type == AssignmentConstant.MASSAGIST) {
            return !(massagistInfoItemMapper.checkItems(dtos).size() == 0);
        } else {
            return !(massagistInfoItemMapper.checkMassagists(dtos).size() == 0);
        }
    }
    
    private boolean isDeleted(int type, List<CancelAssignmentDTO> dtos) {
        if (type == AssignmentConstant.MASSAGIST) {
            return massagistInfoItemMapper.checkItemsCancel(dtos).size() == 0;
        } else {
            return massagistInfoItemMapper.checkMassagistsCancel(dtos).size() == 0;
        }
    }

    public Result addAssignment(OperationRequest request) {
        int type = request.getType();
        Long objId = request.getObjId();
        List<Long> objIdList = request.getObjIdList();
        List<MassagistInfoItem> assignments;

        if (!getExisted(type,objId,objIdList).isEmpty()) {
            return Result.faild(ServiceCode.MASSAGIST_EXPIRED_DATA);
        }

        if (request.getType() == AssignmentConstant.MASSAGIST) {
            assignments = objIdList.stream().map(id -> {
                MassagistInfoItem relation = new MassagistInfoItem();
                relation.setId(IdUtil.getSnowflake(1, 1).nextId());
                relation.setMasseurId(objId);
                relation.setItemId(id);
                relation.setCreateTime(LocalDateTime.now());
                relation.setCreateUser(UserUtil.getAccount());
                relation.setUpdateTime(LocalDateTime.now());
                relation.setUpdateUser(UserUtil.getAccount());
                return relation;
            }).collect(Collectors.toList());
        } else {
            assignments = objIdList.stream().map(id -> {
                MassagistInfoItem relation = new MassagistInfoItem();
                relation.setId(IdUtil.getSnowflake(1, 1).nextId());
                relation.setMasseurId(id);
                relation.setItemId(objId);
                relation.setCreateTime(LocalDateTime.now());
                relation.setCreateUser(UserUtil.getAccount());
                relation.setUpdateTime(LocalDateTime.now());
                relation.setUpdateUser(UserUtil.getAccount());
                return relation;
            }).collect(Collectors.toList());
        }
        // 批量保存新增关系
        boolean flag = saveBatch(assignments);
        if (!flag) {
            return Result.faild(ServiceCode.FAILED);
        }
        return Result.success();
    }

    public Result cancelAssignment(OperationRequest request){
        int type = request.getType();
        long objId = request.getObjId();
        List<Long> objIdList = request.getObjIdList();
        boolean flag;

        if (getExisted(type,objId,objIdList).isEmpty()) {
            return Result.faild(ServiceCode.MASSAGIST_EXPIRED_DATA);
        }

        if (type == AssignmentConstant.MASSAGIST) {
            flag = remove(new LambdaQueryWrapper<MassagistInfoItem>()
                    .eq(MassagistInfoItem::getMasseurId, objId)
                    .in(MassagistInfoItem::getItemId, objIdList));
        } else {
            flag = remove(new LambdaQueryWrapper<MassagistInfoItem>()
                    .eq(MassagistInfoItem::getItemId, objId)
                    .in(MassagistInfoItem::getMasseurId, objIdList));
        }

        if (flag == AssignmentConstant.FAIL){
            return Result.faild(ServiceCode.FAILED);
        }
        return Result.success();
    }

    private List<MassagistInfoItem> getExisted(int type,Long objId,List<Long> objIdList){
        if (type == AssignmentConstant.MASSAGIST) {
            return list(new LambdaQueryWrapper<MassagistInfoItem>()
                    .eq(MassagistInfoItem::getMasseurId, objId)
                    .in(MassagistInfoItem::getItemId, objIdList));
        } else {
            return list(new LambdaQueryWrapper<MassagistInfoItem>()
                    .eq(MassagistInfoItem::getItemId, objId)
                    .in(MassagistInfoItem::getMasseurId, objIdList));
        }
    }

}
