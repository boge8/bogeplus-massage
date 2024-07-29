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
 * 用户实名信息表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-26
 */
@Getter
@Setter
@TableName("massage_user_certification")
@ApiModel(value = "UserCertification对象", description = "用户实名信息表")
public class UserCertification implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("用户账号")
    private String phoneNumber;

    @ApiModelProperty("真实姓名")
    private String realName;

    @ApiModelProperty("身份证号码")
    private String code;

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
