package com.bogeplus.massagist.dto;

import lombok.Data;

@Data
public class GetListDTO {
    //id类型
    private int type;
    //查询类型
    private int status;
    //对象id
    private long objId;
}
