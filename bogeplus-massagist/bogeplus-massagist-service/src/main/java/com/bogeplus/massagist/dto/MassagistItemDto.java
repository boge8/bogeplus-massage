package com.bogeplus.massagist.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@ApiModel(value = "项目新增实体类", description = "按摩项目表")
@NoArgsConstructor
public class MassagistItemDto {


    @ApiModelProperty("产品名称")
    @NotNull(message = "产品名称不存在")
    private String name;

    @ApiModelProperty("原价")
    @NotNull(message = "价格未编写")
    @DecimalMin(value = "1", inclusive = false, message = "原价必须大于0")
    private BigDecimal originalPrice;

    @ApiModelProperty("现价")
    @NotNull(message = "价格未编写")
    @DecimalMin(value = "1", inclusive = false, message = "现价必须大于0")
    private BigDecimal currentPrice;

    @ApiModelProperty("时长")
    @NotNull(message = "时间未编写")
    private Integer duration;

    @ApiModelProperty("产品图片URL")
    @NotNull(message = "图片未上传")
    private String image;

    @ApiModelProperty("项目详情图片URL")
    @NotNull(message = "图片未上传")
    private String description;

    @ApiModelProperty("适用性别，不限：0，仅限女生：1，仅限男生：2")
    @NotNull(message = "未选择适用性别")
    private Integer applicableGender;

    @ApiModelProperty("销量")
    @NotNull(message = "销量未上传")
    private Integer salesVolume;

}
