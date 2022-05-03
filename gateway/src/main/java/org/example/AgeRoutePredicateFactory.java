package org.example;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.List;
import java.util.function.Predicate;

@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {


    public AgeRoutePredicateFactory() {
        super(Config.class);
    }

    /**
     * Returns hints about the number of args and the order for shortcut parsing.
     *
     * @return the list of hints
     */
//    读取参数 复制到配置类
    @Override
    public List<String> shortcutFieldOrder() {
        //顺序必须跟配置文件中一致
        return Lists.newArrayList("minAge", "maxAge");
    }


    /**
     * @param config
     * @return
     */
    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return serverWebExchange -> {
            String ageValue = serverWebExchange.getRequest().getQueryParams().getFirst("age");
            if (StringUtils.isNotEmpty(ageValue)) {
                int age = Integer.parseInt(ageValue);
                System.out.println(age < config.getMaxAge() && age > config.getMinAge());
//                return age < config.getMaxAge() && age > config.getMinAge();
                return age < config.getMaxAge() && age > config.getMinAge();
            }
            return false;
        };
    }


    @Validated
    @Data
    @NoArgsConstructor
    public static class Config {
        private int minAge;
        private int maxAge;

    }
}
