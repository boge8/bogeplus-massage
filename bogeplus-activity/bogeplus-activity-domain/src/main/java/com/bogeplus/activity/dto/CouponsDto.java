package com.bogeplus.activity.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@ApiModel(value = "优惠券实体类", description = "用于优惠券中使用")
@NoArgsConstructor
public class CouponsDto {

    @ApiModelProperty("用户id")
    @NotNull(message = "用户不存在")
    private Long userId;

    @ApiModelProperty("满减总价")
    @DecimalMin(value = "1", inclusive = false, message = "满减总价必须大于0")
    private BigDecimal reductionTotalPrice;

    @ApiModelProperty("满减金额")
    @DecimalMin(value = "1", inclusive = false, message = "满减金额必须大于0")
    private BigDecimal reductionPrice;

    @ApiModelProperty("优惠券数量")
    @Min(value = 1, message = "优惠券数量必须大于0")
    private Integer number;

}
