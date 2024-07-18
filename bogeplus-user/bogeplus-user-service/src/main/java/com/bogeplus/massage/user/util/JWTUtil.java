package com.bogeplus.massage.user.util;

import com.bogeplus.massage.user.entity.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JWTUtil {
    // 生成密钥


    private static final String  Bearer = "Bearer ";
    private final static long EXPIRED = 1000 * 60 * 60 * 24 * 7;
    private final static String SUBJECT = "massage";
    private  static final String SECRET = "masDDSS3sdfsddf21123fff";
    // 生成 Token
    public static String generateToken(UserInfo user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("headImg", user.getHeadImg());
        claims.put("account", user.getAccount());
        claims.put("userNickname", user.getUserNickname());

        String compact = Jwts.builder()
                .setClaims(claims)
                .setSubject(SUBJECT)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRED)) // 10小时有效期
                .signWith( SignatureAlgorithm.HS256, SECRET)
                .compact();
        return Bearer + compact;
    }

    // 解析 Token
    public static UserInfo parseToken(String token) {
        Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(Bearer, "")).getBody();
        UserInfo userInfo = new UserInfo();
        userInfo.setHeadImg((String) body.get("headImg"));
        userInfo.setAccount((String) body.get("account"));
        userInfo.setUserNickname((String) body.get("userNickname"));
        return userInfo;
    }

}
