package com.bogeplus.massagist.mapper;


import com.bogeplus.massagist.dto.AssignmentDTO;
import com.bogeplus.massagist.dto.CancelAssignmentDTO;
import com.bogeplus.massagist.entity.MassagistInfoItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bogeplus.massagist.vo.CheckItemsVO;
import com.bogeplus.massagist.vo.CheckMassagistsVO;
import com.bogeplus.massagist.vo.ItemVO;
import com.bogeplus.massagist.vo.MassagistVO;
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

    //获取已分配项目列表
    List<ItemVO> getAssignedItems(Long massagistId);

    //获取未分配项目列表
    List<ItemVO> getUnassignedItems(Long massagistId);
}
