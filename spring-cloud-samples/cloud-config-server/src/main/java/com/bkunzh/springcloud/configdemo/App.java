package com.bkunzh.springcloud.configdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author bkunzh
 * @date 2020/10/8
 */
@SpringBootApplication
@EnableConfigServer
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
