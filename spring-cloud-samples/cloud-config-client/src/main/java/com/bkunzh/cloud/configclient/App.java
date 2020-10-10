package com.bkunzh.cloud.configclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author bkunzh
 * @date 2020/10/8
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        // --spring.profiles.active=xx
        SpringApplication.run(App.class, args);
    }
}
