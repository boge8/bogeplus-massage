package com.bogeplus.order.dto;

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
@ApiModel(value = "技师新增DTO", description = "技师表")
public class MassagistInfoInsertDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("技师姓名")
    private String name;

    @ApiModelProperty(value = "技师性别",notes = "0：女，1：男")
    private Boolean gender;

    @ApiModelProperty("技师头像")
    private String profilePicture;

    @ApiModelProperty("免费里程")
    private Integer freeMile;

    @ApiModelProperty("技师简介")
    private String bio;

    @ApiModelProperty("经纬度")
    private String Coordinates;

    @ApiModelProperty("接单地址")
    private String receiveAddress;

    @ApiModelProperty("技师所在城市")
    private String location;
}