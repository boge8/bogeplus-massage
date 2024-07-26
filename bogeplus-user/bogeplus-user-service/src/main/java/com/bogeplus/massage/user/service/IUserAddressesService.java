package com.bogeplus.massage.user.service;

import com.bogeplus.massage.user.dto.UserAddressesDTO;
import com.bogeplus.massage.user.entity.UserAddresses;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bogeplus.massage.user.vo.UserAddressesVO;

import java.util.List;

/**
 * <p>
 * 地址表 服务类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-26
 */
public interface IUserAddressesService extends IService<UserAddresses> {

    boolean addUserAddress(UserAddressesDTO userAddressesDTO);
    boolean updateUserAddress(long addressId, UserAddressesDTO userAddressesDTO);
    List<UserAddressesVO> getUserAddress();
    boolean setUserAddressDefault(long addressId);
    boolean setUserAddressNotDefault(long addressId);
}
