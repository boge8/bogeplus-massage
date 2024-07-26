package com.bogeplus.massagist.service;

import com.bogeplus.massagist.entity.MassagistInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.massagist.dto.MassagistProfilePicDTO;

/**
 * <p>
 * 技师表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
public interface IMassagistInfoService extends IService<MassagistInfo> {

    MassagistProfilePicDTO updateProfilePicture(Long id, String profilePicture);
    MassagistProfilePicDTO addProfilePicture(Long id, String profilePicture);

}
