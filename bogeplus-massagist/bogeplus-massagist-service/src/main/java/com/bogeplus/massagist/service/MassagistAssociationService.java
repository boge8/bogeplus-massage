package com.bogeplus.massagist.service;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.dto.AssignmentDTO;

import java.util.List;

/**
 * @Author bin
 * @Date 2021/10/8 15:00
 * @Description 实现技师与项目关系的分配和取消分配
 */
public interface MassagistAssociationService {
    //查询该项目已被分配的技师
    Result getList(int type, long objId, int status);

    //取消技师与项目分配关系
    Result ChangeAssignment(int operation, int type, Long objId, List<Long> objIdList);
}
