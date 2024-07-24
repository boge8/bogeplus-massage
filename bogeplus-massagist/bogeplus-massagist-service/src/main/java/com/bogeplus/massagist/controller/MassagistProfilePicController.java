package com.bogeplus.massagist.controller;

import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.util.OssUtil;
import com.bogeplus.massagist.service.IMassagistInfoService;
import com.aliyuncs.exceptions.ClientException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "新增或修改技师头像接口")
@RestController
@RequestMapping("/massagistInfo")
public class MassagistProfilePicController {

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private IMassagistInfoService massagistInfoService;

    @ApiOperation(value = "获取OSS Token")
    @GetMapping("/token")
    public Result<String> getOssToken() {
        try {
            String result = ossUtil.getOssToken();
            return Result.success(result,ServiceCode.SUCCESS);
        } catch (ClientException e) {
            return Result.faild("获取token失败",ServiceCode.FAILED);
        }
    }

    @ApiOperation(value = "保存或更新按摩师头像")
    @PostMapping("/saveOrUpdateProfilePicture")
    public Result<String> saveOrUpdateProfilePicture(@RequestParam Integer id, @RequestParam String profilePicture) {
        try {
            massagistInfoService.saveOrUpdateProfilePicture(id, profilePicture);
            return Result.success("技师头像新增或修改成功！",ServiceCode.SUCCESS);
        } catch (Exception e) {
            return Result.faild("技师头像新增或修改失败！",ServiceCode.FAILED);
        }
    }
}
