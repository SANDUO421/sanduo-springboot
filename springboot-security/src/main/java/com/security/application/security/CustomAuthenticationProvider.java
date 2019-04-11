package com.security.application.security;

import com.security.application.entity.GrantedAuthorityImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;

/**
 * 提供密码验证功能
 * @author 三多
 * @Time 2019/3/24
 */
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("获取用户名密码");
        //获取认证的用户名和密码
        String username = authentication.getName();
        System.out.println("principal:"+ authentication.getPrincipal().toString());
        String password = authentication.getCredentials().toString();

        //认证逻辑:从数据库查询用户名密码
        if ("admin".equals(username) && "admin".equals(password)){

            //设置权限和角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
            authorities.add(new GrantedAuthorityImpl("ROLE_WRITE"));
            //生成令牌
            Authentication auth = new UsernamePasswordAuthenticationToken(username,password,authorities);
            return  auth;

        }else {
            throw new BadCredentialsException("密码有误");
        }
    }

    /**
     * 提供输入类型的认证服务
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
