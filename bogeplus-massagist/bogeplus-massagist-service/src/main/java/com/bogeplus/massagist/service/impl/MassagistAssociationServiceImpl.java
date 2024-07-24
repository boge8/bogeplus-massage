package com.bogeplus.massagist.service.impl;

import cn.hutool.core.util.IdUtil;
import com.bogeplus.common.constant.massagist.AssignmentConstant;
import com.bogeplus.common.util.Result;

import com.bogeplus.massagist.dto.AssignmentDTO;
import com.bogeplus.massagist.dto.CancelAssignmentDTO;
import com.bogeplus.massagist.mapper.MassagistInfoItemMapper;
import com.bogeplus.massagist.service.MassagistAssociationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author bin
 * @Date 2021/10/8 15:00
 * @Description 实现技师与项目关系的分配和取消分配
 */
@Service
@Slf4j
public class MassagistAssociationServiceImpl implements MassagistAssociationService {

    @Autowired
    private MassagistInfoItemMapper massagistInfoItemMapper;

    /**
     * 查询列表，查询技师或项目的分配情况
     *
     * @param type   传入对象类型 1:技师 2:项目 ps：传入对象则查询的是技师，反之亦然
     * @param status 查询类型 1:已分配 2:未分配
     * @param objId  对象id（项目或技师）
     * @return
     */
    @Override
    public Result getList(int type, long objId, int status) {
        List objList = new ArrayList(); //返回的对象列表,初始化
        if (type == AssignmentConstant.MASSAGIST) {    //传入对象为技师，查询项目列表
            if (status == AssignmentConstant.ASSIGNED) { //查询已分配项目列表
                objList = massagistInfoItemMapper.getAssignedItems(objId);
            } else if (status == AssignmentConstant.UNASSIGNED) {
                objList = massagistInfoItemMapper.getUnassignedItems(objId);
            }
        } else if (type == AssignmentConstant.ITEM) {  //传入对象为项目，查询技师列表
            if (status == AssignmentConstant.ASSIGNED) { //查询已分配项目列表
                objList = massagistInfoItemMapper.getAssignedMassagists(objId);
            } else if (status == AssignmentConstant.UNASSIGNED) {  //查询未分配项目列表
                objList = massagistInfoItemMapper.getUnassignedMassagists(objId);
            }
        }
        return Result.success(objList); //封装响应体
    }

    /**
     * @param operation 操作类型 1:分配 2:取消分配
     *                  操作分配关系
     * @param type      传入对象类型 1:技师 2:项目 ps：传入对象则操作的是技师，反之亦然
     * @param objId     对象id
     * @param objIdList 操作对象列表
     * @return
     */
    @Override
    public Result ChangeAssignment(int operation, int type, Long objId, List<Long> objIdList) {
        if (operation == AssignmentConstant.ASSIGN) {
            List<AssignmentDTO> dtos = new ArrayList<>();
            objIdList.stream().forEach(id ->
                    dtos.add(new AssignmentDTO(
                            IdUtil.getSnowflake(1, 1).nextId(),objId,id)));
            massagistInfoItemMapper.doAssign(type,dtos);
        } else if (operation == AssignmentConstant.UNASSIGN) {
            List<CancelAssignmentDTO> dtos = new ArrayList<>();
            objIdList.stream().forEach(id ->
                    dtos.add(new CancelAssignmentDTO(objId,id)));
            massagistInfoItemMapper.doUnassign(type,dtos);
        }
        return Result.success();
    }

}
