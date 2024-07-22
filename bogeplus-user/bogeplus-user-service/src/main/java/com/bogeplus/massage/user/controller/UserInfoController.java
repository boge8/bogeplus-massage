package com.bogeplus.massage.user.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.massage.user.entity.UserInfo;
import com.bogeplus.massage.user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 上门按摩用户信息表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-16
 */
@Api(tags = "用户信息管理接口")
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "更新用户信息", notes = "根据用户ID更新用户信息")
    @PutMapping("/{id}")
    public Result updateUser(@ApiParam(value = "用户ID", required = true) @PathVariable Long id,
                             @ApiParam(value = "用户信息", required = true) @RequestBody UserInfo userInfo) {
        boolean updated = userInfoService.updateUser(id, userInfo);
        return Result.success(updated);
    }

    @ApiOperation(value = "删除用户", notes = "根据用户ID删除用户")
    @DeleteMapping("/{id}")
    public Result deleteUser(@ApiParam(value = "用户ID", required = true) @PathVariable Long id) {
        boolean deleted = userInfoService.deleteUser(id);
        return Result.success(deleted);
    }

    @ApiOperation(value = "获取用户信息", notes = "根据用户ID获取用户信息")
    @GetMapping("/{id}")
    public Result getUserById(@ApiParam(value = "用户ID", required = true) @PathVariable Long id) {
        UserInfo user = userInfoService.getUserById(id);
        return Result.success(user);
    }

    @ApiOperation(value = "获取用户分页信息", notes = "根据页码和每页大小获取用户分页信息")
    @GetMapping("/page")
    public Result getUserPage(@ApiParam(value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") int page,
                              @ApiParam(value = "每页大小", defaultValue = "5") @RequestParam(defaultValue = "5") int size) {
        // 假设getUserPage返回的是一个分页对象，这里使用Object代替，具体类型需要您根据实际情况替换
        Object userPage = userInfoService.getUserPage(page, size);
        return Result.success(userPage);
    }
}