package com.bogeplus.massagist.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.profile.DefaultProfile;
import com.bogeplus.massagist.config.AliyunOssConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 工具类：提供获取阿里云 OSS 临时凭证的功能
 */
@Component
public class AliyunOssUtil implements InitializingBean {

    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String bucketName;
    private static String roleArn;

    @Autowired
    private AliyunOssConfig aliyunOssConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        //从配置类中获取并初始化静态字段的值
        endpoint = aliyunOssConfig.getEndpoint();
        accessKeyId = aliyunOssConfig.getAccessKeyId();
        accessKeySecret = aliyunOssConfig.getAccessKeySecret();
        bucketName = aliyunOssConfig.getBucketName();
        roleArn = aliyunOssConfig.getRoleArn();
    }

    public static Map<String, String> getOssToken() throws ClientException {
        // 创建STS客户端
        DefaultProfile profile = DefaultProfile.getProfile("cn-chengdu", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        // 构造请求
        final AssumeRoleRequest request = new AssumeRoleRequest();
        request.setRoleArn(roleArn);
        request.setRoleSessionName("oss-session");
        request.setDurationSeconds(3600L);

        // 发起请求并获取临时凭证
        final AssumeRoleResponse response = client.getAcsResponse(request);
        AssumeRoleResponse.Credentials credentials = response.getCredentials();

        Map<String, String> result = new HashMap<>();
        result.put("accessKeyId", credentials.getAccessKeyId());
        result.put("accessKeySecret", credentials.getAccessKeySecret());
        result.put("securityToken", credentials.getSecurityToken());
        result.put("bucketName", bucketName);
        result.put("endpoint", endpoint);

        return result;
    }
}
