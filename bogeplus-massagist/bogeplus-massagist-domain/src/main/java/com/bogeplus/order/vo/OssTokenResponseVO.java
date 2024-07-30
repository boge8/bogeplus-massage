package com.bogeplus.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "OSSToken响应DTO", description = "OSSToken响应DTO，包含凭证和配置信息")
public class OssTokenResponseVO {
    @ApiModelProperty(value = "用于访问OSS的AccessKeyID", example = "LTAI5tB7o3tQj8KnR8FZ24x2")
    private String accessKeyId;
    @ApiModelProperty(value = "用于访问OSS的AccessKeySecret", example = "K7sP9Ks4H8m7GhQ8vB9TnX2uVZ0D4j7D")
    private String accessKeySecret;
    @ApiModelProperty(value = "用于临时访问OSS的安全令牌", example = "CAISsQF1q7G1xJdT2OX6w09/...")
    private String securityToken;
    @ApiModelProperty(value = "OSS存储桶的名称", example = "my-oss-bucket")
    private String bucketName;
    @ApiModelProperty(value = "访问OSS服务的终端地址", example = "https://oss-cn-hangzhou.aliyuncs.com")
    private String endpoint;
}
