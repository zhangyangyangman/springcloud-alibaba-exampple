package org.example.user.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.example.user.service.ShopService;
import org.springframework.stereotype.Service;

@Service
public class ShopSergviceImpl implements ShopService {
    @Override
    @SentinelResource("message")
    public String message() {
        return "message";
    }
}
