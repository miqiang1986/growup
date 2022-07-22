package com.miqiang.lixian;

import com.miqiang.lixian.util.ConvertUtils;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.miqiang.**.mapper")
public class LixianApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(LixianApplication.class, args);
        Environment env = application.getEnvironment();
//        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = ConvertUtils.getString(env.getProperty("server.servlet.context-path"));
        log.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://127.0.0.1:" + port + path + "/\n\t" +
                "Swagger文档: \thttp://127.0.0.1:" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
    }

}
