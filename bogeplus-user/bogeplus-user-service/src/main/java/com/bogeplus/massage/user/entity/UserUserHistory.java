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
 * 按摩客户往期概况表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-26
 */
@Getter
@Setter
@TableName("massage_user_user_history")
@ApiModel(value = "UserUserHistory对象", description = "按摩客户往期概况表")
public class UserUserHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("客户概况ID")
    private Long id;

    @ApiModelProperty("客户ID")
    private Long userId;

    @ApiModelProperty("危险等级")
    private Long dangerLevel;

    @ApiModelProperty("行为备注")
    private String remarks;

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
