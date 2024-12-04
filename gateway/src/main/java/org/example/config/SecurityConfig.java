//package org.example.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
//import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//import reactor.core.publisher.Flux;
//
//@Configuration
//public class SecurityConfig {
//
//    @Value("${}")
//    private String ISSUER;
//
//    @Bean
//    public SecurityWebFilterChain createFilterChain(ServerHttpSecurity http) {
//        http.csrf().disable()
//                .authorizeExchange()
//                .anyExchange().authenticated()
//                .and()
//                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
//                        .jwt(jwtSpec -> jwtSpec.jwtAuthenticationConverter(createReactiveJwtAuthenticationConverter())));
//        return http.build();
//    }
//
//    @Bean
//    public ReactiveJwtAuthenticationConverter createReactiveJwtAuthenticationConverter() {
//        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        ReactiveJwtAuthenticationConverter reactiveJwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();
//        reactiveJwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> Flux.fromIterable(jwtGrantedAuthoritiesConverter.convert(jwt)));
//        return reactiveJwtAuthenticationConverter;
//    }
//
//    @Bean
//    public ReactiveJwtDecoder createReactiveJwtDecoder() {
//        return NimbusReactiveJwtDecoder.withJwkSetUri(ISSUER).build();
//    }
//}
