package com.bogeplus.massage.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户与优惠券关系表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-26
 */
@Getter
@Setter
@TableName("massage_user_user_coupons")
@ApiModel(value = "UserUserCoupons对象", description = "用户与优惠券关系表")
public class UserUserCoupons implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("优惠券ID")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("优惠券id")
    private Long couponsId;

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
