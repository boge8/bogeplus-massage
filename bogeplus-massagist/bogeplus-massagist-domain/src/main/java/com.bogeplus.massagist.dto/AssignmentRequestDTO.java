package com.bogeplus.massagist.dto;

import lombok.Data;

import java.util.List;
@Data
public class AssignmentRequestDTO {
    private int operation;
    private int type;
    private long objId;
    private List<Long> objIdList;
}
