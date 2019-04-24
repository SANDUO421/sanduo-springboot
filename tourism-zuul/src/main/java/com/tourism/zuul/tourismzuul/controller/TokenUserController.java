package com.tourism.zuul.tourismzuul.controller;

import com.google.code.kaptcha.Producer;
import com.tourism.zuul.tourismzuul.config.ApiResponse;
import com.tourism.zuul.tourismzuul.config.IpUtils;
import com.tourism.zuul.tourismzuul.config.SystemClientInfo;
import com.tourism.zuul.tourismzuul.service.TokenEndPointService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
@RestController
@Api(value = "用户登录", description = "用户退出")
public class TokenUserController {
    @Autowired
    HttpServletRequest request;
    @Autowired(required = false)
    private Producer producer;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired(required = false)
    private TokenEndPointService tokenEndPoint;
    @GetMapping("captcha.jpg")
    @ApiOperation(value = "用户验证码", notes = "通过验证码登录")
    public ApiResponse captcha(HttpServletResponse response)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        String ipAddress = IpUtils.getIpAddress(request);
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        redisTemplate.opsForValue().set(ipAddress,text);
        redisTemplate.expire(ipAddress, 50 , TimeUnit.SECONDS);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        byte[] bytes = baos.toByteArray();//转换成字节
        BASE64Encoder encoder = new BASE64Encoder();
        String png_base64 =  encoder.encodeBuffer(bytes).trim();//转换成base64串
        return ApiResponse.ofSuccess(png_base64);
    }
    @PostMapping("sys/login")
    @ApiOperation(value = "用户用户登录", notes = "登录接口")
    public ApiResponse login(@RequestParam("username")String username, @RequestParam("password")String password, @RequestParam("captcha")String captcha){
        Map<String, String> parameters = new HashMap<>();
        try { //验证码
            String ipAddress = IpUtils.getIpAddress(request);
            String kaptcha = (String)redisTemplate.opsForValue().get(ipAddress);
            if(!captcha.equalsIgnoreCase(kaptcha)){
                return ApiResponse.ofMessage(501, "验证码不正确");
            }
            Map<String, Object> tokenInfo = null;
            //用户名密码
            if (!StringUtils.isEmpty(username)&&!StringUtils.isEmpty(password)) {
                parameters.put(OAuth2Utils.GRANT_TYPE, "password");
                parameters.put(OAuth2Utils.CLIENT_ID, SystemClientInfo.CLIENT_ID);
                parameters.put("client_secret", SystemClientInfo.CLIENT_SECRET);
                parameters.put(OAuth2Utils.SCOPE, SystemClientInfo.CLIENT_SCOPE);
                parameters.put("password", password);
                parameters.put("username",username);
                tokenInfo = tokenEndPoint.postAccessToken(parameters);
                String access_token = (String)tokenInfo.get("access_token");
                redisTemplate.opsForValue().set(access_token,username);
                redisTemplate.expire(access_token, 30 ,TimeUnit.DAYS);
            }
            return ApiResponse.ofSuccess(tokenInfo);
        }catch (Exception e){
            e.printStackTrace();
            return ApiResponse.ofMessage(ApiResponse.Status.PASSERROR.getCode(),ApiResponse.Status.PASSERROR.getStandardMessage());
        }

}
        @PostMapping("/sys/refresh_token")
        public Map<String, Object> refresh_token(String refresh_token) {
            Map<String, String> parameters = new HashMap<>();
            parameters.put(OAuth2Utils.GRANT_TYPE, "refresh_token");
            parameters.put(OAuth2Utils.CLIENT_ID, SystemClientInfo.CLIENT_ID);
            parameters.put("client_secret", SystemClientInfo.CLIENT_SECRET);
            parameters.put(OAuth2Utils.SCOPE, SystemClientInfo.CLIENT_SCOPE);
            parameters.put("refresh_token", refresh_token);
            return tokenEndPoint.postAccessToken(parameters);
        }
    /**
     * 退出
     *
     * @param access_token
     */
    @GetMapping("/sys/logout")
    public ApiResponse logout(String access_token, @RequestHeader(required = false, value = "Authorization") String token) {
        if (StringUtils.isBlank(access_token)) {
            if (StringUtils.isNoneBlank(token)) {
                access_token = token.substring(OAuth2AccessToken.BEARER_TYPE.length() + 1);
            }
        }
            redisTemplate.delete(access_token);
          tokenEndPoint.removeToken(access_token);
        return ApiResponse.ofSuccess();
    }

}
