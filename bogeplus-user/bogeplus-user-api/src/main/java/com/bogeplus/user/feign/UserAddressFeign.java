package com.bogeplus.user.feign;

import com.bogeplus.common.util.Result;
import com.bogeplus.massage.user.vo.AddressLocationVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

public interface UserAddressFeign {
    @GetMapping("/getAddressLocation")
    Result<AddressLocationVO> getAddressLocation(@NotNull(message = "地址ID不能为空") @RequestParam("addressId") Long addressId);
}
