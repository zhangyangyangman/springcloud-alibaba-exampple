package org.example.user.service;

import org.example.entity.User;
import org.example.user.service.fallback.OrderServiceFallBack1;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "order", fallback = OrderServiceFallBack.class, fallbackFactory = OrderServiceFallBack1.class)
@FeignClient(name = "order", fallbackFactory = OrderServiceFallBack1.class)
public interface OrderService {

    @RequestMapping("order/get")
    User orderGet();

}
