package com.miqiang.lixian.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author miqiang
 * @version 1.0
 * @description redisson配置
 * @createTime 2022-07-20  13:59
 */
@Configuration
public class RedssonConfig {

    @Value("${spring.redis.host}")
    private String addressUrl;
    @Value("${spring.redis.port}")
    private String addressPort;
    @Value("${spring.redis.password}")
    private String password;

    /**
     * @description 单机模式
     * @author miqiang
     * @createTime 2021/9/13 11:12
     * @updateTime 2021/9/13 11:12
     * @return: org.redisson.api.RedissonClient
     */
    @Bean
    public RedissonClient redisson() throws IOException {
        Config config = new Config();
        Codec codec = new JsonJacksonCodec();
        config.useSingleServer()
                .setAddress("redis://" + addressUrl + ":"+addressPort)
                .setPassword(password)
                .setRetryInterval(5000)
                .setTimeout(10000)
                .setConnectTimeout(10000);
        config.setNettyThreads(4);
        config.setThreads(4);
        config.setCodec(codec);
        return Redisson.create(config);
    }

    /**
     * @description 主从模式
     * @author miqiang
     * @createTime 2021/9/13 11:12
     * @updateTime 2021/9/13 11:12
     * @return: org.redisson.api.RedissonClient
     */
    /* @Bean
    public RedissonClient getRedisson() {
        Config config = new Config();
        config.useMasterSlaveServers()
                // 主服务器配置
                .setMasterAddress("redis://127.0.0.1:6379").setPassword(password)
                // 从服务器配置
                .addSlaveAddress("redis://127.0.0.1:6380").setPassword(password)
                .setRetryInterval(5000)
                .setTimeout(10000)
                .setConnectTimeout(10000);
        // 哨兵模式
//        config.useSentinelServers().setMasterName("myMaster").setPassword(password)
//                .addSentinelAddress("***(哨兵ip):26379","***(哨兵ip):26379")
//                .setRetryInterval(5000)
//                .setTimeout(10000)
//                .setConnectTimeout(10000);
        return Redisson.create(config);
    }
     */
}

