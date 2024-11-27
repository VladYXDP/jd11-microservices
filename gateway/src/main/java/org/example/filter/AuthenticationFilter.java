package org.example.filter;

import org.apache.http.HttpHeaders;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private JwtUtils jwtUtils;

    @Autowired
    public AuthenticationFilter(JwtUtils jwtUtils) {
        super(Config.class);
        this.jwtUtils = jwtUtils;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                    jwtUtils.validate(authHeader);
                } else {
                    throw new RuntimeException("Invalid header auth token");
                }
            } else {
                throw new RuntimeException("Missing autherization header ...");
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}
