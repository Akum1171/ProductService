package com.scaler.ProductService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration // means that this class containing beans also you can do @component but for best practices will use @configuration for redable
public class RestTemplateConfig
{
    @Bean
    public RestTemplate createBean(){
        return new RestTemplate();
    }
}
/*
 we are telling to spring, create bean of this conifguration calls and store into it inside spring container, so that we can reuse whenever required.

 */


/*@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}*/
