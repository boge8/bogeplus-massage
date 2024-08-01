package com.bogeplus.massage.user.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 地址表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-26
 */
@Getter
@Setter
@TableName("massage_user_addresses")
@ApiModel(value = "UserAddresses对象", description = "地址表")
public class UserAddresses implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("联系人姓名")
    private String contactName;

    @ApiModelProperty("联系人电话")
    private String contactPhone;

    @ApiModelProperty("经纬度")
    private String longitudeLatitude;

    @ApiModelProperty("城市编码")
    private String cityCode;

    @ApiModelProperty("服务地址")
    private String address;

    @ApiModelProperty("地址扩展信息")
    private String addressExtra;

    @ApiModelProperty("是否为默认地址")
    private Boolean isDefault;

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
