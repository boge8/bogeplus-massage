package com.bogeplus.massagist.controller;

import com.bogeplus.common.util.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    //查找该技师未被分配的项目
    @GetMapping("/getUnassignedItems")
    public Result getUnassignedItemsByMassagistId() {
        return null;
    }

    //查找该项目未被分配的技师
    @GetMapping("/getUnassignedMassagists")
    public Result getUnassignedMassagistsByItemId() {
        return null;
    }

    //查询已被分配技师的项目
    @GetMapping("/getAssignedItems")
    public Result getUnassignedItems() {
        return null;
    }

    //查询已被分配项目的技师
    @GetMapping("/getAssignedMassagists")
    public Result getUnassignedMassagists() {
        return null;
    }

    //取消分配被选中的项目
    @DeleteMapping("/cancelAssignItems")
    public Result cancelAssignItems() {
        return null;
    }

    //取消分配已被选中的技师
    @DeleteMapping("/cancelAssignMassagists")
    public Result cancelAssignMassagists() {
        return null;
    }

    //为该项目添加技师
    @PostMapping("/assignMassagists")
    public Result assignMassagists() {
        return null;
    }

    //为该技师分配项目
    @PostMapping("/assignItems")
    public Result assignItems() {
        return null;
    }
}
