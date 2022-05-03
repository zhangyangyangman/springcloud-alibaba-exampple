package org.example;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.google.common.collect.Sets;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class ApiGroupGatewayConfig {

    //初始化限流参数
    @PostConstruct
    public void initGatewayRules() {
        Set<GatewayFlowRule> flowRules = Sets.newHashSet();
        flowRules.add(new GatewayFlowRule("shop") //对应路由ID
                .setCount(1) //限流阈值
                .setIntervalSec(1));//统计时间窗口 单位秒 默认1秒

        flowRules.add(new GatewayFlowRule("order") //对应路由ID
                .setCount(2) //限流阈值
                .setIntervalSec(1));//统计时间窗口 单位秒 默认1秒
        GatewayRuleManager.loadRules(flowRules);
    }


    //初始化分株
    @PostConstruct
    public void initApiGroup() {

        Set<ApiDefinition> apiDefinitionSet = Sets.newHashSet();
        apiDefinitionSet.add(new ApiDefinition("shop").setPredicateItems(new HashSet<ApiPredicateItem>() {{
            add(new ApiPathPredicateItem().setPattern("/shop1/shop/**").setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
        }}));

        apiDefinitionSet.add(new ApiDefinition("order").setPredicateItems(new HashSet<ApiPredicateItem>() {{
            add(new ApiPathPredicateItem().setPattern("/order1/order/get"));
        }}));
        GatewayApiDefinitionManager.loadApiDefinitions(apiDefinitionSet);

    }


}
