package com.bogeplus.massagist.service;

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

    void saveOrUpdateProfilePicture(Integer id, String profilePicture);

}