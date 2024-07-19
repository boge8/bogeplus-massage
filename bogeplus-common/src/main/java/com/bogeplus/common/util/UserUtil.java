package com.bogeplus.common.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserUtil {
    // 静态方法从webmvc中获取httpRequest请求头数据x-forward参数，并放到ThreadLocal中去
    public static String getId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String header = request.getHeader("x-forward");
            if (header != null) {
                JSONObject entries = JSONUtil.parseObj(header);
                return entries.get("id").toString();
            }
        }
        return null;
    }

    public static String getAccount() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            String header = request.getHeader("x-forward");
            if (header != null) {
                JSONObject entries = JSONUtil.parseObj(header);
                return entries.get("account").toString();
            }
        }
        return null;
    }
}
