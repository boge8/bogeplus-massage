package com.bogeplus.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 技师物料表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_massagist_massagist_material")
@ApiModel(value = "MassagistMassagistMaterial对象", description = "技师物料表")
public class MassagistMassagistMaterial implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("技师id")
    private Long massagistId;

    @ApiModelProperty("物料id")
    private Long materialId;

    @ApiModelProperty("物料数量")
    private Byte materialQuantity;

    @ApiModelProperty("物料名称")
    private String materialName;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMassagistId() {
        return massagistId;
    }

    public void setMassagistId(Long massagistId) {
        this.massagistId = massagistId;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Byte getMaterialQuantity() {
        return materialQuantity;
    }

    public void setMaterialQuantity(Byte materialQuantity) {
        this.materialQuantity = materialQuantity;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "MassagistMassagistMaterial{" +
            "id = " + id +
            ", massagistId = " + massagistId +
            ", materialId = " + materialId +
            ", materialQuantity = " + materialQuantity +
            ", materialName = " + materialName +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", createUser = " + createUser +
            ", updateUser = " + updateUser +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
