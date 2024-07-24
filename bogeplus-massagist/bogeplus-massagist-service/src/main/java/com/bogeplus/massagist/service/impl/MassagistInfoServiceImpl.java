package com.bogeplus.massagist.service.impl;

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
    public void saveOrUpdateProfilePicture(Integer id, String profilePicture) {
        MassagistInfo massagistInfo = getById(id);
        if (massagistInfo == null) {
            // 新增操作
            massagistInfo = new MassagistInfo();
            massagistInfo.setId(id);
            massagistInfo.setProfilePicture(profilePicture);
            save(massagistInfo);
        } else {
            // 修改操作
            massagistInfo.setProfilePicture(profilePicture);
            updateById(massagistInfo);
        }
    }
}
