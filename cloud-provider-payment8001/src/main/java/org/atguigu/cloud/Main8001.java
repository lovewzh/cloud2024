package org.atguigu.cloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("org.atguigu.cloud.mapper")
@EnableDiscoveryClient
@RefreshScope//consul动态刷新
@Slf4j
public class Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main8001.class,args);
        log.info("cloud-provide-payment8001:start");
        log.info("swagger文档：http://localhost:8001/swagger-ui/index.html");
        log.info("consul:cmd->consul agent -dev");
    }
}