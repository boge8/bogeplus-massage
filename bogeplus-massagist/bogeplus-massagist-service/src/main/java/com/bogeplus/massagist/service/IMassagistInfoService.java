package com.bogeplus.massagist.service;

import com.bogeplus.common.util.Result;
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
     * 新增技师
     * @param massagistInfoDTO
     */
    Result save(MassagistInfoDTO massagistInfoDTO);

    /**
     * 更新技师坐标
     * @param massagistInfoDTO
     * @return
     */
    Result update(MassagistInfoDTO massagistInfoDTO) throws Exception;
}
