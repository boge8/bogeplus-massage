package com.bogeplus.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 项目详情图片表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_massagist_description_img")
@ApiModel(value = "MassagistDescriptionImg对象", description = "项目详情图片表")
public class MassagistDescriptionImg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("按摩项目id")
    private Long itemId;

    @ApiModelProperty("项目详情图片URL")
    private String imgUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "MassagistDescriptionImg{" +
            "id = " + id +
            ", itemId = " + itemId +
            ", imgUrl = " + imgUrl +
        "}";
    }
}
