package com.auth2.permit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@TableName("oauth_client_details")
@Data
@Builder
public class OauthClientDetails implements Serializable {

    @TableId(type = IdType.INPUT)
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;


    private String webServerRedirectUri;

    private String authorities;


    private Integer accessTokenValidity;


    private Integer refreshTokenValidity;


    private String additionalInformation;


    private String autoapprove;

    private String name;

}
