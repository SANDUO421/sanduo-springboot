//package com.gateway.zuul.service;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import java.util.Map;
//@FeignClient(value = "auth2-center")
//public interface TokenEndPointService {
//    @PostMapping(path = "/oauth/token")
//    Map<String, Object> postAccessToken(@RequestParam Map<String, String> parameters);
//    /**
//     * 删除access_token和refresh_token<br>
//     * 认证中心的OAuth2Controller方法removeToken
//     *
//     * @param access_token
//     */
//    @DeleteMapping(path = "/remove_token")
//    void removeToken(@RequestParam("access_token") String access_token);
//}
