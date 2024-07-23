package com.bogeplus.activity.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponsDto {

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("满减总价")
    private BigDecimal reductionTotalPrice;

    @ApiModelProperty("满减金额")
    private BigDecimal reductionPrice;

    @ApiModelProperty("过期时间")
    private LocalDateTime expiryDate;

}
