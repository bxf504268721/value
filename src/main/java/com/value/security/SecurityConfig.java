package com.value.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // 允许所有用户访问
                .antMatchers("/login").permitAll()
                .antMatchers("/login/403", "/login/404", "/login/500").permitAll()
                // 其他路径访问需要权限验证
                .anyRequest().authenticated()
                .and()
                // 使用表单认证方式
                .formLogin()
                // 登录页
                .loginPage("/login")
                .defaultSuccessUrl("/login/index")
                .failureUrl("/login/500").permitAll()
                .and()
                .rememberMe()
                .and()
                .exceptionHandling().accessDeniedPage("/login/404")
                .and()
                .logout()
                // 登出成功后跳转
                .logoutSuccessUrl("/login");

        // 禁用默认的csrf验证
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 不拦截静态资源的访问
        web.ignoring().antMatchers("/js/**", "/css/**", "/img/**");
    }



    /**
     * 用户信息
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityUserDetails);
    }
}
