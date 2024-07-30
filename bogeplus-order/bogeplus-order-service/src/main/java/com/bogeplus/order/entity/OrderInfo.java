package com.bogeplus.order.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_order_info")
@ApiModel(value = "OrderInfo对象", description = "订单信息表")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("订单ID")
    private Long id;

    @ApiModelProperty("订单编号")
    private String orderNum;

    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("技师ID")
    private Long massagistId;

    @ApiModelProperty("优惠卷ID")
    private Long couponId;

    @ApiModelProperty("订单状态码：1-待支付，2-支付超时，3-待接单，4-已接单，5-技师出发，6-技师到达，7-开始服务，8-服务完成，9-用户评价，10-售后中，11-售后结束，12-订单已关闭，13-订单已取消")
    private Byte status;

    @ApiModelProperty("是否已评价，1表示已评价，0表示未评价")
    private Boolean evaluated;

    @ApiModelProperty("订单是否已取消，1表示订单取消，0表示未取消")
    private Boolean cancel;

    @ApiModelProperty("出行方式")
    private String travelMode;

    @ApiModelProperty("出行距离 单位km")
    private BigDecimal travelDistance;

    @ApiModelProperty("出行费用 单位元")
    private BigDecimal travelCost;

    @ApiModelProperty("服务时间")
    private LocalDateTime serviceTime;

    @ApiModelProperty("价格汇总，包含服务费、出行费用等")
    private BigDecimal totalPrice;

    @ApiModelProperty("技师预计收入")
    private BigDecimal expectedIncome;

    @ApiModelProperty("支付方式")
    private String payType;

    @ApiModelProperty("备注信息")
    private String remark;

    @ApiModelProperty("交易ID")
    private Long transactionId;

    @ApiModelProperty("外部交易号")
    private String outTradeNo;

    @ApiModelProperty("是否退款，1表示已退款，0表示未退款")
    private Boolean isRefund;

    @ApiModelProperty("退款金额，单位元")
    private BigDecimal refund;

    @ApiModelProperty("支付时间")
    private LocalDateTime payTime;

    @ApiModelProperty("接单时间")
    private LocalDateTime takeTime;

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

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Boolean getEvaluated() {
        return evaluated;
    }

    public void setEvaluated(Boolean evaluated) {
        this.evaluated = evaluated;
    }

    public Boolean getCancel() {
        return cancel;
    }

    public void setCancel(Boolean cancel) {
        this.cancel = cancel;
    }

    public String getTravelMode() {
        return travelMode;
    }

    public void setTravelMode(String travelMode) {
        this.travelMode = travelMode;
    }

    public BigDecimal getTravelDistance() {
        return travelDistance;
    }

    public void setTravelDistance(BigDecimal travelDistance) {
        this.travelDistance = travelDistance;
    }

    public BigDecimal getTravelCost() {
        return travelCost;
    }

    public void setTravelCost(BigDecimal travelCost) {
        this.travelCost = travelCost;
    }

    public LocalDateTime getServiceTime() {
        return serviceTime;
    }

    public void setServiceTime(LocalDateTime serviceTime) {
        this.serviceTime = serviceTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getExpectedIncome() {
        return expectedIncome;
    }

    public void setExpectedIncome(BigDecimal expectedIncome) {
        this.expectedIncome = expectedIncome;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public Boolean getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(Boolean isRefund) {
        this.isRefund = isRefund;
    }

    public BigDecimal getRefund() {
        return refund;
    }

    public void setRefund(BigDecimal refund) {
        this.refund = refund;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime = payTime;
    }

    public LocalDateTime getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(LocalDateTime takeTime) {
        this.takeTime = takeTime;
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
        return "OrderInfo{" +
            "id = " + id +
            ", orderNum = " + orderNum +
            ", userId = " + userId +
            ", massagistId = " + massagistId +
            ", couponId = " + couponId +
            ", status = " + status +
            ", evaluated = " + evaluated +
            ", cancel = " + cancel +
            ", travelMode = " + travelMode +
            ", travelDistance = " + travelDistance +
            ", travelCost = " + travelCost +
            ", serviceTime = " + serviceTime +
            ", totalPrice = " + totalPrice +
            ", expectedIncome = " + expectedIncome +
            ", payType = " + payType +
            ", remark = " + remark +
            ", transactionId = " + transactionId +
            ", outTradeNo = " + outTradeNo +
            ", isRefund = " + isRefund +
            ", refund = " + refund +
            ", payTime = " + payTime +
            ", takeTime = " + takeTime +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", createUser = " + createUser +
            ", updateUser = " + updateUser +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
