package org.example.user.service.fallback;

import org.example.entity.User;
import org.example.user.service.OrderService;
import org.springframework.stereotype.Service;

//容错类 需要实现feign 接口 及方法 远程调用失败  会进入当前类同名方法 执行容错逻辑
@Service
public class OrderServiceFallBack implements OrderService {
    @Override
    public User orderGet() {
        System.out.println("报错了");
        return null;
    }
}
