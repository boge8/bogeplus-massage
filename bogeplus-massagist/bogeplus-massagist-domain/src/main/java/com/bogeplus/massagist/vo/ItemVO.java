package com.bogeplus.massagist.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVO {
    //id
    private Long id;
    //项目名称
    private String name;
    //项目图片
    private String img;
}
