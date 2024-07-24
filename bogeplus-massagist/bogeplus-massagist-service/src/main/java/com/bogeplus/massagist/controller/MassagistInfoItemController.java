package com.bogeplus.massagist.controller;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.dto.AssignmentRequestDTO;
import com.bogeplus.massagist.service.MassagistAssociationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

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
    private MassagistAssociationService massagistAssociationService;

    @GetMapping("/getList")
    public Result getList(@RequestParam int type, @RequestParam int status,@RequestParam long id){
        return massagistAssociationService.getList(type,id,status);
    }

    @PostMapping("/changeAssignment")
    public Result changeAssignment(@RequestBody AssignmentRequestDTO dto){
        return massagistAssociationService.ChangeAssignment(dto.getOperation(),dto.getType(),dto.getObjId(),dto.getObjIdList());
    }
}
