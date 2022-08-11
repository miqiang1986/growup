package com.miqiang.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.miqiang.gateway.util.JwtUtil;
import com.miqiang.gateway.util.RedisUtil;
import com.miqiang.gateway.util.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * @author miqiang
 * @version 1.0
 * @description
 * @createTime 2022-07-21  09:25
 */
@Order(-1)
@Component
@RefreshScope
@Slf4j
public class AuthrizeFilter implements GlobalFilter {

    @Value("${miqiang.globalUrls}")
    private String globalUrls;
    @Autowired
    private RedisUtil redisUtil;
    /** 登录用户Token令牌缓存KEY前缀 */
    public static final String PREFIX_USER_TOKEN  = "prefix_user_token_";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        URI uri = request.getURI();
        log.info("globalUrls is {}, uri path is {}", globalUrls, uri.getPath());
        if (StringUtils.isNotBlank(globalUrls)) {
            List<String> urls = Arrays.asList(globalUrls.split(","));
            if (urls.contains(uri.getPath())) {
                return chain.filter(exchange);
            }
        }
        HttpHeaders headers = request.getHeaders();
        List<String> tokens = headers.get("X-Access-Token");
        if (null != tokens && !tokens.isEmpty()) {
            String token = tokens.get(0);
            String username = JwtUtil.getUsername(token);
            Object obj = redisUtil.get(PREFIX_USER_TOKEN + username);
            if (null != obj) {
                UserVo userVo = JSONObject.parseObject(obj.toString(), UserVo.class);
                if (null != userVo) {
                    // 防止通过账号自己生成一个token
                    if (token.equals(userVo.getToken())) {
                        // todo 验证用户权限
                        // 放行
                        return chain.filter(exchange);
                    }
                }
            }
        }
        String url = uri.getHost()+":"+uri.getPort()+uri.getPath();
        log.info("没有token的请求拦截地址："+url);
        // 拦截 设置状态码
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }

}
