package com.bogeplus.user.feign;

import com.bogeplus.common.util.Result;
import com.bogeplus.massage.user.vo.AddressLocationVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@Component
@FeignClient(value = "bogeplus-user")
public interface UserAddressFeign {
    @GetMapping("/userAddresses/getAddressLocation")
    Result<AddressLocationVO> getAddressLocation(@NotNull(message = "地址ID不能为空") @RequestParam("addressId") Long addressId);
}
