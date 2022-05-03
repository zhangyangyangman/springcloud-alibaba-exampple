package org.example;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.DispatcherHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

//@Component
public class Zyy1GlobalFilter implements GlobalFilter, Ordered {

    private static final Log log = LogFactory.getLog(Zyy1GlobalFilter.class);

    private final ObjectProvider<DispatcherHandler> dispatcherHandlerProvider;

    // do not use this dispatcherHandler directly, use getDispatcherHandler() instead.
    private volatile DispatcherHandler dispatcherHandler;

    public Zyy1GlobalFilter(ObjectProvider<DispatcherHandler> dispatcherHandlerProvider) {
        this.dispatcherHandlerProvider = dispatcherHandlerProvider;
    }

    private DispatcherHandler getDispatcherHandler() {
        if (dispatcherHandler == null) {
            dispatcherHandler = dispatcherHandlerProvider.getIfAvailable();
        }

        return dispatcherHandler;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI requestUrl = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR);

//        String scheme = requestUrl.getScheme();
//        if (isAlreadyRouted(exchange) || !"forward".equals(scheme)) {
//            return chain.filter(exchange);
//        }
//
//        // TODO: translate url?
//
//        if (log.isTraceEnabled()) {
//            log.trace("Forwarding to URI: " + requestUrl);
//        }

        System.out.println(requestUrl + "========");

        return chain.filter(exchange);
    }

}
