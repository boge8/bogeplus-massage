package com.bogeplus.message.service.impl;

import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.message.config.SmsConfig;
import com.bogeplus.message.service.SmsService;
import com.bogeplus.message.util.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class AliyunSmsServiceImpl implements SmsService {
    @Autowired
    private SmsConfig smsConfig;
    @Override
    public Boolean sendSms(String phone, String content) {
        String method = "POST";
        String appcode =smsConfig.getAppCode();
        String host = smsConfig.getHost();
        String path = smsConfig.getPath();
        String templateId = smsConfig.getTemplateId();
        String signId = smsConfig.getSmsSignId();

        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", phone);
        querys.put("param", "**code**:"+content+",**minute**:5");

//smsSignId（短信前缀）和templateId（短信模板），可登录国阳云控制台自助申请。参考文档：http://help.guoyangyun.com/Problem/Qm.html
        // 需要提前申请签名和模板是需要审核的，这个到时候再自己的公司里面，去单独申请

        querys.put("smsSignId",signId);
        querys.put("templateId", templateId);
        Map<String, String> bodys = new HashMap<String, String>();
        try {
            /**
             * 重要提示如下:
             * HttpUtils请从\r\n\t    \t* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java\r\n\t    \t* 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
            return true;
        } catch (Exception e) {
            throw  new BizException(ServiceCode.SMS_SEND_FAILD.getCode(),ServiceCode.SMS_SEND_FAILD.getMsg());
        }
    }
}
