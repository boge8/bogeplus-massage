package com.bogeplus.activity.feign;

import com.bogeplus.activity.vo.CouponsVO;
import com.bogeplus.common.util.Result;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public interface ActivityFeign {

    @GetMapping("/getAvailable")
    Result<List<CouponsVO>> getAvailable();

    @GetMapping("/getUsed")
    Result<List<CouponsVO>> getUsed();

    @GetMapping("/getExpired")
    Result<List<CouponsVO>> getExpired();
}
