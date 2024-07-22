package com.bogeplus.massagist.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 技师表
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@TableName("massage_massagist_info")
@ApiModel(value = "MassagistInfo对象", description = "技师表")
public class MassagistInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("技师姓名")
    private String name;

    @ApiModelProperty("技师头像")
    private String profilePicture;

    @ApiModelProperty("是否接种（未接种：0，已接种：1)")
    private Boolean vaccinated;

    @ApiModelProperty("技师评分")
    private BigDecimal rating;

    @ApiModelProperty("服务数量")
    private Integer completedOrders;

    @ApiModelProperty("免费里程")
    private Integer freeMile;

    @ApiModelProperty("点赞数量")
    private Integer likes;

    @ApiModelProperty("评论数量")
    private Integer comment;

    @ApiModelProperty("技师简介")
    private String bio;

    @ApiModelProperty("技师认证状态，未认证：0")
    private Boolean massagistVerified;

    @ApiModelProperty("实名认证状态，未认证：0，已认证：1")
    private Boolean realNameVerified;

    @ApiModelProperty("经纬度")
    private String longtitudeLatitude;

    @ApiModelProperty("接单地址")
    private String receiveAddress;

    @ApiModelProperty("技师所在城市")
    private String location;

    @ApiModelProperty("可预约时间段")
    private String availableTimeSlots;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Boolean getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(Boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(Integer completedOrders) {
        this.completedOrders = completedOrders;
    }

    public Integer getFreeMile() {
        return freeMile;
    }

    public void setFreeMile(Integer freeMile) {
        this.freeMile = freeMile;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Boolean getMassagistVerified() {
        return massagistVerified;
    }

    public void setMassagistVerified(Boolean massagistVerified) {
        this.massagistVerified = massagistVerified;
    }

    public Boolean getRealNameVerified() {
        return realNameVerified;
    }

    public void setRealNameVerified(Boolean realNameVerified) {
        this.realNameVerified = realNameVerified;
    }

    public String getLongtitudeLatitude() {
        return longtitudeLatitude;
    }

    public void setLongtitudeLatitude(String longtitudeLatitude) {
        this.longtitudeLatitude = longtitudeLatitude;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailableTimeSlots() {
        return availableTimeSlots;
    }

    public void setAvailableTimeSlots(String availableTimeSlots) {
        this.availableTimeSlots = availableTimeSlots;
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
        return "MassagistInfo{" +
            "id = " + id +
            ", name = " + name +
            ", profilePicture = " + profilePicture +
            ", vaccinated = " + vaccinated +
            ", rating = " + rating +
            ", completedOrders = " + completedOrders +
            ", freeMile = " + freeMile +
            ", likes = " + likes +
            ", comment = " + comment +
            ", bio = " + bio +
            ", massagistVerified = " + massagistVerified +
            ", realNameVerified = " + realNameVerified +
            ", longtitudeLatitude = " + longtitudeLatitude +
            ", receiveAddress = " + receiveAddress +
            ", location = " + location +
            ", availableTimeSlots = " + availableTimeSlots +
            ", createTime = " + createTime +
            ", updateTime = " + updateTime +
            ", createUser = " + createUser +
            ", updateUser = " + updateUser +
            ", isDeleted = " + isDeleted +
        "}";
    }
}
