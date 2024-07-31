package com.bogeplus.massagist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.controller.requestBody.OperationRequest;
import com.bogeplus.massagist.entity.MassagistInfoItem;
import com.bogeplus.massagist.vo.MassagistChoiceVO;

import java.util.List;

/**
 * @Author bin
 * @Date 2021/10/8 15:00
 * @Description 实现技师与项目关系的分配和取消分配
 */
public interface MassagistInfoItemService extends IService<MassagistInfoItem> {

    Result<?> getAssignedList(Integer type, Long objId);

    Result<?> getUnassignedList(Integer type, Long objId);

    List<MassagistChoiceVO> getMassagists(Long itemId, String cityCode);

    //建立分配关系
    Result addAssignment(OperationRequest request);

    //删除分配关系
    Result cancelAssignment(OperationRequest request);
}
