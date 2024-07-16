package com.bogeplus.common.constant;

/**
 * 统一管理redis 各种缓存的前缀！
 */
public class RedisConstant {
    public static final String USER_TOKEN = "user_token:"; // 用户token缓存
    public static final String USER_SMS_CODE = "user_sms_code:%s"; // 用户信息缓存
    public static final String USER_SMS_CODE_COUNT = "user_sms_code_count:%s"; // 保存用户手机号当日发送次数

    // 静态工具方法 格式化redis key
    public static String format(String key, Object... args) {
        String format = String.format(key, args);
        return format;
    }
}
