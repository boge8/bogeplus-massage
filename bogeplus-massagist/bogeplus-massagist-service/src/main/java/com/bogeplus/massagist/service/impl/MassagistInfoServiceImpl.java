package com.bogeplus.massagist.service.impl;

import com.bogeplus.massagist.dto.MassagistInfoDTO;
import com.bogeplus.massagist.entity.MassagistInfo;
import com.bogeplus.massagist.mapper.MassagistInfoMapper;
import com.bogeplus.massagist.service.IMassagistInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    /**
     * 新增或修改技师
     * @param massagistInfoDTO
     */
    @Override
    public void saveOrUpdate(MassagistInfoDTO massagistInfoDTO) {

        //将DTO对象转换为实体对象
        MassagistInfo massagistInfo = new MassagistInfo();
        BeanUtils.copyProperties(massagistInfoDTO, massagistInfo);

        //修改更新时间,更新者
        massagistInfo.setUpdateTime(LocalDateTime.now());
        massagistInfo.setUpdateUser("bogeplus");

        if (massagistInfo.getId() == null) {
            //新增创建时间，创建者
            massagistInfo.setCreateTime(LocalDateTime.now());
            massagistInfo.setCreateUser("bogeplus");
        }

        //保持或更新实体对象
        this.saveOrUpdate(massagistInfo);
    }
}
