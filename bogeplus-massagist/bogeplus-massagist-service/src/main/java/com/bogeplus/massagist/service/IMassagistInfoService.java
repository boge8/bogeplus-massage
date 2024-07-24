package com.bogeplus.massagist.service;

import com.bogeplus.massagist.dto.MassagistInfoDTO;
import com.bogeplus.massagist.entity.MassagistInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 技师表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
public interface IMassagistInfoService extends IService<MassagistInfo> {

    /**
     * 新增或修改技师
     * @param massagistInfoDTO
     */
    void saveOrUpdate(MassagistInfoDTO massagistInfoDTO);
}
