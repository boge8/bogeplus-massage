package com.bogeplus.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 支付流水表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_order_pay_transaction")
@ApiModel(value = "OrderPayTransaction对象", description = "支付流水表")
public class OrderPayTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("流水号")
    private Long id;

    @ApiModelProperty("订单编号")
    private String orderNum;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("技师ID")
    private Long massagistId;

    @ApiModelProperty("支付方式")
    private String payType;

    @ApiModelProperty("实支付金额")
    private BigDecimal amount;

    @ApiModelProperty("支付状态：1-成功，2-失败，3-待支付，4-退款")
    private Byte status;

    @ApiModelProperty("支付时间")
    private LocalDateTime payTime;

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

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMassagistId() {
        return massagistId;
    }

    public void setMassagistId(Long massagistId) {
        this.massagistId = massagistId;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
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
        return "OrderPayTransaction{" +
            "id = " + id +
            ", orderNum = " + orderNum +
            ", userId = " + userId +
            ", massagistId = " + massagistId +
            ", payType = " + payType +
            ", amount = " + amount +
            ", status = " + status +
            ", payTime = " + payTime +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", createUser = " + createUser +
            ", updateUser = " + updateUser +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
