package com.bogeplus.massagist.service.impl;

import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.massagist.entity.MassagistInfo;
import com.bogeplus.massagist.mapper.MassagistInfoMapper;
import com.bogeplus.massagist.service.IMassagistInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.massagist.dto.MassagistProfilePicDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 技师表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Service
public class MassagistInfoServiceImpl extends ServiceImpl<MassagistInfoMapper, MassagistInfo> implements IMassagistInfoService {

    @Override
    @Transactional
    public MassagistProfilePicDTO updateProfilePicture(Long id, String profilePicture) {
        MassagistInfo massagistInfo = getById(id);
        if (massagistInfo == null) {
            throw new BizException(ServiceCode.MASSAGIST_NOT_EXIST.getCode(),ServiceCode.MASSAGIST_NOT_EXIST.getMsg());
        }
        massagistInfo.setProfilePicture(profilePicture);
        updateById(massagistInfo);

        ////创建并返回DTO对象
        MassagistProfilePicDTO massagistProfilePicDTO = new MassagistProfilePicDTO();
        massagistProfilePicDTO.setId(massagistInfo.getId());
        massagistProfilePicDTO.setProfilePicture(massagistInfo.getProfilePicture());
        return massagistProfilePicDTO;
    }

    @Override
    @Transactional
    public MassagistProfilePicDTO addProfilePicture(Long id, String profilePicture) {
        MassagistInfo massagistInfo = getById(id);
        if (massagistInfo == null){
            throw new BizException(ServiceCode.MASSAGIST_NOT_EXIST.getCode(),ServiceCode.MASSAGIST_NOT_EXIST.getMsg());
        }
        if (massagistInfo.getProfilePicture() != null){
            throw new BizException(ServiceCode.MASSAGIST_PROFILE_PICTURE_ALREADY_EXIST.getCode(), ServiceCode.MASSAGIST_PROFILE_PICTURE_ALREADY_EXIST.getMsg());
        }
        massagistInfo.setProfilePicture(profilePicture);
        updateById(massagistInfo);

        // 创建并返回DTO对象
        MassagistProfilePicDTO massagistProfilePicDTO = new MassagistProfilePicDTO();
        massagistProfilePicDTO.setId(massagistInfo.getId());
        massagistProfilePicDTO.setProfilePicture(massagistInfo.getProfilePicture());
        return massagistProfilePicDTO;
    }
}
