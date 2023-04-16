package com.twlee.bank.common.config;

import com.twlee.bank.common.ui.resolver.AuthenticateArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticateArgumentResolver authenticateArgumentResolver;

    public WebConfig(AuthenticateArgumentResolver authenticateArgumentResolver) {
        this.authenticateArgumentResolver = authenticateArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(authenticateArgumentResolver);
    }
}
