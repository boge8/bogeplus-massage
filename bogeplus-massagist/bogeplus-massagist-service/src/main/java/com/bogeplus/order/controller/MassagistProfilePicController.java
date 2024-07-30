package com.bogeplus.order.controller;

import com.aliyuncs.exceptions.ClientException;
import com.bogeplus.common.util.Result;
import com.bogeplus.order.vo.OssTokenResponseVO;
import com.bogeplus.order.util.AliyunOssUtil;
import com.bogeplus.order.service.IMassagistInfoService;
import com.bogeplus.order.dto.MassagistProfilePicDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/massagistInfo")
@Api(value = "新增和修改技师头像接口",tags = "新增和修改技师头像接口")
public class MassagistProfilePicController {

    @Autowired
    private IMassagistInfoService massagistInfoService;

    @ApiOperation(value = "获取OSSToken" , notes = "获取用于直接上传文件到OSS的临时访问凭证")
    @GetMapping("/token")
    public Result<OssTokenResponseVO> getOssToken() {
        Map<String, String> resultMap = null;
        try {
            resultMap = AliyunOssUtil.getOssToken();
        } catch (ClientException e) {
            throw new RuntimeException(e);
        }
        OssTokenResponseVO ossTokenResponseVO = new OssTokenResponseVO();
        ossTokenResponseVO.setAccessKeyId(resultMap.get("accessKeyId"));
        ossTokenResponseVO.setAccessKeySecret(resultMap.get("accessKeySecret"));
        ossTokenResponseVO.setSecurityToken(resultMap.get("securityToken"));
        ossTokenResponseVO.setBucketName(resultMap.get("bucketName"));
        ossTokenResponseVO.setEndpoint(resultMap.get("endpoint"));
        return Result.success("获取OSSToken成功", ossTokenResponseVO);
    }

    @ApiOperation(value = "更新技师头像")
    @PutMapping("/updateProfilePicture")
    public Result<MassagistProfilePicDTO> updateProfilePicture(@RequestParam Long id, @RequestParam String profilePicture) {
        MassagistProfilePicDTO massagistProfilePicDTO = massagistInfoService.updateProfilePicture(id, profilePicture);
        return Result.success("技师头像更新成功！", massagistProfilePicDTO);
    }

    @ApiOperation(value = "新增技师头像")
    @PostMapping("/addProfilePicture")
    public Result<MassagistProfilePicDTO> addProfilePicture(@RequestParam Long id, @RequestParam String profilePicture) {
        MassagistProfilePicDTO massagistProfilePicDTO = massagistInfoService.addProfilePicture(id, profilePicture);
        return Result.success("技师头像新增成功", massagistProfilePicDTO);
    }
}
