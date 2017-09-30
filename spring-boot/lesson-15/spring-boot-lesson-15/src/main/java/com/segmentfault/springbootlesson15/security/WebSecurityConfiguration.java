package com.segmentfault.springbootlesson15.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.frameoptions.AllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.23
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //CSRF
        http.csrf().csrfTokenRepository(new CookieCsrfTokenRepository()).requireCsrfProtectionMatcher(
                httpServletRequest -> httpServletRequest.getMethod().equals("POST")
        );

        // CSP header
        http.headers().contentSecurityPolicy("script-src https://code.jquery.com/");

        // X-Frame-Options header
        // 相同域名是允许的
        // http.headers().frameOptions().sameOrigin();

        // 实现白名单方式
        http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(new AllowFromStrategy() {
            @Override
            public String getAllowFromValue(HttpServletRequest request) {
                return "xiaomage.com";
            }
        }));

        // XSS header
        http.headers().xssProtection().block(false);


        // 授权
        http.authorizeRequests().anyRequest().fullyAuthenticated()
                .and().
                formLogin().usernameParameter("name") // 用户名参数
                .passwordParameter("pwd") // 密码参数
                .loginProcessingUrl("/loginAction") // 登录 Action 的 URI
                .loginPage("/login") // 登录页面 URI
                .failureForwardUrl("/error") // 登录失败后的页面URI
                .permitAll()
                .and().logout().permitAll();

    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("xiaomage").password("123456").roles("ADMIN")
                .and().withUser("刘德华").password("123456").roles("USER");

    }
}
