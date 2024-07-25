package com.bogeplus.massagist.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.dto.MassagistInfoInsertDTO;
import com.bogeplus.massagist.dto.MassagistInfoUpdateDTO;
import com.bogeplus.massagist.service.IMassagistInfoService;
import com.bogeplus.massagist.vo.MassagistInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@Api(value = "技师信息", tags = "技师信息")
public class MassagistInfoController {

    @Autowired
    private IMassagistInfoService massagistInfoService;

    @ApiOperation(value = "新增技师")
    @PostMapping("/save")
    public Result save(@RequestBody MassagistInfoInsertDTO massagistInfoInsertDTO){
        massagistInfoService.save(massagistInfoInsertDTO);
        return Result.success();
    }

    @ApiOperation(value = "修改技师")
    @PostMapping("/update")
    public Result update(@RequestBody MassagistInfoUpdateDTO massagistInfoUpdateDTO) throws Exception {
        massagistInfoService.update(massagistInfoUpdateDTO);
        return Result.success();
    }

    /*@ApiOperation(value = "分页查询技师")
    @GetMapping("/page")
    public Result<IPage<MassagistInfoVO>> page(@RequestParam Integer page, @RequestParam Integer pageSize) {
        IPage<MassagistInfoVO> iPage = massagistInfoService.pageQuery(page,pageSize);
        return Result.success(iPage);
    }*/

    @ApiOperation(value = "根据id查询技师")
    @GetMapping("/getById")
    public Result<MassagistInfoVO> getById(Long id) {
        MassagistInfoVO massagistInfoVO = massagistInfoService.getById(id);
        return Result.success(massagistInfoVO);
    }

    @ApiOperation(value = "根据id删除技师")
    @DeleteMapping("/deleteById")
    public Result deleteById(Long id) {
        massagistInfoService.removeById(id);
        return Result.success();
    }
}
