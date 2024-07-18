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
import com.bogeplus.massage.user.controller.request.LoginRequest;
import com.bogeplus.massage.user.controller.request.SendSmsRequest;
import com.bogeplus.massage.user.service.IUserInfoService;
import com.bogeplus.message.dto.SmsDTO;
import com.bogeplus.message.feign.SmsFeign;
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
import java.util.Map;

@RestController
@Slf4j
@Api(tags = "登录管理", value = "登录相关接口")
public class LoginController {
    @Autowired
    private ImageCaptchaApplication application;

    @Autowired
    private IUserInfoService userInfoService;


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
        log.info("phoneNum:{}", sendSmsRequest.getPhoneNum());
        Boolean bool = userInfoService.sendSms(sendSmsRequest);
        return Result.status(bool);
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

    @PostMapping("/login")
    @ApiOperation(value = "登录接口", notes = "用户端登录")
    public Result login(@RequestBody LoginRequest loginRequest) {
        Result<Map<String,String>> result = userInfoService.login(loginRequest);
        return result;
    }
}
