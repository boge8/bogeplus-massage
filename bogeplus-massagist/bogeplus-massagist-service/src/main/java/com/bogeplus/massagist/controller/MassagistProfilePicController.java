package com.bogeplus.massagist.controller;

import com.aliyuncs.exceptions.ClientException;
import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.util.OssUtil;
import com.bogeplus.massagist.service.IMassagistInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/massagistInfo")
@Api(value = "新增和修改技师头像接口",tags = "新增和修改技师头像接口")
public class MassagistProfilePicController {

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private IMassagistInfoService massagistInfoService;

    @ApiOperation(value = "获取OSS Token")
    @GetMapping("/token")
    public Result<String> getOssToken() {

        String result = null;
        try {
            result = ossUtil.getOssToken();
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
        return Result.success(result,ServiceCode.SUCCESS);
    }

    @ApiOperation(value = "更新技师头像")
    @PutMapping("/updateProfilePicture")
    public Result<String> updateProfilePicture(@RequestParam Integer id, @RequestParam String profilePicture) {
        try {
            massagistInfoService.updateProfilePicture(id, profilePicture);
            return Result.success("技师头像更新成功！", ServiceCode.SUCCESS);
        } catch (BizException e) {
            return Result.faild(e.getMsg(), ServiceCode.FAILED);
        }
    }

    @ApiOperation(value = "新增技师头像")
    @PostMapping("/addProfilePicture")
    public Result<String> addProfilePicture(@RequestParam Integer id, @RequestParam String profilePicture) {
        try {
            massagistInfoService.addProfilePicture(id, profilePicture);
            return Result.success("技师头像新增成功！", ServiceCode.SUCCESS);
        } catch (BizException e) {
            return Result.faild(e.getMsg(), ServiceCode.FAILED);
        }
    }
}
