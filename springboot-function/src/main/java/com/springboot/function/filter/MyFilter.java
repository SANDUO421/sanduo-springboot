package com.springboot.function.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义过滤器 只能在web应用中使用
 *  @WebFilter(filterName = "myFilter",urlPatterns = "/filter/*")
 *  必须配合 启类上 @ServletComponentScan 使用
 * @author 三多
 * @Time 2019/7/17
 */
//@WebFilter(filterName = "myFilter",urlPatterns = "/filter/*")
public class MyFilter implements Filter {

    /**
     * 初始
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter init ");
    }

    /**
     * 处理
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("MyFilter doFilter："+req.getParameter("name"));
        chain.doFilter(request, response);
        return ;

    }

    /**
     * 销毁
     */
    @Override
    public void destroy() {
        System.out.println("MyFilter destroy");
    }
}
