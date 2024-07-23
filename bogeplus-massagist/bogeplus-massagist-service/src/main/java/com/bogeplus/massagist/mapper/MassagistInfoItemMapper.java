package com.bogeplus.massagist.mapper;

import com.bogeplus.massagist.entity.MassagistInfoItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bogeplus.massagist.vo.ItemVO;
import com.bogeplus.massagist.vo.MassagistVO;
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
    List<MassagistVO> getAssignedMassagistsByItemId(Long itemId);

    List<MassagistVO> getUnassignedMassagistsByItemId(Long itemId);

    List<ItemVO> getAssignedItemsByItemId(Long massagistId);

    List<ItemVO> getUnassignedItemsByItemId(Long massagistId);
}
