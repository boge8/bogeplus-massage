package com.bogeplus.massagist.service;

import com.bogeplus.common.util.Result;

/**
 * @Author bin
 * @Date 2021/10/8 15:00
 * @Description 实现技师与项目关系的分配和取消分配
 */
public interface MassagistAssociationService {
    //查询该项目已被分配的技师
    Result getAssignedMassagistsByItemId(Long itemId);

    //查询该项目未被分配到的技师
    Result getUnassignedMassagistsByItemId(Long itemId);

    //查询该技师已被分配的项目
    Result getAssignedItemsByMassagistId(Long massagistId);

    //查询该技师未被分配到项目
    Result getUnassignedItemsByMassagistId(Long massagistId);

    //取消技师与项目分配关系
    Result cancelAssign(Long massagistId, Long itemId);

    //建立技师与项目分配关系
    Result assign(Long massagistId, Long itemId);
}
