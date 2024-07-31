package com.bogeplus.massagist.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
@ApiModel(value = "技师信息VO", description = "技师表")
public class MassagistChoiceVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("技师姓名")
    private String name;

    @ApiModelProperty("技师头像")
    private String profilePicture;

    @ApiModelProperty("是否接种（未接种：0，已接种：1)")
    private Boolean vaccinated;

    @ApiModelProperty("服务数量")
    private Integer completedOrders;

    @ApiModelProperty("点赞数量")
    private Integer likes;

    @ApiModelProperty("评论数量")
    private Integer comment;

    @ApiModelProperty("可预约时间段")
    private String availableTimeSlots;

    @ApiModelProperty("技师距离")
    private double distance;
}
