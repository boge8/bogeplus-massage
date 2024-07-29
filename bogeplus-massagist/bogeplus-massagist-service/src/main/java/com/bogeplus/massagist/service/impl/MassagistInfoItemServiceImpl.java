package com.bogeplus.massagist.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.common.constant.massagist.AssignmentConstant;
import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.controller.requestBody.OperationRequest;

import com.bogeplus.massagist.entity.MassagistInfoItem;
import com.bogeplus.massagist.mapper.MassagistInfoItemMapper;
import com.bogeplus.massagist.service.MassagistInfoItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author bin
 * @Date 2024/07/29 15:00
 * @Description 实现技师与项目关系的分配和取消分配
 */
@Service
@Slf4j
public class MassagistInfoItemServiceImpl extends ServiceImpl<MassagistInfoItemMapper, MassagistInfoItem> implements MassagistInfoItemService {

    @Autowired
    private MassagistInfoItemMapper massagistInfoItemMapper;

    /**
     * 查询该对象已被分配项目
     * @param type 对象类型 1-项目 2-技师
     * @param objId 对象id
     * @return 响应体
     */
    public Result<?> getAssignedList(Integer type, Long objId) {
        List<?> assignedList =
                type == AssignmentConstant.MASSAGIST
                ? massagistInfoItemMapper.getAssignedItems(objId)
                : massagistInfoItemMapper.getAssignedMassagists(objId);
        return Result.success(assignedList);
    }

    /**
     * 查询该对象未被分配项目
     * @param type 对象类型 1-项目 2-技师
     * @param objId 对象id
     * @return 响应体
     */
    public Result<?> getUnassignedList(Integer type,Long objId){
        List<?> unAssignedList =
                type == AssignmentConstant.MASSAGIST
                ? massagistInfoItemMapper.getUnassignedItems(objId)
                : massagistInfoItemMapper.getUnassignedMassagists(objId);
        return Result.success(unAssignedList);
    }

    /**
     * 新增分配关系
     * @param request 请求体
     * @return 响应体
     */
    public Result addAssignment(OperationRequest request) {
        Integer type = request.getType();
        Long objId = request.getObjId();
        List<Long> objIdList = request.getObjIdList();
        List<MassagistInfoItem> assignments;

        if (!getExisted(type,objId,objIdList).isEmpty()) {
            throw new BizException(ServiceCode.MASSAGIST_EXPIRED_DATA.getCode(),ServiceCode.MASSAGIST_EXPIRED_DATA.getMsg());
        }

        assignments = getAddition(objId,objIdList,type == AssignmentConstant.MASSAGIST);

        // 批量保存新增关系
        boolean flag = saveBatch(assignments);
        if (!flag) {
            log.error("新增分配关系失败");
            return Result.faild(ServiceCode.FAILED.getMsg(),ServiceCode.FAILED.getCode());
        }
        return Result.success();
    }

    /**
     * 删除分配关系
     * @param request 请求体
     * @return 响应体
     */
    public Result cancelAssignment(OperationRequest request){
        Integer type = request.getType();
        Long objId = request.getObjId();
        List<Long> objIdList = request.getObjIdList();
        boolean flag;

        if (getExisted(type,objId,objIdList).isEmpty()) {
            throw new BizException(ServiceCode.MASSAGIST_EXPIRED_DATA.getCode(),ServiceCode.MASSAGIST_EXPIRED_DATA.getMsg());
        }

        LambdaQueryWrapper<MassagistInfoItem> queryWrapper = new LambdaQueryWrapper<>();
        if (type == AssignmentConstant.MASSAGIST) {
            queryWrapper.eq(MassagistInfoItem::getMasseurId, objId)
                    .in(MassagistInfoItem::getItemId, objIdList);
        } else {
            queryWrapper.eq(MassagistInfoItem::getItemId, objId)
                    .in(MassagistInfoItem::getMasseurId, objIdList);
        }
        if (!remove(queryWrapper)){
            return Result.faild(ServiceCode.FAILED.getMsg(),ServiceCode.FAILED.getCode());
        }
        return Result.success();
    }

    /**
     * 获取新增项目集合
     * @param objId 对象id
     * @param objIdList 操作对象id集合
     * @param isMassagist 类型是否为技师
     * @return 新增项目集合
     */
    private List<MassagistInfoItem> getAddition(Long objId,List<Long> objIdList,boolean isMassagist){
        List<MassagistInfoItem> assignments = objIdList.stream().map(id -> {
            MassagistInfoItem relation = new MassagistInfoItem();
            relation.setMasseurId(isMassagist ? objId : id);
            relation.setItemId(isMassagist? id : objId);
            return relation;
        }).collect(Collectors.toList());
        return assignments;
    }

    /**
     * 获取已存在项目集合
     * @param type 类型 1-项目 2-技师
     * @param objId  对象id
     * @param objIdList 操作对象id集合
     * @return 已存在项目集合
     */
    private List<MassagistInfoItem> getExisted(Integer type, Long objId, List<Long> objIdList) {
        LambdaQueryWrapper<MassagistInfoItem> queryWrapper = new LambdaQueryWrapper<>();
        if (type == AssignmentConstant.MASSAGIST) {
            queryWrapper.eq(MassagistInfoItem::getMasseurId, objId)
                    .in(MassagistInfoItem::getItemId, objIdList);
        } else {
            queryWrapper.eq(MassagistInfoItem::getItemId, objId)
                    .in(MassagistInfoItem::getMasseurId, objIdList);
        }
        return list(queryWrapper);
    }

}
