package com.bogeplus.order.dto;

import lombok.Data;

import java.util.List;
@Data
public class OperationDTO {
    //操作类型
    private int operation;
    //传入对象类型
    private int type;
    //传入对象id
    private long objId;
    //操作对象id集合
    private List<Long> objIdList;
}
