package org.example.user.service.fallback;

import feign.hystrix.FallbackFactory;
import org.example.user.service.OrderService;
import org.springframework.stereotype.Service;

//容错类 需要实现feign 接口 及方法 远程调用失败  会进入当前类同名方法 执行容错逻辑
@Service
public class OrderServiceFallBack1 implements FallbackFactory<OrderService> {
    @Override
    public OrderService create(Throwable cause) {


        return () -> {
            cause.printStackTrace();
            System.out.println("报错了------------");
            return null;
        };
    }
}
