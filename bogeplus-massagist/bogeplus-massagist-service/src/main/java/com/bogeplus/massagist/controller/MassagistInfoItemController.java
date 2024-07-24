package com.bogeplus.massagist.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.dto.GetListDTO;
import com.bogeplus.massagist.dto.OperationDTO;
import com.bogeplus.massagist.service.MassagistInfoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 * <p>
 * 技师与按摩项目项目关系表 前端控制器
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Controller
@RequestMapping("/massagistInfoItem")
public class MassagistInfoItemController {

    @Autowired
    private MassagistInfoItemService massagistAssociationService;

    @GetMapping("/getList")
    public Result getList(@RequestParam GetListDTO dto){
        return massagistAssociationService.getList(dto);
    }

    @PostMapping("/changeAssignment")
    public Result changeAssignment(@RequestBody OperationDTO dto){
        return massagistAssociationService.ChangeAssignment(dto);
    }
}
