package org.example.config;

import com.alibaba.csp.sentinel.adapter.servlet.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean customFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new CommonFilter());
        filter.addUrlPatterns("/*"); // /*不生效的话试试/**
        filter.addInitParameter(CommonFilter.WEB_CONTEXT_UNIFY, "false");
        filter.setOrder(1);
        filter.setName("sentinelFilter");
        return filter;
    }


}
