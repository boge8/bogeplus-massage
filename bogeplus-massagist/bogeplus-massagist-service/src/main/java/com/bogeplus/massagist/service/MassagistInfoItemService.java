package com.bogeplus.massagist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.controller.requestBody.OperationRequest;
import com.bogeplus.massagist.entity.MassagistInfoItem;

/**
 * @Author bin
 * @Date 2021/10/8 15:00
 * @Description 实现技师与项目关系的分配和取消分配
 */
public interface MassagistInfoItemService extends IService<MassagistInfoItem> {
    //查询该项目已被分配的技师
    Result getList(int type, int status, long objId);

    //取消技师与项目分配关系
    Result changeAssignment(OperationRequest request);

    //建立分配关系
    Result addAssignment(OperationRequest request);

    //删除分配关系
    Result cancelAssignment(OperationRequest request);
}
