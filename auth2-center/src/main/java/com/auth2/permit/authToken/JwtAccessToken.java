package com.auth2.permit.authToken;
import com.api.common.utils.ConstantUtils;
import com.api.common.utils.JsonUtils;
import com.auth2.permit.model.LoginAppUser;
import com.auth2.permit.model.UserEntity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import java.util.Map;
/**
 * @库琦
 * 自定义JwtAccessToken转换器
 */
public class JwtAccessToken extends JwtAccessTokenConverter {

    /**
     * 生成token
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        // 设置额外用户信息
        UserEntity baseUser = ((LoginAppUser) authentication.getPrincipal()).getEntity();
        baseUser.setPassword(null);
        // 将用户信息添加到token额外信息中
        defaultOAuth2AccessToken.getAdditionalInformation().put(ConstantUtils.USER_INFO, baseUser);
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }
    /**
     * 解析token
     * @param value
     * @param map
     * @return
     */
    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map){
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
        return oauth2AccessToken;
    }
    private void convertData(OAuth2AccessToken accessToken, Map<String, ?> map) {
        accessToken.getAdditionalInformation().put(ConstantUtils.USER_INFO,convertUserData(map.get(ConstantUtils.USER_INFO)));
    }
    private UserEntity convertUserData(Object map) {
        String json = JsonUtils.deserializer(map);
        UserEntity user = JsonUtils.serializable(json, UserEntity.class);
        return user;
    }
}
