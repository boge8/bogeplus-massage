package com.bogeplus.massagist.controller;

import com.bogeplus.common.util.PageResult;
import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.dto.MassagistInfoDTO;
import com.bogeplus.massagist.entity.MassagistInfo;
import com.bogeplus.massagist.service.IMassagistInfoService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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

    /**
     * 新增技师
     * @param massagistInfoDTO
     * @return
     */
    @ApiModelProperty(value = "新增技师")
    @PostMapping("/save")
    public Result saveMassagist(@RequestBody MassagistInfoDTO massagistInfoDTO){
        return massagistInfoService.save(massagistInfoDTO);
    }

    /**
     * 修改技师
     * @param massagistInfoDTO
     * @return
     * @throws Exception
     */
    @ApiModelProperty(value = "修改技师")
    @PostMapping("/update")
    public Result update(@RequestBody MassagistInfoDTO massagistInfoDTO) throws Exception {
        return massagistInfoService.update(massagistInfoDTO);
    }


}
