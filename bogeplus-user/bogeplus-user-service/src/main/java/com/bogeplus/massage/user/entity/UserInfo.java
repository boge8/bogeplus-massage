package com.bogeplus.massage.user.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 上门按摩用户信息表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-16
 */
@TableName("massage_user_info")
@ApiModel(value = "用户基本信息", description = "上门按摩用户信息表")
@Data
public class UserInfo {

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "用户昵称")
    private String userNickname;

    @ApiModelProperty(value = "头像")
    private String headImg;

    @ApiModelProperty(value = "用户账号")
    private String account;

    @ApiModelProperty(value = "用户性别，男：0，女：1,未知：2")
    private Integer gender;

    @ApiModelProperty(value = "实名认证状态,未认证：0，已认证：1")
    private Boolean realNameVerified;

    @ApiModelProperty(value = "注册时间")
    private Date registTime;

    @TableField(fill = FieldFill.INSERT,value="create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE,value="update_time")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    private Long createUser;

    @ApiModelProperty(value = "最后更新人")
    private Long updateUser;

    @TableLogic
    @ApiModelProperty(value = "逻辑删除标志 0：未删除，1：已删除")
    private Boolean isDeleted;
}

