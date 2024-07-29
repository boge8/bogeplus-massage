package com.bogeplus.activity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 优惠券和订单的关系表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
@TableName("massage_coupons_order_relation")
@Data
@ApiModel(value = "CouponsOrderRelation对象", description = "优惠券和订单的关系表")
public class CouponsOrderRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关系ID")
    private Long id;

    @ApiModelProperty("优惠券ID")
    private Long couponId;

    @ApiModelProperty("订单ID")
    private Long orderId;

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
