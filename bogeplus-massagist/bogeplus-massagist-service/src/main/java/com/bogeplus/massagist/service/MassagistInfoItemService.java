package com.bogeplus.massagist.service;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.dto.GetListDTO;
import com.bogeplus.massagist.dto.OperationDTO;

import java.util.List;

/**
 * @Author bin
 * @Date 2021/10/8 15:00
 * @Description 实现技师与项目关系的分配和取消分配
 */
public interface MassagistInfoItemService {
    //查询该项目已被分配的技师
    Result getList(GetListDTO dto);

    //取消技师与项目分配关系
    Result ChangeAssignment(OperationDTO dto);
}
