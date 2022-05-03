package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.springframework.cloud.gateway.support.GatewayToStringStyler.filterToStringCreator;

//@Component
public class ZyyGatewayFilterFactory
        extends AbstractGatewayFilterFactory<ZyyGatewayFilterFactory.Config> {

    /**
     * Parts key.
     */
    public static final String PARTS_KEY = "parts";

    public ZyyGatewayFilterFactory() {
        super(ZyyGatewayFilterFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(PARTS_KEY);
    }

    @Override
    public GatewayFilter apply(ZyyGatewayFilterFactory.Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();
//                addOriginalRequestUrl(exchange, request.getURI());
//                String path = request.getURI().getRawPath();
//                String newPath = "/"
//                        + Arrays.stream(StringUtils.tokenizeToStringArray(path, "/"))
//                        .skip(config.parts).collect(Collectors.joining("/"));
//                newPath += (newPath.length() > 1 && path.endsWith("/") ? "/" : "");
//                ServerHttpRequest newRequest = request.mutate().path(newPath).build();
//
//                exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR,
//                        newRequest.getURI());

                System.out.println(request.getURI());
//                return exchange.getResponse().setComplete();

                return chain.filter(exchange);
            }

            @Override
            public String toString() {
                return filterToStringCreator(ZyyGatewayFilterFactory.this)
                        .append("parts", config.getParts()).toString();
            }
        };
    }

    @Data
    @NoArgsConstructor
    public static class Config {

        private int parts;


    }
}
