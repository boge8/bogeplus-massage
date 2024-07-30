package com.bogeplus.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Auther: xu
 * @Date: 2024/7/30 - 07 - 30 - 下午6:36
 * @Description: com.bogeplus.order.dto
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MassagistOrderExtraDTO {

    //主键:关联对应订单信息表的id
    @NonNull
    private Long id;

    //订单的技师到达拍照url(一张)
    private String destinationUrl;

    //位置(地址名称)
    private String location;

    //到达时间
    private LocalDateTime arrivalTime;

    //备注
    private String note;
}
