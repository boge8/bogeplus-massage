package com.bogeplus.massagist.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.dto.MassagistInfoDTO;
import com.bogeplus.massagist.entity.MassagistInfo;
import com.bogeplus.massagist.service.IMassagistInfoService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        massagistInfoService.save(massagistInfoDTO);
        return Result.success();
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
        massagistInfoService.update(massagistInfoDTO);
        return Result.success();
    }

    /**
     * 分页查询技师
     *
     * @return
     */
    /*@ApiModelProperty(value = "分页查询技师")
    @GetMapping("/page")
    public Result<PageResult> page(@RequestParam Integer page, @RequestParam Integer pageSize) {
        PageResult pageResult = massagistInfoService.pageQuery(page,pageSize);
        return Result.success(pageResult);
    }*/

    /**
     * 根据id查询技师
     * @param id
     */
    @ApiModelProperty(value = "根据id查询技师")
    @PostMapping("/getById")
    public Result<MassagistInfo> getById(Long id) {
        MassagistInfo massagistInfo = massagistInfoService.getById(id);
        return Result.success(massagistInfo);
    }

    /**
     * 根据id删除技师
     * @param id
     */
    @ApiModelProperty(value = "根据id删除技师")
    @PostMapping("/removeById")
    public Result removeById(Long id) {
        massagistInfoService.deleteById(id);
        return Result.success();
    }
}
