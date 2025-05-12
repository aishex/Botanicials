package com.botanicials.Botanicials.config;

import com.botanicials.Botanicials.service.OAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

    private final OAuthService oAuthService;
    private final JwtFilter jwtFilter;

    public SecurityConfig(OAuthService oAuthService, JwtFilter jwtFilter){
        this.oAuthService = oAuthService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/**").permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(oAuthService)
                        )
                        .defaultSuccessUrl("/", true)
                );
        return http.build();
    }
}
