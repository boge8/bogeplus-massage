package com.bogeplus.massagist.mapper;

import com.bogeplus.massagist.entity.MassagistInfo;
import com.bogeplus.massagist.entity.MassagistInfoItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bogeplus.massagist.vo.*;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 技师与按摩项目项目关系表 Mapper 接口
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Mapper
public interface MassagistInfoItemMapper extends BaseMapper<MassagistInfoItem> {

    //获取已分配技师列表
    List<MassagistVO> getAssignedMassagists(Long itemId);

    //获取未分配技师列表
    List<MassagistVO> getUnassignedMassagists(Long itemId);

    List<MassagistInfo> getMassagists(@Param("itemId") Long itemId, @Param("cityCode") String cityCode);

    //获取已分配项目列表
    List<ItemVO> getAssignedItems(Long massagistId);

    //获取未分配项目列表
    List<ItemVO> getUnassignedItems(Long massagistId);
}
