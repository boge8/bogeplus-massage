package com.bogeplus.user.controller;

import com.bogeplus.user.controller.request.UserRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
@Api(tags = "首页管理",value = "首页面欢迎页")
public class HelloController {
    @GetMapping("/h1")
    @ApiOperation(value = "hello接口",notes = "hello接口详细描述")
    public String hello(@ApiParam(value = "用户名",example = "李博",required = false) @RequestParam(required = false,defaultValue = "xxx") String username,
                        @ApiParam(value = "密码",example = "123123",required = true,defaultValue = "yyy") @RequestParam(required = true,defaultValue = "xxx")String password){
        if (username.length() < 6) {
            throw new RuntimeException("姓名长度太短了!");
        }
        log.info("username:{},password:{}",username,password);
        return "username:"+username+",hello boy!";
    }
    @PostMapping("/addUser")
    @ApiOperation(value = "用户注册接口",notes = "用户注册接口详细描述")
    public UserRequest addUser(@RequestBody UserRequest userRequest){
        log.info("userRequest:{}",userRequest);
        return userRequest;
}
}
