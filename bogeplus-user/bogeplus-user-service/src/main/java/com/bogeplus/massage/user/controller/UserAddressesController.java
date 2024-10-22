package com.bogeplus.massage.user.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.massage.user.dto.UserAddressesDTO;
import com.bogeplus.massage.user.service.IUserAddressesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 地址表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-26
 */
@RestController
@RequestMapping("/userAddresses")
@Api(tags = "用户地址管理")
public class UserAddressesController {
    @Autowired
    private IUserAddressesService userAddressesService;

    @PostMapping("/addUserAddress")
    @ApiOperation(value = "添加用户地址", notes = "添加用户地址")
    public Result addUserAddress(@RequestBody UserAddressesDTO userAddressesDTO) {
        return Result.success(userAddressesService.addUserAddress(userAddressesDTO));
    }

    @PostMapping("/updateUserAddress")
    @ApiOperation(value = "修改用户地址", notes = "修改用户地址")
    public Result updateUserAddress(@RequestParam("addressId") long addressId, @RequestBody UserAddressesDTO userAddressesDTO) {
        return Result.success(userAddressesService.updateUserAddress(addressId, userAddressesDTO));
    }

    @GetMapping("/getUserAddress")
    @ApiOperation(value = "获取用户地址", notes = "获取用户地址")
    public Result getUserAddress() {
        return Result.success(userAddressesService.getUserAddress());
    }

    @DeleteMapping("/deleteUserAddress")
    @ApiOperation(value = "删除用户地址", notes = "删除用户地址")
    public Result deleteUserAddress(@RequestParam("addressId") Long id) {
        return Result.success(userAddressesService.removeById(id));
    }

    @PutMapping("/setAddressDefault")
    @ApiOperation(value = "设置地址为默认地址", notes = "设置地址为默认地址")
    public Result setAddressDefault(@RequestParam("addressId") Long id) {
        return Result.success(userAddressesService.setUserAddressDefault(id));
    }
}
