package com.bogeplus.activity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 优惠券基础信息表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
@TableName("massage_coupons_base_info")
@ApiModel(value = "CouponsBaseInfo对象", description = "优惠券基础信息表")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CouponsBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("优惠券ID")
    private Long id;

    @ApiModelProperty("优惠券标识,用于作为分发的代码")
    private String code;

    @ApiModelProperty("满减总价")
    private BigDecimal reductionTotalPrice;

    @ApiModelProperty("过期时间,指的是用户领取后,开始计算过期时间,这里给一个指定的天数")
    private Integer expiryDate;

    @ApiModelProperty("优惠券数量")
    private Integer number;

    @ApiModelProperty("满减金额")
    private BigDecimal reductionPrice;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建者")
    private String createUser;

    @ApiModelProperty("更新者")
    private String updateUser;

    @ApiModelProperty("逻辑删除 0未删除：1已删除")
    private Boolean isDeleted;
}
