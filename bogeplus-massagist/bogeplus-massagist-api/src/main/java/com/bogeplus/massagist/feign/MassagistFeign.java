package com.bogeplus.massagist.feign;

import com.bogeplus.common.util.Result;
import com.bogeplus.massagist.vo.MassagistInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

@FeignClient(name = "bogeplus-massagist")
public interface MassagistFeign {
    @GetMapping("/massagistInfo/getById")
    Result<MassagistInfoVO> getById(@NotNull(message = "对象id不能为空") @RequestParam("id") Long id);
}
