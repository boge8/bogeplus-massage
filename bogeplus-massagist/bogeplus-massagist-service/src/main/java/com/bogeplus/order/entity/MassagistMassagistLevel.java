package com.bogeplus.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 技师等级规则表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_massagist_massagist_level")
@ApiModel(value = "MassagistMassagistLevel对象", description = "技师等级规则表")
public class MassagistMassagistLevel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("等级ID")
    private Long id;

    @ApiModelProperty("等级名称")
    private String levelName;

    @ApiModelProperty("分成比例")
    private BigDecimal sharePercentage;

    @ApiModelProperty("最低成交额")
    private BigDecimal minimumTransaction;

    @ApiModelProperty("在线时长要求")
    private Integer onlineHoursRequired;

    @ApiModelProperty("总续钟率")
    private BigDecimal renewalRateRequired;

    @ApiModelProperty("总积分要求")
    private Integer totalPointsRequired;

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

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public BigDecimal getSharePercentage() {
        return sharePercentage;
    }

    public void setSharePercentage(BigDecimal sharePercentage) {
        this.sharePercentage = sharePercentage;
    }

    public BigDecimal getMinimumTransaction() {
        return minimumTransaction;
    }

    public void setMinimumTransaction(BigDecimal minimumTransaction) {
        this.minimumTransaction = minimumTransaction;
    }

    public Integer getOnlineHoursRequired() {
        return onlineHoursRequired;
    }

    public void setOnlineHoursRequired(Integer onlineHoursRequired) {
        this.onlineHoursRequired = onlineHoursRequired;
    }

    public BigDecimal getRenewalRateRequired() {
        return renewalRateRequired;
    }

    public void setRenewalRateRequired(BigDecimal renewalRateRequired) {
        this.renewalRateRequired = renewalRateRequired;
    }

    public Integer getTotalPointsRequired() {
        return totalPointsRequired;
    }

    public void setTotalPointsRequired(Integer totalPointsRequired) {
        this.totalPointsRequired = totalPointsRequired;
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
        return "MassagistMassagistLevel{" +
            "id = " + id +
            ", levelName = " + levelName +
            ", sharePercentage = " + sharePercentage +
            ", minimumTransaction = " + minimumTransaction +
            ", onlineHoursRequired = " + onlineHoursRequired +
            ", renewalRateRequired = " + renewalRateRequired +
            ", totalPointsRequired = " + totalPointsRequired +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", createUser = " + createUser +
            ", updateUser = " + updateUser +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
