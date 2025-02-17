package com.bogeplus.common.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ServiceCode {
    SUCCESS("操作成功", 200),
    FAILED("操作失败", 500),
    PARAM_ERROR("参数错误", 400),
    NOT_LOGIN("未登录", 401),
    RESOURCE_NOT_FOUND("资源未找到", 404),
    PERMISSION_DENIED("没有权限", 403),
    DUPLICATE_ENTRY("重复记录", 409),
    SERVER_ERROR("服务器内部错误", 503),



    /* 关于业务服务的状态码约定
        使用6位数字来定义
        100201:
        100202:
        100401:
        200500:
        前三位数字：表示业务模块
        后三位数字：表示业务状态，2开头表示正常，4、5开头表示异常
     */
    /*优惠券模块*/
    COUPONS_LESS_THAN_ZERO("优惠券价格小于0,请输入正确的价格",300401),
    COUPONS_LESS_THAN_TODAY("优惠券过期时间小于今天",300402),
    COUPONS_NOT_EXIST("优惠券不存在",300403),

    /*优惠券订单关联*/
    ORDER_NOT_EXIST("订单id不存在",300405),

    //用户已注册
    USER_ALREADY_REGISTERED("用户已注册", 100501),
    //用户不存在
    USER_NOT_EXIST("用户不存在", 100401),
    IMAGE_CODE_ERROR( "图形验证码错误",100402 ),
    SMS_CODE_ERROR( "短信验证码错误",100502 ),
    SMS_CODE_NOT_EXIST( "短信验证码不存在",100503 ),
     SMS_SEND_OVERDUE  ("短信发送过于频繁，请明天再试！",100502 ),
     SMS_SEND_REPEAT  ("短信5分钟内有效，1分钟内不可重复发送！",100503 ),
     IMAGE_CODE_NOT_EXIST ("图形验证码不存在",100403 ),
    SMS_SEND_FAILD ("短信发送失败！",200501 ) ,
    //技师相关状态码
    MASSAGIST_NOT_EXIST("技师不存在",300401),
    MASSAGIST_PROFILE_PICTURE_ALREADY_EXIST("技师头像已存在",300402),
    MASSAGIST_EXPIRED_DATA("数据已过期，请刷新",300403);
    private String msg;
    private Integer code;

    ServiceCode(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

}
