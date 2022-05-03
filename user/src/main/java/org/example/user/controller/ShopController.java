package org.example.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("user")
public class ShopController {

    @RequestMapping("get")
    public String getShop() {
        log.info("查询销售情况");
        return "asda";

    }
}
