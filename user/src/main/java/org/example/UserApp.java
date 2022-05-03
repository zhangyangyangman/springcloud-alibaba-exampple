package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserApp {
    public static void main(String[] args) {
        SpringApplication.run(UserApp.class, args);
        System.out.println("Hello World!");
    }
}
