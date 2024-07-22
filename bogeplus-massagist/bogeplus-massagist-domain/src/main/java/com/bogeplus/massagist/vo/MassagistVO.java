package com.bogeplus.massagist.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MassagistVO {
    //id
    private Long id;
    //姓名
    private String name;
    //头像
    private String headImg;
}
