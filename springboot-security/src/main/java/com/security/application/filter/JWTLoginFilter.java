package com.security.application.filter;

import com.alibaba.fastjson.JSONObject;
import com.security.application.entity.AccountCredentials;
import com.security.application.security.TokenAuthenticationService;
import com.security.application.utils.JsonResultUtils;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义JWTLoginFilter
 *
 * @author 三多
 * @Time 2019/3/22
 */
//TODO 1、待完成
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager manager) {
        //路径匹配
        super(new AntPathRequestMatcher(url));
        //设置认证管理器
        setAuthenticationManager(manager);
    }

    /**
     * 登录验证时调用
     *
     * @param req
     * @param res
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
        //JSON反序列化获得 AccountCredentials（认证的相关信息）
        AccountCredentials creds = JSONObject.parseObject(req.getInputStream(), AccountCredentials.class);
       //返回一个验证令牌
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword())
        );
    }

    /**
     * 验证成功后调用
     *
     * @param request
     * @param response
     * @param chain
     * @param auth
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        //super.successfulAuthentication(request, response, chain, authResult);

        TokenAuthenticationService.addAuthentication(response,auth.getName());
        }

    /**
     * 验证失败后调用，
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //super.unsuccessfulAuthentication(request, response, failed);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        response.getOutputStream().println(JsonResultUtils.returnJsonString(500,"Internal Server Error！",null));
    }

}
