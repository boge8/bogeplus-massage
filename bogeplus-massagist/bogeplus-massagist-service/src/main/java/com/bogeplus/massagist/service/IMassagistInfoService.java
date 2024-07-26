package com.bogeplus.massagist.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.massagist.dto.MassagistProfilePicDTO;
import com.bogeplus.massagist.dto.MassagistInfoInsertDTO;
import com.bogeplus.massagist.dto.MassagistInfoUpdateDTO;
import com.bogeplus.massagist.entity.MassagistInfo;
import com.bogeplus.massagist.vo.MassagistInfoVO;

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

    /**
     * 新增技师
     * @param massagistInfoInsertDTO
     */
    void save(MassagistInfoInsertDTO massagistInfoInsertDTO);

    /**
     * 更新技师坐标
     * @param massagistInfoUpdateDTO
     * @return
     */
    void update(MassagistInfoUpdateDTO massagistInfoUpdateDTO) throws Exception;

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    IPage<MassagistInfoVO> pageQuery(Integer page, Integer pageSize);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    MassagistInfoVO getById(Long id);

}
