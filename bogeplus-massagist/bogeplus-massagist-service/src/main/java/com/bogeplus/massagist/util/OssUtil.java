package com.bogeplus.massagist.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.auth.sts.AssumeRoleRequest;
import com.aliyuncs.auth.sts.AssumeRoleResponse;
import com.aliyuncs.exceptions.ClientException;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OssUtil {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.roleArn}")
    private String roleArn;

    @Value("${aliyun.oss.bucketName}")
    private String bucketName;

    public String getOssToken() throws ClientException {
        // 创建STS客户端
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-hangzhou", accessKeyId, accessKeySecret);
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

        // 使用 Hutool 的 JSONUtil 将 Map 转换为 JSON 字符串
        return JSONUtil.toJsonStr(result);
    }
}
