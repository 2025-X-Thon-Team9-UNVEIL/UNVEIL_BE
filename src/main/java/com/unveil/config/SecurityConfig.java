package com.unveil.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // CSRF 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                // session 사용 X
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // form login 비활성화
                .formLogin(AbstractHttpConfigurer::disable)
                // http Basic 인증 비활성화 (브라우저 기본 아이디/비밀번호 팝업 방지)
                .httpBasic(AbstractHttpConfigurer::disable)
                // URL별 권한 설정
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers(
                                "/api/login",
                                "/api/signup",
                                "/api/locations/**",
                                "/api/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/api-docs/**",
                                "/swagger-resources/**"
                        ).permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                
                );

        return http.build();
    }
}

