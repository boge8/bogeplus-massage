package com.bogeplus.massage.user.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bogeplus.common.constant.RedisConstant;
import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.common.util.RedisUtil;
import com.bogeplus.common.util.Result;
import com.bogeplus.massage.user.controller.request.LoginRequest;
import com.bogeplus.massage.user.controller.request.SendSmsRequest;
import com.bogeplus.massage.user.entity.UserInfo;
import com.bogeplus.massage.user.mapper.UserInfoMapper;
import com.bogeplus.massage.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.massage.user.util.JWTUtil;
import com.bogeplus.message.dto.SmsDTO;
import com.bogeplus.message.feign.SmsFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 上门按摩用户信息表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-16
 */
@Service
@Slf4j
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private SmsFeign smsFeign;

    @Autowired
    UserInfoMapper userInfoMapper;

    @Override
    public Result<Map<String, String>> login(LoginRequest loginRequest) {
        //1、获取redis 验证码
        Object code = RedisUtil.get(RedisConstant.format(RedisConstant.USER_SMS_CODE, loginRequest.getPhoneNum()));

        //2、校验验证码是否正确
        if (code != null) {
            String codeStr = (String) code;
            if (!codeStr.equals(loginRequest.getSmsCode())) {
                return Result.faild(ServiceCode.SMS_CODE_ERROR.getMsg(), ServiceCode.SMS_CODE_ERROR.getCode());

            }
        } else {
            return Result.faild(ServiceCode.SMS_CODE_NOT_EXIST.getMsg(), ServiceCode.SMS_CODE_NOT_EXIST.getCode());
        }
        // 删除redis缓存短信
        RedisUtil.del(RedisConstant.format(RedisConstant.USER_SMS_CODE, loginRequest.getPhoneNum()));
        //3、查询数据表该用户是否已注册
        LambdaQueryWrapper<UserInfo> qw = new LambdaQueryWrapper<>();
        qw.eq(UserInfo::getAccount, loginRequest.getPhoneNum());
        UserInfo userInfo = userInfoMapper.selectOne(qw);
        //4、如果未注册，应该自动注册，生成随机的用户信息给予默认值
        if (userInfo == null) {
            userInfo = new UserInfo();
            // hutool获取雪花算法ID
            Long id = IdUtil.getSnowflake(1, 1).nextId();
            userInfo.setId(id);
            userInfo.setUserNickname("用户" + loginRequest.getPhoneNum());
            userInfo.setAccount(loginRequest.getPhoneNum());
            userInfo.setGender(2);
            userInfo.setRealNameVerified(false);
            userInfo.setRegistTime(new Date());
            userInfo.setCreateUser(id);
            userInfo.setUpdateUser(id);
            userInfo.setCreateTime(new Date());
            userInfo.setUpdateTime(new Date());
            userInfo.setIsDeleted(false);
            userInfo.setHeadImg("http://xxxx.com/xxx.jpg");

        }
        //5、封装访问令牌、刷新令牌
        String token = JWTUtil.generateToken(userInfo);
        // 基于用户的基本信息 id、手机号、头像...设计一个JWT工具类，进行封装，生成用户的登录Token
        //6、封装结果返回给前端
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("accessToken", token);

        return Result.success(stringStringHashMap);
    }

    @Override
    public Boolean sendSms(SendSmsRequest sendSmsRequest) {
        String userSmsCode = RedisConstant.format(RedisConstant.USER_SMS_CODE, sendSmsRequest.getPhoneNum());
        //0、短信发送验证码 60秒内不能重新发送
        long expire = RedisUtil.getExpire(userSmsCode);
        if (expire > 240) {
            log.info("短信仍然在有效期不能重复发送:{}", sendSmsRequest.getPhoneNum());
            throw new BizException(ServiceCode.SMS_SEND_REPEAT.getCode(), ServiceCode.SMS_SEND_REPEAT.getMsg());
        }
        //1、查询redis 该手机号当日发送短信总次数
        String userCountSms = RedisConstant.format(RedisConstant.USER_SMS_CODE_COUNT, sendSmsRequest.getPhoneNum());
        Object countSmsObject = RedisUtil.get(userCountSms);
        Integer count = 0;
        if (countSmsObject != null) {
            count = (Integer) countSmsObject;
        }
        //2、如果>10次，则返回当日已经超出发送次数，明日再试
        if (count > 10) {
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
        // 随机生成6位验证码
        String code = (int) ((Math.random() * 9 + 1) * 100000) + "";
        log.info("发送验证码：{}", code);
        SmsDTO smsDTO = new SmsDTO(sendSmsRequest.getPhoneNum(), code);
        // 缓存redis
        RedisUtil.set(userSmsCode, code, 300);

//        Result result = smsFeign.sendSms(smsDTO);
//        Integer code1 = result.getCode();
        Integer code1 = 200;
        boolean bool = code1.equals(ServiceCode.SUCCESS.getCode());
        if (bool) RedisUtil.incr(userCountSms, 1);
        return bool;
    }

    public static void main(String[] args) {
        String s = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXNzYWdlIiwiaGVhZEltZyI6Imh0dHA6Ly94eHh4LmNvbS94eHguanBnIiwidXNlck5pY2tuYW1lIjoi55So5oi3MTY2MDExMjU2MjMiLCJleHAiOjE3MjE5MTY5MTksImlhdCI6MTcyMTMxMjExOSwiYWNjb3VudCI6IjE2NjAxM1I1NjIzIn0.X2N1pxy9qYLReAGn-DPSGESWPRJKfv8RSh2Go3iHGBA";
        UserInfo userInfo = JWTUtil.parseToken(s);
        System.out.println(userInfo);
    }


    /**
     * 更新用户信息
     *
     * @param userInfo 新的用户信息
     * @return 更新是否成功
     */
    public boolean updateUser(UserInfo userInfo) {
        return userInfoMapper.updateById(userInfo) > 0;
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 删除是否成功
     */
    public boolean deleteUser(Long id) {
        return userInfoMapper.deleteById(id) > 0;
    }

    /**
     * 根据用户ID获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    public UserInfo getUserById(Long id) {
        return userInfoMapper.selectById(id);
    }

    /**
     * 获取用户分页信息
     *
     * @param page 页码
     * @param size 每页大小
     * @return 用户分页信息
     */
    public Object getUserPage(int page, int size) {
        // 创建分页对象
        Page<UserInfo> userPage = new Page<>(page, size);
        // 使用 MyBatis Plus 的 selectPage 方法进行分页查询
        userInfoMapper.selectPage(userPage, null); // 第二个参数为查询条件，这里为 null 表示无条件查询
        return userPage;
    }


}
