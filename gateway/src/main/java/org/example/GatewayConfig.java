package org.example;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Configuration
public class GatewayConfig {
    private List<ViewResolver> viewResolverList;
    private ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfig(ObjectProvider<List<ViewResolver>> objectProvider, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolverList = objectProvider.getIfAvailable();
        this.serverCodecConfigurer = serverCodecConfigurer;

    }

    //初始化限流器
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    //初始化限流参数
    @PostConstruct
    public void initGatewayParam() {
        Set<GatewayFlowRule> flowRules = Sets.newHashSet();
        flowRules.add(new GatewayFlowRule("shop") //对应路由ID
                .setCount(1) //限流阈值
                .setIntervalSec(1));//统计时间窗口 单位秒 默认1秒
        GatewayRuleManager.loadRules(flowRules);
    }

    //自定义异常处理类
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionhandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolverList, serverCodecConfigurer);
    }

    //自定义异常错误页面
    @PostConstruct
    public void initBlockHandlers() {

        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange exchange, Throwable t) {
                return ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject("限流了"));
            }
        };

        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }


}
