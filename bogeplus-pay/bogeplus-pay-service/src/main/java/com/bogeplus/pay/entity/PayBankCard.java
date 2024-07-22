package com.bogeplus.pay.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 银行卡信息表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_pay_bank_card")
@ApiModel(value = "PayBankCard对象", description = "银行卡信息表")
public class PayBankCard implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键:银行卡ID")
    private Long id;

    @ApiModelProperty("技师ID，关联技师表")
    private Long massagistId;

    @ApiModelProperty("银行编号")
    private String bankCode;

    @ApiModelProperty("银行名称")
    private String bankName;

    @ApiModelProperty("银行卡号，存储时部分隐藏")
    private String cardNumber;

    @ApiModelProperty("银行卡类型 0:储蓄卡, 1:信用卡, 2:其他")
    private Byte cardType;

    @ApiModelProperty("是否为默认卡")
    private Boolean isDefault;

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

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Byte getCardType() {
        return cardType;
    }

    public void setCardType(Byte cardType) {
        this.cardType = cardType;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
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
        return "PayBankCard{" +
            "id = " + id +
            ", massagistId = " + massagistId +
            ", bankCode = " + bankCode +
            ", bankName = " + bankName +
            ", cardNumber = " + cardNumber +
            ", cardType = " + cardType +
            ", isDefault = " + isDefault +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", createUser = " + createUser +
            ", updateUser = " + updateUser +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
