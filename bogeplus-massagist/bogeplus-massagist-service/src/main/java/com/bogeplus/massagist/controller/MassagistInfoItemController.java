package com.bogeplus.massagist.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.controller.requestBody.OperationRequest;
import com.bogeplus.massagist.service.MassagistInfoItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * <p>
 * 技师与按摩项目项目关系表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@RestController
@Slf4j
@Api(tags = "技师项目分配",value = "技师项目分配相关接口" )
@RequestMapping("/massagistInfoItem")
@Validated
public class MassagistInfoItemController {

    @Autowired
    private MassagistInfoItemService massagistAssociationService;

    @GetMapping("/getAssignedList")
    @ApiOperation(value = "获取已分配列表", notes = "获取已分配列表描述")
    public Result<?> getAssignedList(@Max(value = 2, message = "传入类型参数错误")
                                     @Min(value = 1, message = "传入类型参数错误")
                                     @NotNull(message = "类型不能为空")
                                     @RequestParam Integer type,
                                     @NotNull(message = "对象id不能为空")
                                     @RequestParam Long objId) {
        return massagistAssociationService.getAssignedList(type, objId);
    }

    @GetMapping("/getUnassignedList")
    @ApiOperation(value = "获取未分配列表", notes = "获取已分配列表描述")
    public Result<?> getUnassignedList(@Max(value = 2, message = "传入类型参数错误")
                                       @Min(value = 1, message = "传入类型参数错误")
                                       @NotNull(message = "类型不能为空")
                                       @RequestParam Integer type,
                                       @NotNull(message = "对象id不能为空")
                                       @RequestParam Long objId) {
        return massagistAssociationService.getUnassignedList(type, objId);
    }

    @PostMapping("/addAssignment")
    @ApiOperation(value = "建立分配关系", notes = "修改分配关系描述")
    public Result addAssignment(@Valid @RequestBody OperationRequest request){
        return massagistAssociationService.addAssignment(request);
    }

    @PostMapping("/cancelAssignment")
    @ApiOperation(value = "删除分配关系", notes = "修改分配关系描述")
    public Result cancelAssignment(@Valid @RequestBody OperationRequest request){
        return massagistAssociationService.cancelAssignment(request);
    }
}
