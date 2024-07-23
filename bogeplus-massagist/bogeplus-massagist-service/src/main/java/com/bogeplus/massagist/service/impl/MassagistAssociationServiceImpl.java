package com.bogeplus.massagist.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.bogeplus.common.enums.MassagistEnums.RedisKeyPrefix;
import com.bogeplus.common.util.RedisUtil;
import com.bogeplus.common.util.Result;

import com.bogeplus.massagist.mapper.MassagistInfoItemMapper;
import com.bogeplus.massagist.service.MassagistAssociationService;
import com.bogeplus.massagist.vo.ItemVO;
import com.bogeplus.massagist.vo.MassagistVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author bin
 * @Date 2021/10/8 15:00
 * @Description 实现技师与项目关系的分配和取消分配
 */
@Service
public class MassagistAssociationServiceImpl implements MassagistAssociationService {

    @Autowired
    private MassagistInfoItemMapper massagistInfoItemMapper;

    //查询该项目已被分配的技师
    @Override
    public Result getAssignedMassagistsByItemId(Long itemId) {
        //参数校验
        if (ObjectUtil.isNull(itemId)) {
            //这里之后封装到枚举类
            return Result.faild("技师不存在", 500);
        }
        //初始化集合
        List<MassagistVO> assignedMassagists;
        //如果redis中有数据则查redis
        Object massagists_json = RedisUtil.get(RedisKeyPrefix.ASSIGNED_MASSAGISTS + itemId.toString());
        //redis中有数据则走redis
        if (ObjectUtil.isNotNull(massagists_json)) {
            JSONArray massagist_jsonArray = JSONUtil.parseArray(massagists_json);
            assignedMassagists = JSONUtil.toList(massagist_jsonArray, MassagistVO.class);
        } else {
            //如果redis中没有数据则查数据库
            assignedMassagists = massagistInfoItemMapper.getAssignedMassagistsByItemId(itemId);
            if (ObjectUtil.isNotNull(assignedMassagists)) {
                //更新redis缓存
                RedisUtil.set(RedisKeyPrefix.ASSIGNED_MASSAGISTS + itemId.toString(), JSONUtil.toJsonStr(assignedMassagists));
            }
        }

        return Result.success(assignedMassagists);
    }

    //查询该项目未被分配到的技师
    @Override
    public Result getUnassignedMassagistsByItemId(Long itemId) {
        //参数校验
        if (ObjectUtil.isNull(itemId)) {
            //这里之后封装到枚举类
            return Result.faild("技师不存在", 500);
        }
        //初始化集合
        List<MassagistVO> unassignedMassagists;
        Object massagists_json = RedisUtil.get(RedisKeyPrefix.UNASSIGNED_MASSAGISTS + itemId.toString());
        if (ObjectUtil.isNotNull(massagists_json)) {
            JSONArray massagist_jsonArray = JSONUtil.parseArray(massagists_json);
            unassignedMassagists = JSONUtil.toList(massagist_jsonArray, MassagistVO.class);
        } else {
            unassignedMassagists = massagistInfoItemMapper.getUnassignedMassagistsByItemId(itemId);
            if (ObjectUtil.isNotNull(unassignedMassagists)) {
                //更新redis缓存
                RedisUtil.set(RedisKeyPrefix.UNASSIGNED_MASSAGISTS + itemId.toString(), JSONUtil.toJsonStr(unassignedMassagists));
            }
        }
        return Result.success(unassignedMassagists);
    }

    //查询该技师已被分配的项目
    @Override
    public Result getAssignedItemsByMassagistId(Long massagistId) {
        //参数校验
        if (ObjectUtil.isNull(massagistId)) {
            //这里之后封装到枚举类
            return Result.faild("服务不存在", 500);
        }
        List<ItemVO> assignedItems;
        Object items_json = RedisUtil.get(RedisKeyPrefix.ASSIGNED_ITEMS + massagistId.toString());
        if (ObjectUtil.isNotNull(items_json)) {
            JSONArray item_jsonArray = JSONUtil.parseArray(items_json);
            assignedItems = JSONUtil.toList(item_jsonArray, ItemVO.class);
        } else {
            assignedItems = massagistInfoItemMapper.getUnassignedItemsByItemId(massagistId);
            if (ObjectUtil.isNotNull(assignedItems)) {
                //更新redis缓存
                RedisUtil.set(RedisKeyPrefix.ASSIGNED_ITEMS + massagistId.toString(), JSONUtil.toJsonStr(assignedItems));
            }
        }
        return Result.success(assignedItems);
    }

    //查询该技师未被分配到项目
    @Override
    public Result getUnassignedItemsByMassagistId(Long massagistId) {
        //参数校验
        if (ObjectUtil.isNull(massagistId)) {
            //这里之后封装到枚举类
            return Result.faild("服务不存在", 500);
        }
        List<ItemVO> unassignedItems;
        Object items_json = RedisUtil.get(RedisKeyPrefix.UNASSIGNED_ITEMS + massagistId.toString());
        if (ObjectUtil.isNotNull(items_json)) {
            JSONArray item_jsonArray = JSONUtil.parseArray(items_json);
            unassignedItems = JSONUtil.toList(item_jsonArray, ItemVO.class);
        } else {
            unassignedItems = massagistInfoItemMapper.getUnassignedItemsByItemId(massagistId);
            if (ObjectUtil.isNotNull(unassignedItems)) {
                //更新redis缓存
                RedisUtil.set(RedisKeyPrefix.UNASSIGNED_ITEMS + massagistId.toString(), JSONUtil.toJsonStr(unassignedItems));
            }
        }
        return Result.success(unassignedItems);
    }

    //取消技师与项目分配关系
    @Override
    public Result cancelAssign(Long massagistId, Long itemId) {
        return null;
    }

    //建立技师与项目分配关系
    @Override
    public Result assign(Long massagistId, Long itemId) {
        return null;
    }

}
