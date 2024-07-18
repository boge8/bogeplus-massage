package com.bogeplus.massage.user.controller.request;

import cloud.tianai.captcha.validator.common.model.dto.ImageCaptchaTrack;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "发送短信验证码请求参数",description = "发送短信验证码请求参数")
public class SendSmsRequest {
    @ApiModelProperty(value = "手机号",example = "18888888888",required = true)
    private String phoneNum;
//    @ApiModelProperty(value = "滑块验证码",example = "asf23423ddasfs233",required = false)
//    private String imageCode;
//
//    /** 背景图片宽度. */
//    @ApiModelProperty(value = "背景图片宽度",example = "300",required = false)
//    private Integer bgImageWidth;
//    /** 背景图片高度. */
//    @ApiModelProperty(value = "背景图片高度",example = "300",required = false)
//    private Integer bgImageHeight;
//    /** 模板图片宽度. */
//    @ApiModelProperty(value = "模板图片宽度",example = "300",required = false)
//    private Integer templateImageWidth;
//    /** 模板图片高度. */
//    @ApiModelProperty(value = "模板图片高度",example = "300",required = false)
//    private Integer templateImageHeight;
//    /** 滑动开始时间. */
//    @ApiModelProperty(value = "滑动开始时间",example = "2021-01-01 00:00:00",required = false)
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private Date startTime;
//    /** 滑动结束时间. */
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @ApiModelProperty(value = "滑动结束时间",example = "2021-01-01 00:00:00",required = false)
//    private Date stopTime;
//
//    //横坐标
//    @ApiParam(name = "left", value = "横坐标", required = true)
//    private Integer left;
//
//    //纵坐标
//    @ApiParam(name = "top", value = "纵坐标", required = true)
//    private Integer top;
//    /** 滑动的轨迹. */
//    @ApiModelProperty(value = "滑动的轨迹",example = "asf23423ddasfs233",required = false)
//    private List<ImageCaptchaTrack.Track> trackList;
//    /** 扩展数据，用户传输加密数据等.*/
//    @ApiModelProperty(value = "扩展数据，用户传输加密数据等",example = "asf23423ddasfs233",required = false)
//    private Object data;

}
