package com.bogeplus.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 技师收益表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_pay_massagist_income")
@ApiModel(value = "PayMassagistIncome对象", description = "技师收益表")
public class PayMassagistIncome implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

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

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("收益金额")
    private BigDecimal harvestMoney;

    @ApiModelProperty("收益类型(0：服务费、1：车费)")
    private Byte harvestType;

    @ApiModelProperty("订单日期")
    private LocalDate orderDate;

    @ApiModelProperty("是否已结算(0：未结算、1：已结算)")
    private Byte withdrawFunds;

    @ApiModelProperty("是否已提现(0：未提现、1：已提现)")
    private String withdrawalOfCash;

    @ApiModelProperty("技师id")
    private Long massagistId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getHarvestMoney() {
        return harvestMoney;
    }

    public void setHarvestMoney(BigDecimal harvestMoney) {
        this.harvestMoney = harvestMoney;
    }

    public Byte getHarvestType() {
        return harvestType;
    }

    public void setHarvestType(Byte harvestType) {
        this.harvestType = harvestType;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Byte getWithdrawFunds() {
        return withdrawFunds;
    }

    public void setWithdrawFunds(Byte withdrawFunds) {
        this.withdrawFunds = withdrawFunds;
    }

    public String getWithdrawalOfCash() {
        return withdrawalOfCash;
    }

    public void setWithdrawalOfCash(String withdrawalOfCash) {
        this.withdrawalOfCash = withdrawalOfCash;
    }

    public Long getMassagistId() {
        return massagistId;
    }

    public void setMassagistId(Long massagistId) {
        this.massagistId = massagistId;
    }

    @Override
    public String toString() {
        return "PayMassagistIncome{" +
            "id = " + id +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", createUser = " + createUser +
            ", updateUser = " + updateUser +
            ", isDeleted = " + isDeleted +
            ", orderId = " + orderId +
            ", harvestMoney = " + harvestMoney +
            ", harvestType = " + harvestType +
            ", orderDate = " + orderDate +
            ", withdrawFunds = " + withdrawFunds +
            ", withdrawalOfCash = " + withdrawalOfCash +
            ", massagistId = " + massagistId +
        "}";
    }
}
