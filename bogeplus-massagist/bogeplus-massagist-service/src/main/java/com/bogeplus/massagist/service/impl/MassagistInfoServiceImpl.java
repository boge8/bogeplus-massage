package com.bogeplus.massagist.service.impl;

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
@Slf4j
@Service
public class MassagistInfoServiceImpl extends ServiceImpl<MassagistInfoMapper, MassagistInfo> implements IMassagistInfoService {

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
            massagistInfo.setCoordinates(coordinates);
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
        IPage<MassagistInfoVO> massagistInfoVOPage = new Page<MassagistInfoVO>(page, pageSize);


        return ;
    }


    /**
     * 根据id查询技师
     * @param id
     * @return
     */
    @Override
    public MassagistInfoVO getById(Long id) {
        MassagistInfo massagistInfo = massagistInfoMapper.selectById(id);
        MassagistInfoVO massagistInfoVO = new MassagistInfoVO();
        BeanUtils.copyProperties(massagistInfo, massagistInfoVO);
        return massagistInfoVO;
    }
}
