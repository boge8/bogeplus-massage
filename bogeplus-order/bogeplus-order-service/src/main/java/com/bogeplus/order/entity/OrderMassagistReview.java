package com.bogeplus.order.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 技师对客户评价表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_order_massagist_review")
@ApiModel(value = "OrderMassagistReview对象", description = "技师对客户评价表")
public class OrderMassagistReview implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评价ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("订单编号")
    private Long orderId;

    @ApiModelProperty("劳累部位(使用数字来来定义；	0:腰部)")
    private Byte tiredBodyPart;

    @ApiModelProperty("受力(1:表示受力大；0:表示受力小)")
    private Byte forceLevel;

    @ApiModelProperty("地址类型(0：酒店，1：住宅）")
    private Byte addressType;

    @ApiModelProperty("客户性别(男:1；女:0)")
    private Byte customerGender;

    @ApiModelProperty("目测年龄")
    private Integer estimatedAge;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("评价时间")
    private LocalDateTime evaluationTime;

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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Byte getTiredBodyPart() {
        return tiredBodyPart;
    }

    public void setTiredBodyPart(Byte tiredBodyPart) {
        this.tiredBodyPart = tiredBodyPart;
    }

    public Byte getForceLevel() {
        return forceLevel;
    }

    public void setForceLevel(Byte forceLevel) {
        this.forceLevel = forceLevel;
    }

    public Byte getAddressType() {
        return addressType;
    }

    public void setAddressType(Byte addressType) {
        this.addressType = addressType;
    }

    public Byte getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(Byte customerGender) {
        this.customerGender = customerGender;
    }

    public Integer getEstimatedAge() {
        return estimatedAge;
    }

    public void setEstimatedAge(Integer estimatedAge) {
        this.estimatedAge = estimatedAge;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getEvaluationTime() {
        return evaluationTime;
    }

    public void setEvaluationTime(LocalDateTime evaluationTime) {
        this.evaluationTime = evaluationTime;
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
        return "OrderMassagistReview{" +
            "id = " + id +
            ", orderId = " + orderId +
            ", tiredBodyPart = " + tiredBodyPart +
            ", forceLevel = " + forceLevel +
            ", addressType = " + addressType +
            ", customerGender = " + customerGender +
            ", estimatedAge = " + estimatedAge +
            ", remarks = " + remarks +
            ", evaluationTime = " + evaluationTime +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", createUser = " + createUser +
            ", updateUser = " + updateUser +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
