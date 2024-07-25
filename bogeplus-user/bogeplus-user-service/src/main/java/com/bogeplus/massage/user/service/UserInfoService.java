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
public interface UserInfoService extends IService<UserInfo> {

    Result<Map<String, String>> login(LoginRequest loginRequest) ;

    Boolean sendSms(SendSmsRequest sendSmsRequest);

    /**
     * 更新用户信息
     *
     * @param userInfo 新的用户信息
     * @return 更新是否成功
     */
    boolean updateUser(UserInfo userInfo);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 删除是否成功
     */
    boolean deleteUseById(Long id);

    /**
     * 根据用户ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    UserInfo getUserById(Long id);

    /**
     * 获取用户分页信息
     *
     * @param page 页码
     * @param size 每页大小
     * @return 用户分页信息
     */
    Object getUserPage(int page, int size);
}
