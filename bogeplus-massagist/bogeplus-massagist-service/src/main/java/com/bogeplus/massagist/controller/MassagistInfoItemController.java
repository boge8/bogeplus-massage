package com.bogeplus.massagist.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.controller.requestBody.OperationRequest;
import com.bogeplus.massagist.service.MassagistInfoItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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
public class MassagistInfoItemController {

    @Autowired
    private MassagistInfoItemService massagistAssociationService;

    @GetMapping("/getList")
    @ApiOperation(value = "获取分配列表", notes = "获取分配列表描述")
    public Result getList(@RequestParam int type, @RequestParam int status, @RequestParam long objId){
        return massagistAssociationService.getList(type,status,objId);
    }

    @PostMapping("/changeAssignment")
    @ApiOperation(value = "修改分配关系", notes = "修改分配关系描述")
    public Result changeAssignment(@RequestBody OperationRequest request){
        return massagistAssociationService.changeAssignment(request);
    }
}
