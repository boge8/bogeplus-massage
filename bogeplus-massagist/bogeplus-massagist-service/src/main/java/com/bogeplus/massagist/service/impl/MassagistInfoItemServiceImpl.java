package com.bogeplus.massagist.service.impl;

import cn.hutool.core.util.IdUtil;
import com.bogeplus.common.constant.massagist.AssignmentConstant;
import com.bogeplus.common.util.Result;

import com.bogeplus.massagist.controller.requestBody.OperationRequest;
import com.bogeplus.massagist.dto.AssignmentDTO;
import com.bogeplus.massagist.dto.CancelAssignmentDTO;
import com.bogeplus.massagist.mapper.MassagistInfoItemMapper;
import com.bogeplus.massagist.service.MassagistInfoItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @Author bin
 * @Date 2021/10/8 15:00
 * @Description 实现技师与项目关系的分配和取消分配
 */
@Service
@Slf4j
public class MassagistInfoItemServiceImpl implements MassagistInfoItemService {

    @Autowired
    private MassagistInfoItemMapper massagistInfoItemMapper;

    /**
     * 查询列表
     * @param type 传入对象类型 1:技师 2:项目 ps：传入对象则查询的是技师，反之亦然
     * @param status 查询类型 1:已分配 2:未分配
     * @param objId 对象id（项目或技师）
     * @return
     */
    @Override
    public Result getList(int type, int status, long objId) {
        List objList;                        //返回的对象列表,初始化
        if (type == AssignmentConstant.MASSAGIST) {    //传入对象为技师，查询项目列表
            if (status == AssignmentConstant.ASSIGNED) { //查询已分配项目列表
                objList = massagistInfoItemMapper.getAssignedItems(objId);
            } else if (status == AssignmentConstant.UNASSIGNED) {
                objList = massagistInfoItemMapper.getUnassignedItems(objId);
            } else {
                return Result.faild("传入status类型错误", 500);
            }
        } else if (type == AssignmentConstant.ITEM) {  //传入对象为项目，查询技师列表
            if (status == AssignmentConstant.ASSIGNED) { //查询已分配项目列表
                objList = massagistInfoItemMapper.getAssignedMassagists(objId);
            } else if (status == AssignmentConstant.UNASSIGNED) {  //查询未分配项目列表
                objList = massagistInfoItemMapper.getUnassignedMassagists(objId);
            } else {
                return Result.faild("传入status类型错误", 500);
            }
        } else {
            return Result.faild("传入type类型错误", 500);
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
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            int operation = request.getOperation();     //操作类型 1:分配 2:取消分配
            int type = request.getType();               //传入对象类型 1:技师 2:项目 ps：传入对象则查询的是技师，反之亦然
            long objId = request.getObjId();            //对象id
            List<Long> objIdList = request.getObjIdList();  //操作对象id列表

            if (operation == AssignmentConstant.ASSIGN) {
                List<AssignmentDTO> dtos = objIdList.stream()
                        .map(id -> new AssignmentDTO(IdUtil.getSnowflake(1, 1).nextId(), objId, id))
                        .collect(Collectors.toList());

                if (type == AssignmentConstant.MASSAGIST) {
                    if (isExisted(type,dtos)) {
                        return Result.faild("请刷新数据", 500);
                    }
                    massagistInfoItemMapper.assignItems(dtos);
                } else if (type == AssignmentConstant.ITEM) {
                    if (isExisted(type,dtos)) {
                        return Result.faild("请刷新数据", 500);
                    }
                    massagistInfoItemMapper.assignMassagists(dtos);
                } else {
                    return Result.faild("传入type类型错误", 500);
                }
            } else if (operation == AssignmentConstant.UNASSIGN) {
                List<CancelAssignmentDTO> dtos = objIdList.stream()
                        .map(id -> new CancelAssignmentDTO(objId, id))
                        .collect(Collectors.toList());

                if (type == AssignmentConstant.MASSAGIST) {
                    if (isDeleted(type,dtos)) {
                        return Result.faild("请刷新数据", 500);
                    }
                    massagistInfoItemMapper.unassignItems(dtos);
                } else if (type == AssignmentConstant.ITEM) {
                    if (isDeleted(type,dtos)) {
                        return Result.faild("请刷新数据", 500);
                    }
                    massagistInfoItemMapper.unassignMassagists(dtos);
                } else {
                    return Result.faild("传入type类型错误", 500);
                }
            } else {
                return Result.faild("传入operation类型错误", 500);
            }

            return Result.success();
        } finally {
            lock.unlock();
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
            return massagistInfoItemMapper.checkItems(dtos).size() == 0;
        } else {
            return massagistInfoItemMapper.checkMassagists(dtos).size() == 0;
        }
    }
}
