package com.bogeplus.user.feign;

import com.bogeplus.common.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "bogeplus-user")
public interface UserAddressFeign {
    @GetMapping("/userAddresses/getDefaultUserAddress")
    Result getDefaultUserAddress(@RequestParam("userId") long userId);
}