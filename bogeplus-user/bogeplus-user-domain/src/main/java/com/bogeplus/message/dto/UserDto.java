package com.bogeplus.message.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户数据传递信息")
@Data
public class UserDto {
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
}
