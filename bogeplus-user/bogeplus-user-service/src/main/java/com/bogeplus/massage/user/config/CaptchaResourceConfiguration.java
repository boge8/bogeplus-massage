package com.bogeplus.massage.user.config;

import cloud.tianai.captcha.common.constant.CaptchaTypeConstant;
import cloud.tianai.captcha.resource.ResourceStore;
import cloud.tianai.captcha.resource.common.model.dto.Resource;
import cloud.tianai.captcha.resource.common.model.dto.ResourceMap;
import cloud.tianai.captcha.resource.impl.provider.ClassPathResourceProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class CaptchaResourceConfiguration {

    private final ResourceStore resourceStore;

    @PostConstruct
    public void init() {

        // 2. 添加自定义背景图片, resource 的参数1为资源类型(默认支持 classpath/file/url ), resource 的参数2为资源路径, resource 的参数3为标签
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/a.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/b.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/c.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/d.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/e.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/g.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/h.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/i.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.SLIDER, new Resource("classpath", "bgimages/j.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.ROTATE, new Resource("classpath", "bgimages/48.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.CONCAT, new Resource("classpath", "bgimages/48.jpg", "default"));
        resourceStore.addResource(CaptchaTypeConstant.WORD_IMAGE_CLICK, new Resource("classpath", "bgimages/c.jpg", "default"));
    }
}