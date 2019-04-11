package com.security.application.security;

import com.security.application.utils.JsonResultUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * JWT 生成和验签的类
 *
 * @author 三多
 * @Time 2019/3/25
 */
public class TokenAuthenticationService {
    //5天 毫秒
    static final long EXPIRATIONNTIME = 432000000;
    //jwt密码
    static final String SECRET = "Password@123";
    //Token 前缀
    static final String TOKEN_PREFIX = "Bearer";
    //存放Token的Header Key 授权token
    static final String HEADER_STRING = "Authorization";
    //存放权限和角色
    static final String AUTHORITIES = "authorities";

    /**
     * 生成JWT
     *
     * @param response
     * @param username
     */
    public static void addAuthentication(HttpServletResponse response, String username) {
        String jwt = Jwts.builder()
                //保存权限（角色）
                .claim(AUTHORITIES, "ROLE_ADMIN,AUTH_WRITE")
                //用户名写入标题
                .setSubject("username")
                //有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONNTIME))
                //签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        //将JWT写入body
        try {
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getOutputStream().println(JsonResultUtils.returnJsonString(0, "", jwt));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * JWT验证方法
     *
     * @param request
     * @return
     */
    public static Authentication getAuthentication(HttpServletRequest request) {
        //从header中获取token
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            // 解析token
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            //获取用户名
            String username = claims.getSubject();
            //获取权限和角色(逗号分隔)
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get(AUTHORITIES));
            //返回验证令牌
            return username != null ?
                    new UsernamePasswordAuthenticationToken(username, null, authorities) :
                    null;

        }
        return null;
    }
}
