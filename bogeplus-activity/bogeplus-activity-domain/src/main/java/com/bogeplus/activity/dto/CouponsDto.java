package com.bogeplus.activity.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ApiModel(value = "优惠券实体类", description = "用于优惠券中使用")
@NoArgsConstructor
public class CouponsDto {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("满减总价")
    private BigDecimal reductionTotalPrice;

    @ApiModelProperty("满减金额")
    private BigDecimal reductionPrice;

}
