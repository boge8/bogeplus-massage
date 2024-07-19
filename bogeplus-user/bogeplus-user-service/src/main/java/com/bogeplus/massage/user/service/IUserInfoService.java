package com.bogeplus.massage.user.service;

import com.bogeplus.common.util.Result;
import com.bogeplus.massage.user.controller.request.LoginRequest;
import com.bogeplus.massage.user.controller.request.SendSmsRequest;
import com.bogeplus.massage.user.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 上门按摩用户信息表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-16
 */
public interface IUserInfoService extends IService<UserInfo> {

    Result<Map<String, String>> login(LoginRequest loginRequest) ;

    Boolean sendSms(SendSmsRequest sendSmsRequest);
}
