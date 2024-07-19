package com.bogeplus.massage.user.mapper;

import com.bogeplus.massage.user.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 上门按摩用户信息表 Mapper 接口
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-16
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {
    public UserInfo getById(Long id);
}
