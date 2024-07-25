package com.bogeplus.massagist.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.massagist.api.GaodeApiService;
import com.bogeplus.massagist.dto.MassagistInfoDTO;
import com.bogeplus.massagist.entity.MassagistInfo;
import com.bogeplus.massagist.mapper.MassagistInfoMapper;
import com.bogeplus.massagist.service.IMassagistInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
     * @param massagistInfoDTO
     */
    @Override
    public void save(MassagistInfoDTO massagistInfoDTO) {
        //将DTO对象转换为实体对象
        MassagistInfo massagistInfo = new MassagistInfo();
        BeanUtils.copyProperties(massagistInfoDTO, massagistInfo);

        //新增技师ID
        massagistInfo.setId(snowflake.nextId());

        //默认值设置: 未接种，评分0，服务数量0，免费里程0，点赞数量0，评论数量0
        massagistInfo.setVaccinated(false);
        massagistInfo.setRating(new BigDecimal(0));
        massagistInfo.setCompletedOrders(0);
        massagistInfo.setFreeMile(0);
        massagistInfo.setLikes(0);
        massagistInfo.setComment(0);

        //默认值设置: 未认证
        massagistInfo.setMassagistVerified(false);
        massagistInfo.setRealNameVerified(false);

        //新增创建时间，创建者
        massagistInfo.setCreateTime(LocalDateTime.now());
        massagistInfo.setCreateUser("bogeplus");

        //修改更新时间,更新者
        massagistInfo.setUpdateTime(LocalDateTime.now());
        massagistInfo.setUpdateUser("bogeplus");

        //保存或更新实体对象
        log.info("新增技师信息：{}", massagistInfo);
        massagistInfoMapper.insert(massagistInfo);
    }

    /**
     * 修改技师信息
     * @param massagistInfoDTO
     */
    public void update(MassagistInfoDTO massagistInfoDTO) throws Exception {
        //id不能为空
        if (massagistInfoDTO.getId() == null) {
            return;
        }

        //将DTO对象转换为实体对象
        MassagistInfo massagistInfo = new MassagistInfo();
        BeanUtils.copyProperties(massagistInfoDTO, massagistInfo);

        //判断技师接单地址是否为空
        if (massagistInfoDTO.getReceiveAddress() != null) {
            //通过接单地址获取技师经纬度坐标
            String coordinates = gaodeApiService.getCoordinates(massagistInfoDTO.getReceiveAddress());
            massagistInfo.setCoordinates(coordinates);
        }

        //修改更新时间,更新者
        massagistInfo.setUpdateTime(LocalDateTime.now());
        massagistInfo.setUpdateUser("bogeplus");

        //修改技师信息
        massagistInfoMapper.update(massagistInfo);
    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
    @Override
    public PageResult pageQuery(Integer page, Integer pageSize) {
        //分页开始
        PageHelper.startPage(page,pageSize);

        //分页查询
        List<MassagistInfo> massagistInfos = massagistInfoMapper.selectList(null);

        return (PageResult) PageResult.success(massagistInfos);

    }*/

    /**
     * 根据id查询技师
     * @param id
     * @return
     */
    @Override
    public MassagistInfo getById(Long id) {
        MassagistInfo massagistInfo = this.getById(id);
        return massagistInfo;
    }

    /**
     * 根据id删除技师
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        //逻辑删除
        MassagistInfo massagistInfo = new MassagistInfo();
        massagistInfo.setId(id);
        massagistInfo.setIsDeleted(true);

        //修改更新时间,更新者
        massagistInfo.setUpdateTime(LocalDateTime.now());
        massagistInfo.setUpdateUser("bogeplus");
        this.update(massagistInfo);
    }
}
