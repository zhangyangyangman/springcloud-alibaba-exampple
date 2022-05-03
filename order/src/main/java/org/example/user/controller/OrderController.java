package org.example.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

    @Value("${server.port}")
    private String serrverPort;

    @RequestMapping("get")
    public User getOrders() {
        log.info("查询订单" + serrverPort);
        System.out.println(Thread.currentThread().getId());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new User();
    }
}
