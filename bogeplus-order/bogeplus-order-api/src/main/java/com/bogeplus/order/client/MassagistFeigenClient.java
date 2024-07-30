package com.bogeplus.order.client;

import com.bogeplus.common.util.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


import java.math.BigDecimal;

@FeignClient(value = "bogeplus-massagist",path = "/massagistInfo")
public interface MassagistFeigenClient {

    @PutMapping("/evaluate/{id}/{sumRating}")
    public Result updateRatingById(@PathVariable("id") Long id,
                                   @PathVariable("sumRating") BigDecimal sumRating
    );
}
