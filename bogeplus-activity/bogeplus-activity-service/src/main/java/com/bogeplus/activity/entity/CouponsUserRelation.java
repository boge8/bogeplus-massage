package com.bogeplus.activity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 优惠券和用户的关系表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-25
 */
@TableName("massage_coupons_user_relation")
@Data
@ApiModel(value = "CouponsUserRelation对象", description = "优惠券和用户的关系表")
public class CouponsUserRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关系ID")
    private Long id;

    @ApiModelProperty("优惠券ID")
    private Long couponId;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("领取时间")
    private LocalDateTime receiveTime;

    @ApiModelProperty("过期时间,这里指的是具体哪一天过期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private LocalDateTime expiryDate;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private LocalDateTime createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private LocalDateTime updateTime;

    @ApiModelProperty("创建者")
    private String createUser;

    @ApiModelProperty("更新者")
    private String updateUser;

    @ApiModelProperty("逻辑删除 0未删除：1已删除")
    private Boolean isDeleted;
}
