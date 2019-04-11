package com.security.application.config;

import com.security.application.filter.JWTAuthenticationFilter;
import com.security.application.filter.JWTLoginFilter;
import com.security.application.security.CustomAuthenticationProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * springSecurity 配置类
 * @author 三多
 * @Time 2019/3/22
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //关闭csrf验证
        http.csrf().disable()
                //对请求进行认证
                .authorizeRequests()
                //所有 "/" 请求都放行
                .antMatchers("/").permitAll()
                //所有的 /login post 请求都放行
                .antMatchers(HttpMethod.POST,"/login").permitAll()
                //权限检查
                .antMatchers(HttpMethod.GET,"/hello").hasAuthority("AUTH_WRITE")
                //角色检查
                .antMatchers(HttpMethod.GET,"/world").hasRole("ADMIN")
                //所有的请求都需要认证
                .anyRequest().authenticated()
                .and()
                //添加一个过滤器，所有的 /login 访问请求交给 JWTLoginFIlter来处理，这个类处理所有的JWT相关的内容
                .addFilterBefore(new JWTLoginFilter("/login",authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                //添加一个过滤器验证其他请求的Token是否合法
                .addFilterBefore(new JWTAuthenticationFilter(),UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //使用自定义身份验证组件
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }
}
