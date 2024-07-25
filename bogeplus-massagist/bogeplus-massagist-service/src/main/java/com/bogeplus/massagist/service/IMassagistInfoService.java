package com.bogeplus.massagist.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.massagist.dto.MassagistInfoDTO;
import com.bogeplus.massagist.entity.MassagistInfo;

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
    void save(MassagistInfoDTO massagistInfoDTO);

    /**
     * 更新技师坐标
     * @param massagistInfoDTO
     * @return
     */
    void update(MassagistInfoDTO massagistInfoDTO) throws Exception;

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
   /* PageResult pageQuery(Integer page, Integer pageSize);*/

    /**
     * 根据id查询
     * @param id
     * @return
     */
    MassagistInfo getById(Long id);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Long id);
}
