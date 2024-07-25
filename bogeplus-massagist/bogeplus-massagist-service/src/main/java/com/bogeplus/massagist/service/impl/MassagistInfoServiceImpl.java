package com.bogeplus.massagist.service.impl;

import com.bogeplus.common.exception.BizException;
import com.bogeplus.massagist.entity.MassagistInfo;
import com.bogeplus.massagist.mapper.MassagistInfoMapper;
import com.bogeplus.massagist.service.IMassagistInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    public void updateProfilePicture(Integer id, String profilePicture) {
        MassagistInfo massagistInfo = getById(id);
        if (massagistInfo == null) {
            throw new BizException("技师信息不存在，无法更新头像");
        }
        massagistInfo.setProfilePicture(profilePicture);
        updateById(massagistInfo);
    }

    @Override
    @Transactional
    public void addProfilePicture(Integer id, String profilePicture) {
        MassagistInfo massagistInfo = getById(id);
        if (massagistInfo == null){
            throw new BizException("技师信息不存在，无法新增头像");
        }
        if (massagistInfo.getProfilePicture() != null){
            throw new BizException("技师头像已经存在，无法新增");
        }
        massagistInfo.setProfilePicture(profilePicture);
        updateById(massagistInfo);
    }
}
