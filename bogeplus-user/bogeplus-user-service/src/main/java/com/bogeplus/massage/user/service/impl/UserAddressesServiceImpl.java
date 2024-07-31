package com.bogeplus.massage.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bogeplus.common.constant.user.UserConstant;
import com.bogeplus.common.enums.ServiceCode;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.common.util.Result;
import com.bogeplus.common.util.UserUtil;
import com.bogeplus.massage.user.dto.UserAddressesDTO;
import com.bogeplus.massage.user.entity.UserAddresses;
import com.bogeplus.massage.user.mapper.UserAddressesMapper;
import com.bogeplus.massage.user.service.IUserAddressesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bogeplus.massage.user.vo.AddressLocationVO;
import com.bogeplus.massage.user.vo.DefaultAddressVO;
import com.bogeplus.massage.user.vo.UserAddressesVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 地址表 服务实现类
 * </p>
 *
 * @author bogeplus
 * @since 2024-07-26
 */
@Service
public class UserAddressesServiceImpl extends ServiceImpl<UserAddressesMapper, UserAddresses> implements IUserAddressesService {
    private final UserAddressesMapper userAddressesMapper;
    @Value(value = "${gaode.key}")
    private String key;
    @Value(value = "${gaode.url}")
    private String baseUrl;

    public UserAddressesServiceImpl(UserAddressesMapper userAddressesMapper) {
        this.userAddressesMapper = userAddressesMapper;
    }

    public boolean addUserAddress(UserAddressesDTO userAddressesDTO) {
        UserAddresses userAddresses = new UserAddresses();
        String userID = UserUtil.getId();
        if (StrUtil.isBlank(userID)) {
            throw new BizException("用户未登录");
        }

        userAddresses.setId(IdUtil.getSnowflakeNextId());
        userAddresses.setUserId(Long.parseLong(UserUtil.getId()));
        userAddresses.setAddressExtra(userAddressesDTO.getAddressExtra());
        userAddresses.setContactName(userAddressesDTO.getContactName());
        userAddresses.setContactPhone(userAddressesDTO.getContactPhone());
        Map<String, String> geoCode = getGeoCode(userAddressesDTO.getAddress());
        userAddresses.setAddress(geoCode.get("formattedAddress"));
        userAddresses.setLongitudeLatitude(geoCode.get("location"));
        userAddresses.setIsDefault(false);
        userAddresses.setCreateUser(userID);
        userAddresses.setUpdateUser(userID);
        userAddresses.setCreateTime(LocalDateTime.now());
        userAddresses.setUpdateTime(LocalDateTime.now());
        return this.save(userAddresses);
    }

    @Override
    public boolean updateUserAddress(long addressId, UserAddressesDTO userAddressesDTO) {

            UserAddresses userAddresses = this.getById(addressId);
            if (userAddresses != null) {
                userAddresses.setAddressExtra(userAddressesDTO.getAddressExtra());
                userAddresses.setContactName(userAddressesDTO.getContactName());
                userAddresses.setContactPhone(userAddressesDTO.getContactPhone());
                Map<String, String> geoCode = getGeoCode(userAddressesDTO.getAddress());
                userAddresses.setAddress(geoCode.get("formattedAddress"));
                userAddresses.setLongitudeLatitude(geoCode.get("location"));
                userAddresses.setUpdateUser(UserUtil.getId());
                userAddresses.setUpdateTime(LocalDateTime.now());
                return this.updateById(userAddresses);
            }
            else {
                throw new BizException("地址不存在");
            }
    }

    @Override
    public List<UserAddressesVO> getUserAddress() {
        LambdaQueryWrapper<UserAddresses> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserAddresses::getUserId, UserUtil.getId());
        final List<UserAddresses> list = this.list(lambdaQueryWrapper);
        List<UserAddressesVO> addressList = new ArrayList<>();
        list.forEach(userAddresses -> {
            UserAddressesVO userAddressesVO = new UserAddressesVO();
            BeanUtil.copyProperties(userAddresses, userAddressesVO);
            addressList.add(userAddressesVO);
        });

        return addressList;
    }

    @Override
    public boolean setUserAddressDefault(long addressId) {
        UserAddresses userAddresses = new UserAddresses();
        userAddresses.setId(addressId);
        userAddresses.setIsDefault(true);
        return this.updateById(userAddresses);
    }

    @Override
    public boolean setUserAddressNotDefault(long addressId) {
        UserAddresses userAddresses = new UserAddresses();
        userAddresses.setId(addressId);
        userAddresses.setIsDefault(false);
        return this.updateById(userAddresses);
    }

    /**
     * 获取默认地址
     * @return 响应体
     */
    @Override
    public Result<DefaultAddressVO> getDefaultAddress() {
        List<UserAddresses> defaultAddressList = userAddressesMapper.selectList(
                new LambdaQueryWrapper<UserAddresses>()
                        .eq(UserAddresses::getUserId, UserUtil.getId())
                        .eq(UserAddresses::getIsDefault, UserConstant.IS_DEFAULT));
        log.debug(UserUtil.getId());
        UserAddressesVO userAddressesVO = new UserAddressesVO();
        if (defaultAddressList.size() > 1) {
            throw new BizException("该用户设置了多个默认地址");
        }
        if (defaultAddressList.isEmpty()) {
            return Result.success(null); // 确保返回类型一致
        }
        BeanUtil.copyProperties(defaultAddressList.get(0), userAddressesVO);
        return Result.success(userAddressesVO);
    }



    // 高德地图获取地理编码
    public Map<String, String> getGeoCode(String address) {
        String url = baseUrl + "?address=" + address + "&key=" + key;

        // 创建 HttpClient 实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建 GET 请求
            HttpGet request = new HttpGet(url);

            // 执行请求
            HttpResponse response = httpClient.execute(request);

            // 获取并打印响应
            String responseBody = EntityUtils.toString(response.getEntity());
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                // Parse JSON string
                JsonNode rootNode = objectMapper.readTree(responseBody);
                JsonNode geocodesNode = rootNode.path("geocodes").get(0); // Get the first geocode

                // Retrieve formatted_address and location
                String formattedAddress = geocodesNode.path("formatted_address").asText();
                String location = geocodesNode.path("location").asText();

                Map<String, String> map = new HashMap<>();
                map.put("formattedAddress", formattedAddress);
                map.put("location", location);
                return map;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Result<AddressLocationVO> getAddressLocation(long addressId) {
        List<UserAddresses> addressList = list(new LambdaQueryWrapper<UserAddresses>().eq(UserAddresses::getId, addressId));
        if (addressList.isEmpty()) {
            throw new BizException("地址不存在");
        }
        if (addressList.size()>1) {
            Result.faild(ServiceCode.FAILED.getMsg(),ServiceCode.FAILED.getCode());
        }
        return Result.success(addressList.get(0));
    }
}
