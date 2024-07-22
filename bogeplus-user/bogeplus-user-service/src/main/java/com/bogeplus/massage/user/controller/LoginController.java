package com.bogeplus.massage.user.controller;

import cloud.tianai.captcha.application.ImageCaptchaApplication;
import cloud.tianai.captcha.application.vo.CaptchaResponse;
import cloud.tianai.captcha.application.vo.ImageCaptchaVO;
import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import com.bogeplus.common.util.Result;
import com.bogeplus.common.util.UserUtil;
import com.bogeplus.massage.user.controller.request.LoginRequest;
import com.bogeplus.massage.user.controller.request.SendSmsRequest;
import com.bogeplus.massage.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@Api(tags = "登录管理", value = "登录相关接口")
public class LoginController {
    @Autowired
    private ImageCaptchaApplication application;

    @Autowired
    private UserInfoService userInfoService;


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


    @ApiOperation(value = "test", notes = "test")
    @GetMapping("/test1")
    public String test1(){
        String account = UserUtil.getAccount();
        String id = UserUtil.getId();
        log.info("account:{},id:{}",account,id);
        return "test1";
    }
}
