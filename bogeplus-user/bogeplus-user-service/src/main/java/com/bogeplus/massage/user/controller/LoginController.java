package com.bogeplus.massage.user.controller;

import cloud.tianai.captcha.application.ImageCaptchaApplication;
import cloud.tianai.captcha.application.vo.CaptchaResponse;
import cloud.tianai.captcha.application.vo.ImageCaptchaVO;
import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.common.response.ApiResponse;
import cloud.tianai.captcha.validator.common.model.dto.ImageCaptchaTrack;
import com.bogeplus.common.constant.RedisConstant;
import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.common.util.RedisUtil;
import com.bogeplus.common.util.Result;
import com.bogeplus.massage.user.controller.request.SendSmsRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@Slf4j
@Api(tags = "登录管理", value = "登录相关接口")
public class LoginController {
    @Autowired
    private ImageCaptchaApplication application;

    /**
     * 发送手机短信验证码接口
     * 入参：
     *
     * @param String phoneNum手机号
     * @param String imageCode 滑块验证码
     */
    @PostMapping("/sendSms")
    @ApiOperation(value = "发送手机短信验证码接口", notes = "发送手机短信验证码接口详细描述")
    public Result sendSms(@RequestBody SendSmsRequest sendSmsRequest) {
        log.info("phoneNum:{},imageCode:{}", sendSmsRequest.getPhoneNum(), sendSmsRequest.getImageCode());
        //1、查询redis 该手机号当日发送短信总次数
        String userCountSms = RedisConstant.format(RedisConstant.USER_SMS_CODE_COUNT, sendSmsRequest.getPhoneNum());
       Object countSmsObject = RedisUtil.get(userCountSms);
       Integer count = 0;
       if(countSmsObject!=null){
           count = (Integer) countSmsObject;
       }
        //2、如果>10次，则返回当日已经超出发送次数，明日再试
        if(count>10){
            throw new BizException(ServiceCode.SMS_SEND_OVERDUE.getCode(), ServiceCode.SMS_SEND_OVERDUE.getMsg());
        }
        //3、如果>3次，则查询redis 该手机号当前滑块验证码，并进行校验
//        if (count >= 3) {
//            ApiResponse<?> response = null;
//            try {
//                ImageCaptchaTrack imageCaptchaTrack = new ImageCaptchaTrack();
//                BeanUtils.copyProperties(sendSmsRequest,imageCaptchaTrack);
//                response = application.matching(sendSmsRequest.getImageCode(), imageCaptchaTrack);
//            } catch (RuntimeException e) {
//                throw new BizException(ServiceCode.IMAGE_CODE_NOT_EXIST.getCode(), ServiceCode.IMAGE_CODE_NOT_EXIST.getMsg());
//            }
//            if (!response.isSuccess()) {
//        //3.2 如果校验不正确，则返回校验失败
//                return Result.faild(ServiceCode.IMAGE_CODE_ERROR.getMsg(), ServiceCode.IMAGE_CODE_ERROR.getCode());
//            }
//        }
        //4、发送短信验证码
        log.info("发送验证码：123123");
        //5、redis 记录该手机号当日发送短信总次数+1，清除缓存中的滑块验证码key
        RedisUtil.incr(userCountSms, 1);
        //6、返回成功
        return Result.success();
    }

    /**
     * 获取图形验证码
     * 入参：
     *
     */
    @GetMapping("/getImgCode")
    @ApiOperation(value = "获取图形验证码接口", notes = "获取图形验证码接口详细描述")
    public Result<CaptchaResponse> getImgCode() {
        //生成滑块验证码
        // 1.生成滑块验证码(该数据返回给前端用于展示验证码数据)
        CaptchaResponse<ImageCaptchaVO> res1 = application.generateCaptcha(CaptchaTypeConstant.SLIDER);
        log.info("res1:{}", res1);
        return Result.success(res1);

    }
}
