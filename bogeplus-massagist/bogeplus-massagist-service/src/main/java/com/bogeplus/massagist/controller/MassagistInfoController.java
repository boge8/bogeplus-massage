package com.bogeplus.massagist.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.dto.MassagistInfoDTO;
import com.bogeplus.massagist.service.IMassagistInfoService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 技师表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@RestController
@RequestMapping("/massagistInfo")
public class MassagistInfoController {

    @Autowired
    private IMassagistInfoService massagistInfoService;

    @ApiModelProperty(value = "新增或修改技师")
    @RequestMapping("/saveOrUpdate")
    public Result saveOrUpdateMassagist(@RequestBody MassagistInfoDTO massagistInfoDTO){
        massagistInfoService.saveOrUpdate(massagistInfoDTO);
        return Result.success();
    }

}
