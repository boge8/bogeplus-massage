package com.bogeplus.user.feign;

import com.bogeplus.massage.user.vo.UserAddressesVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bogeplus-user")
public interface UserAddressFeign {
    @GetMapping("/userAddresses/getDefaultUserAddress")
    UserAddressesVO getDefaultUserAddress(@RequestParam("userId") long userId);
}