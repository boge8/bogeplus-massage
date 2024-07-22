package com.bogeplus.activity.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 优惠券表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_coupons_info")
@ApiModel(value = "优惠券基本信息", description = "优惠券表")
@Data
public class CouponsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("优惠券代码")
    private String code;

    @ApiModelProperty("满减总价")
    private BigDecimal reductionTotalPrice;

    @ApiModelProperty("满减金额")
    private BigDecimal reductionPrice;

    @ApiModelProperty("过期时间")
    private LocalDateTime expiryDate;

    @ApiModelProperty("优惠券状态，未使用：0，已使用：1，已过期：2")
    private Integer status;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建者")
    private String createUser;

    @ApiModelProperty("更新者")
    private String updateUser;

    @TableLogic
    @ApiModelProperty("逻辑删除 0未删除：1已删除")
    private Boolean isDeleted;
}
