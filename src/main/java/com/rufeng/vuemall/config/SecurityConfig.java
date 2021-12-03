package com.rufeng.vuemall.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rufeng.vuemall.common.CommonResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * SpringSecurity的配置
 *
 * @author rufeng
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    public SecurityConfig(AccessDeniedHandler accessDeniedHandler,
                          AuthenticationEntryPoint authenticationEntryPoint) {
        this.accessDeniedHandler = accessDeniedHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        /* 使用HttpServlet，没有包装需求 */
        httpSecurity.servletApi().disable();
        /*登入登出在Controller中处理*/
        httpSecurity.logout().disable();
        /* 请求头不需要额外操作 */
        httpSecurity.headers().disable();
        /*重定向认证后重新处理请求*/
        httpSecurity.requestCache().disable();
        httpSecurity.sessionManagement().disable();
        /* 不配置自动禁用 */
        /*httpSecurity.formLogin();*/
        /*httpSecurity.rememberMe();*/
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);
        httpSecurity.authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .antMatchers(HttpMethod.OPTIONS).permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Component
    public static class MyAccessDeniedHandler implements AccessDeniedHandler {
        private final ObjectMapper objectMapper;

        public MyAccessDeniedHandler(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
            response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            response.setContentType(MediaType.APPLICATION_JSON.toString());
            response.getWriter().println(objectMapper.writeValueAsString(
                    CommonResponse.unAuthorized(accessDeniedException.getMessage())));
            response.getWriter().flush();
        }
    }

    @Component
    static class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

        private final ObjectMapper objectMapper;

        public MyAuthenticationEntryPoint(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
            response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
            response.setContentType(MediaType.APPLICATION_JSON.toString());
            response.getWriter().println(objectMapper.writeValueAsString(
                    CommonResponse.authenticationFailed(authException.getMessage())));
            response.getWriter().flush();
        }
    }

}
