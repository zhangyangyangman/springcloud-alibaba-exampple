package org.example.user.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.User;
import org.example.user.service.OrderService;
import org.example.user.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class ShopController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrderService orderService;

    @Autowired
    ShopService shopService;

    @RequestMapping("/shop/msg")
//    @SentinelResource("msg")
    public String msg() {
        log.info("msg");
        shopService.message();
        return "msg";
    }

    @RequestMapping("/shop/msg1")
//    @SentinelResource("msg1")
    public String msg1() {
        log.info("msg1");
        shopService.message();
        return "msg1";
    }

    @RequestMapping("/shop/get")
//    @SentinelResource("get")
    public String getShop() {
        log.info("查询销售情况");


        //直接调用
//        RestTemplate restTemplate = con.getBean(RestTemplate.class);
//        User res = restTemplate.getForObject("http://localhost:8081/get", User.class);
//        System.out.println(JSONObject.toJSON(res));

        //直接调用
//        DiscoveryClient discoveryClient = con.getBean(DiscoveryClient.class);
//        List<ServiceInstance> order = discoveryClient.getInstances("order");
//        ServiceInstance in = order.get(0);
//        RestTemplate restTemplate = con.getBean(RestTemplate.class);
//        User res = restTemplate.getForObject("http://" + in.getHost() + ":" + in.getPort() + "/order/get", User.class);
//        System.out.println(JSONObject.toJSON(res));


        //手动随机
//        DiscoveryClient discoveryClient = con.getBean(DiscoveryClient.class);
//        List<ServiceInstance> instances = discoveryClient.getInstances("order");
//        ServiceInstance in = instances.get(new Random().nextInt(instances.size()));
//        RestTemplate restTemplate = con.getBean(RestTemplate.class);
//        User res = restTemplate.getForObject("http://" + in.getHost() + ":" + in.getPort() + "/order/get", User.class);
//        System.out.println(JSONObject.toJSON(res));


        //f负载均衡
//        User res = restTemplate.getForObject("http://order/order/get", User.class);
//        System.out.println(JSONObject.toJSON(res));


//        //feign
        User res = orderService.orderGet();
        System.out.println(JSONObject.toJSON(res));


        shopService.message();

        return "asda";

    }
}
