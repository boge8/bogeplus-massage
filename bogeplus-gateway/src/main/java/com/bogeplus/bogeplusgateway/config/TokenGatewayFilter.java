package com.bogeplus.bogeplusgateway.config;

import com.bogeplus.bogeplusgateway.util.JWTUtil;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
@Slf4j
@Component
public class TokenGatewayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取token
      String token =   exchange.getRequest().getHeaders().getFirst("Authorization");
        log.info("TokenGatewayFilter:15 进入全局过滤器:{}",token);

        // 解析Token
        if(null !=token && !"".equals(token)) {
            try {
                String userJsonString = JWTUtil.parseToken(token);
                return  chain.filter(exchange.mutate().request(exchange.getRequest().mutate().header("x-forward", userJsonString).build()).build());
            } catch (Exception e) {
                log.info("TokenGatewayFilter:25 解析Token异常:{}",token);
            }
        }

        // 如果有Token且解析正确，就讲他们提取出来转为json放到request中，向后面的微服务路由
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
