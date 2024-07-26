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
 * 危险等级表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-26
 */
@Getter
@Setter
@TableName("massage_user_danger_level")
@ApiModel(value = "UserDangerLevel对象", description = "危险等级表")
public class UserDangerLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键:危险等级id")
    private Long id;

    @ApiModelProperty("明确定义(危险的具体情况)")
    private String clearDefinition;

    @ApiModelProperty("等级(例如:1、2、3)")
    private String levelName;

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
