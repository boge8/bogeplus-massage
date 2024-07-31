package com.bogeplus.massage.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.bogeplus.common.exception.BizException;
import com.bogeplus.common.util.UserUtil;
import com.bogeplus.massage.user.dto.UserAddressesDTO;
import com.bogeplus.massage.user.entity.UserAddresses;
import com.bogeplus.massage.user.mapper.UserAddressesMapper;
import com.bogeplus.massage.user.service.IUserAddressesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
    @Value(value = "${gaode.key}")
    private String key;
    @Value(value = "${gaode.url}")
    private String baseUrl;

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
        if(StrUtil.isBlank(UserUtil.getId())) {
            return null;
        }
        lambdaQueryWrapper.eq(UserAddresses::getUserId, UserUtil.getId());
        final List<UserAddresses> list = this.list(lambdaQueryWrapper);
        List<UserAddressesVO> addressList = new ArrayList<>();
        list.forEach(userAddress -> {
            UserAddressesVO userAddressesVO = new UserAddressesVO();
            BeanUtil.copyProperties(userAddress, userAddressesVO);
            addressList.add(userAddressesVO);
        });

        return addressList;
    }

    @Override
    public UserAddressesVO getDefaultUserAddress(long userId) {
        LambdaQueryWrapper<UserAddresses> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(UserAddresses::getUserId, userId);
        lambdaQueryWrapper.eq(UserAddresses::getIsDefault, true);
        UserAddresses userAddress = this.getOne(lambdaQueryWrapper);
        UserAddressesVO userAddressesVO = new UserAddressesVO();
        BeanUtil.copyProperties(userAddress, userAddressesVO);
        return userAddressesVO;
    }

    @Override
    public boolean setUserAddressDefault(long addressId) {
        UserAddresses userAddress = new UserAddresses();
        LambdaUpdateWrapper<UserAddresses> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(UserAddresses::getIsDefault, true);
        userAddress.setIsDefault(false);
        this.update(userAddress, lambdaUpdateWrapper);
        userAddress.setId(addressId);
        userAddress.setIsDefault(true);
        return this.updateById(userAddress);
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
            log.error("userAddress 根据地址调用高德获取GEO信息失败：" + url);
        }

        return null;
    }
}
