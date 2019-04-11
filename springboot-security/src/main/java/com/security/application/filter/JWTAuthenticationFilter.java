package com.security.application.filter;

import com.security.application.security.TokenAuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWTAuthenticationFilter  JWT认证服务
 * @author 三多
 * @Time 2019/3/23
 */
public class JWTAuthenticationFilter extends GenericFilterBean {
    /**
     * 拦截器拦截所有需要JWT的请求，然后调用TokenAuthenticationService的静态方法验证JWT
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res =(HttpServletResponse)response;
        Authentication authentication = TokenAuthenticationService.getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req,res);
    }
}
