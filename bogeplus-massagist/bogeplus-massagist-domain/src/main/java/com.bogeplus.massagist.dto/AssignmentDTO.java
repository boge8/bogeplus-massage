package com.bogeplus.massagist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignmentDTO {
    /* 关系id */
    private Long id;
    /* 对象id */
    private Long objId;
    /* 操作对象id集合 */
    private Long obj2Id;
}
