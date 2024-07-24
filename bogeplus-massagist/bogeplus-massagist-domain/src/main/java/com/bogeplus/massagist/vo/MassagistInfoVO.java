package com.bogeplus.massagist.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 技师表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("massage_massagist_info")
@ApiModel(value = "MassagistInfo对象", description = "技师表")
public class MassagistInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("技师姓名")
    private String name;

    @ApiModelProperty("技师头像")
    private String profilePicture;

    @ApiModelProperty("是否接种（未接种：0，已接种：1)")
    private Boolean vaccinated;

    @ApiModelProperty("技师评分")
    private BigDecimal rating;

    @ApiModelProperty("服务数量")
    private Integer completedOrders;

    @ApiModelProperty("免费里程")
    private Integer freeMile;

    @ApiModelProperty("点赞数量")
    private Integer likes;

    @ApiModelProperty("评论数量")
    private Integer comment;

    @ApiModelProperty("技师简介")
    private String bio;

    @ApiModelProperty("技师认证状态，未认证：0")
    private Boolean massagistVerified;

    @ApiModelProperty("实名认证状态，未认证：0，已认证：1")
    private Boolean realNameVerified;

    @ApiModelProperty("经纬度")
    private String longtitudeLatitude;

    @ApiModelProperty("接单地址")
    private String receiveAddress;

    @ApiModelProperty("技师所在城市")
    private String location;

    @ApiModelProperty("可预约时间段")
    private String availableTimeSlots;

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
