package com.security.application.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * 授权：存储权限类型和角色
 * @author 三多
 * @Time 2019/3/25
 */
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;

    public GrantedAuthorityImpl(String authority) {
        this.authority = authority;
    }
    public  void setAuthority(){
        this.authority = authority;
    }
    @Override
    public String getAuthority() {

        return this.authority;
    }
}
