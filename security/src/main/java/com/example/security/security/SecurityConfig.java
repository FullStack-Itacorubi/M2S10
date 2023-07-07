package com.example.security.security;

import com.example.security.security.jwt.JwtFilter;
import com.example.security.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private UsersService service;

    private BasicAuthEntrypoint entrypoint;

    public SecurityConfig(UsersService service, BasicAuthEntrypoint entrypoint) {
        this.service = service;
        this.entrypoint = entrypoint;
    }

    @Bean
    public JwtFilter getFilter() {
        return new JwtFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .exceptionHandling(new Customizer<ExceptionHandlingConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(ExceptionHandlingConfigurer<HttpSecurity> httpSecurityExceptionHandlingConfigurer) {
                        httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(entrypoint);
                    }
                })
                .authorizeHttpRequests((r) -> {
                    r.requestMatchers(HttpMethod.POST, "/users/login").permitAll()
                            .requestMatchers(HttpMethod.GET, "/acesso").hasAuthority("READ")
                            .requestMatchers(HttpMethod.GET, "/acesso/2").hasRole("PERMISSAO_02");
                })
                //.httpBasic(Customizer.withDefaults());
                .addFilterBefore(
                        getFilter(),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }




    //VALIDAÇÃO DO MANAGER DE AUTENTICAÇÃO DO BASIC
    /*@Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder
                .userDetailsService(service)
                .passwordEncoder(new PasswordEncoder() {
                    @Override
                    public String encode(CharSequence rawPassword) {
                        return rawPassword.toString();
                    }

                    @Override
                    public boolean matches(CharSequence rawPassword, String encodedPassword) {
                        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
                    }
                });

    }*/


}
