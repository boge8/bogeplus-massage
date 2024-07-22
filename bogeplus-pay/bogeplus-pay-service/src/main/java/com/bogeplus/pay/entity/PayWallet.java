package com.bogeplus.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 钱包表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_pay_wallet")
@ApiModel(value = "PayWallet对象", description = "钱包表")
public class PayWallet implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("明细ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("技师ID")
    private Integer massagistId;

    @ApiModelProperty("余额")
    private BigDecimal balance;

    @ApiModelProperty("未结算金额")
    private BigDecimal unsettledAmount;

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

    public Integer getMassagistId() {
        return massagistId;
    }

    public void setMassagistId(Integer massagistId) {
        this.massagistId = massagistId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getUnsettledAmount() {
        return unsettledAmount;
    }

    public void setUnsettledAmount(BigDecimal unsettledAmount) {
        this.unsettledAmount = unsettledAmount;
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
        return "PayWallet{" +
            "id = " + id +
            ", massagistId = " + massagistId +
            ", balance = " + balance +
            ", unsettledAmount = " + unsettledAmount +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", createUser = " + createUser +
            ", updateUser = " + updateUser +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
