package com.bogeplus.massagist.service.impl;

import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.exception.BizException;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.common.util.UserUtil;
import com.bogeplus.massagist.api.GaodeApiService;
import com.bogeplus.massagist.dto.MassagistInfoInsertDTO;
import com.bogeplus.massagist.dto.MassagistInfoUpdateDTO;
import com.bogeplus.massagist.entity.MassagistInfo;
import com.bogeplus.massagist.mapper.MassagistInfoMapper;
import com.bogeplus.massagist.service.IMassagistInfoService;
import com.bogeplus.massagist.vo.MassagistInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.massagist.dto.MassagistProfilePicDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 * 技师表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-22
 */
@Slf4j
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
    private static long workerId = 0;
    private static long datacenterId = 1;
    private Snowflake snowflake = IdUtil.createSnowflake(workerId, datacenterId);

    @Autowired
    private GaodeApiService gaodeApiService;
    @Autowired
    private MassagistInfoMapper massagistInfoMapper;

    /**
     * 新增技师信息
     * @param massagistInfoInsertDTO
     */
    @Override
    public void save(MassagistInfoInsertDTO massagistInfoInsertDTO) {
        //将DTO对象转换为实体对象
        MassagistInfo massagistInfo = new MassagistInfo();
        BeanUtils.copyProperties(massagistInfoInsertDTO, massagistInfo);

        //新增技师ID
        massagistInfo.setId(snowflake.nextId());

        //新增创建时间，创建者
        massagistInfo.setCreateTime(LocalDateTime.now());
        massagistInfo.setCreateUser(UserUtil.getAccount());
        System.out.println(UserUtil.getAccount());

        //修改更新时间,更新者
        massagistInfo.setUpdateTime(LocalDateTime.now());
        massagistInfo.setUpdateUser(UserUtil.getAccount());

        //保存或更新实体对象
        log.info("新增技师信息：{}", massagistInfo);
        massagistInfoMapper.insert(massagistInfo);
    }

    /**
     * 修改技师信息
     * @param massagistInfoUpdateDTO
     * @throws Exception
     */
    @Override
    public void update(MassagistInfoUpdateDTO massagistInfoUpdateDTO) throws Exception {
        //将DTO对象转换为实体对象
        MassagistInfo massagistInfo = new MassagistInfo();
        BeanUtils.copyProperties(massagistInfoUpdateDTO, massagistInfo);

        //判断技师接单地址是否为空
        if (massagistInfo.getReceiveAddress() != null) {
            //通过接单地址获取技师经纬度坐标
            String coordinates = gaodeApiService.getCoordinates(massagistInfo.getReceiveAddress());
            massagistInfo.setLongtitudeLatitude(coordinates);
        }

        //修改更新时间,更新者
        massagistInfo.setUpdateTime(LocalDateTime.now());
        massagistInfo.setUpdateUser(UserUtil.getAccount());

        //修改技师信息
        massagistInfoMapper.updateById(massagistInfo);
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public IPage<MassagistInfoVO> pageQuery(Integer page, Integer pageSize) {
        IPage<MassagistInfo> massagistInfoPage = new Page<MassagistInfo>(page, pageSize);

        massagistInfoMapper.selectPage(massagistInfoPage, null);

        IPage<MassagistInfoVO> iPage = massagistInfoPage.convert(massagistInfo -> {
            MassagistInfoVO massagistInfoVO = new MassagistInfoVO();
            BeanUtils.copyProperties(massagistInfo, massagistInfoVO);
            return massagistInfoVO;
        });

        return iPage;
    }


    /**
     * 根据id查询技师
     * @param id
     * @return
     */
    @Override
    public MassagistInfoVO selectById(Long id) {
        MassagistInfo massagistInfo = massagistInfoMapper.selectById(id);
        MassagistInfoVO massagistInfoVO = new MassagistInfoVO();
        BeanUtils.copyProperties(massagistInfo, massagistInfoVO);
        return massagistInfoVO;
    }
}
