package com.bogeplus.order.entity;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.bogeplus.common.util.UserUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 订单项目中间表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Data
@TableName("massage_order_item")
@ApiModel(value = "OrderItem对象", description = "订单项目中间表")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("订单ID")
    private Long orderId;

    @ApiModelProperty("项目ID")
    private Long itemId;

    @ApiModelProperty("项目数量")
    private Integer quantity;

    @ApiModelProperty("状态，0表示常规订单，1表示加钟订单")
    private Byte status;

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

    public static OrderItem nil(){
        OrderItem orderItem = new OrderItem();
        orderItem.id = IdUtil.getSnowflake(1, 1).nextId();
        orderItem.createTime = LocalDateTime.now();
        orderItem.createUser = UserUtil.getAccount();
        orderItem.updateTime = LocalDateTime.now();
        orderItem.updateUser = UserUtil.getAccount();
        return orderItem;
    }
}
