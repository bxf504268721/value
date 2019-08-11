package com.value.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
* @Description security权限控制
* @Author bxf
* @Date   2019/8/8
*/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserDetails securityUserDetails;

    /**
     * 用来配置拦截保护的请求，比如什么请求放行，什么请求需要验证
     * @param http 安全请求对象
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 允许所有用户访问
                .antMatchers("/login/403", "/login/404", "/login/500").permitAll()
                // 其他路径访问需要权限验证
                .anyRequest().authenticated()
                .and()
                // 使用表单认证方式
                .formLogin()
                // 需要校验登录至此页面
                .loginPage("/login")
                .defaultSuccessUrl("/login/index")
                .failureUrl("/login/500").permitAll()
                .and()
                // Cookie以键"remember-me-key"进行保存 60s
                .rememberMe().tokenValiditySeconds(60).key("remember-me-key")
                .and()
                .exceptionHandling().accessDeniedPage("/login/404")
                .and()
                .logout()
                // 登出成功后跳转
                .logoutSuccessUrl("/login")
                .deleteCookies("remember-me-key");

        // 禁用默认的csrf验证
        http.csrf().disable();
    }

    /**
     * 用来配置Fillter链
     * @param web Spring Web Security对象
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 不拦截静态资源的访问
        web.ignoring().antMatchers("/js/**", "/css/**", "/img/**");
    }

    /**
     * 用来配置用户签名服务，主要是user-details机制，你还可以给与用户赋予角色
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetails).passwordEncoder(passwordEncoder());
    }

    /**
     * 密码编码器
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
