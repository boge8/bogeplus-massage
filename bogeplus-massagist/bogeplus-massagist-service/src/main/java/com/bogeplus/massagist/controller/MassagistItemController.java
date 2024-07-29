package com.bogeplus.massagist.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bogeplus.common.util.PageResult;
import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.dto.MassagistItemPageDto;
import com.bogeplus.massagist.dto.MassagistItemPutDto;
import com.bogeplus.massagist.entity.MassagistItem;
import com.bogeplus.massagist.service.IMassagistItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static com.bogeplus.common.enums.ServiceCode.MASSAGIST_ITEM_NOT_EXIST;

/**
 * <p>
 * 按摩项目表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@RestController
@Validated
@Api(value = "按摩项目信息接口",tags = "按摩项目信息接口")
@RequestMapping("/massagistItem")
public class MassagistItemController {

    @Resource
    private IMassagistItemService massagistItemService;

    @ApiOperation(value = "通过项目id进行查询",notes = "根据项目id进行项目查询")
    @GetMapping("/{id}")
    public Result getItemById(@ApiParam(value = "项目id")@PathVariable("id") Long id){
        if (id == null){
            return Result.faild(MASSAGIST_ITEM_NOT_EXIST.getMsg(),MASSAGIST_ITEM_NOT_EXIST.getCode());
        }
        MassagistItem item = massagistItemService.getById(id);
        return Result.success(item);
    }

    @ApiOperation(value = "项目分页查询",notes = "项目分页查询")
    @GetMapping("")
    public Result listByPageItem(@Valid @ApiParam(value = "项目分页实体类")@RequestBody MassagistItemPageDto massagistItemPageDto){
        Integer pageSize = massagistItemPageDto.getPageSize();
        Integer pageNo = massagistItemPageDto.getPageNo();
        Page<MassagistItem> page = new Page<>(pageNo, pageSize);
        Page<MassagistItem> resultPageItem = massagistItemService.page(page);
        return Result.success(resultPageItem);
    }

    @ApiOperation(value = "项目新增",notes = "项目新增")
    @PostMapping("")
    public Result addItem(@Valid @ApiParam(value = "项目新增实体类")@RequestBody MassagistItem massagistItem){
        boolean saved = massagistItemService.save(massagistItem);
        return saved ? Result.success("项目新增成功") : Result.faild("项目新增失败");
    }

    @ApiOperation(value = "项目修改",notes = "项目修改")
    @PutMapping("")
    public Result updateItem(@Valid@ApiParam(value = "项目id")@RequestBody MassagistItemPutDto massagistItemPutDto){
        MassagistItem massagistItem = new MassagistItem();
        BeanUtils.copyProperties(massagistItemPutDto,massagistItem);
        boolean updated = massagistItemService.updateById(massagistItem);
        return updated ? Result.success("项目修改成功") : Result.faild("项目修改失败");
    }

    @ApiOperation(value = "删除项目(逻辑删除)",notes = "删除项目(逻辑删除)")
    @DeleteMapping("/{id}")
    public Result delItem(@ApiParam(value = "项目id")@PathVariable("id") Long id){
        boolean removed = massagistItemService.removeById(id);
        return removed ? Result.success("项目修改删除成功") : Result.faild("项目修改删除失败");
    }

}
