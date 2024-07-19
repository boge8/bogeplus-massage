package com.bogeplus.bogeplusgateway.util;

import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTUtil {
    // 生成密钥


    private static final String  Bearer = "Bearer ";
    private  static final String SECRET = "masDDSS3sdfsddf21123fff";

    // 解析 Token  返回UserInfo json格式字符串
    public static String parseToken(String token) {
        Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(Bearer, "")).getBody();
        HashMap<String, Object> userInfoMap = new HashMap<>();
        userInfoMap.put("account", (String) body.get("account"));
        userInfoMap.put("id", (Long) body.get("id"));
        String jsonStr = JSONUtil.toJsonStr(userInfoMap);

        return jsonStr;
    }

}
