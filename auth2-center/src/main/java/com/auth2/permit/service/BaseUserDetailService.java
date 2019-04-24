package com.auth2.permit.service;

import com.auth2.permit.client.RoleClientService;
import com.auth2.permit.client.UserClientService;
import com.auth2.permit.model.LoginAppUser;
import com.auth2.permit.model.RoleEntity;
import com.auth2.permit.model.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 *
 */
@Service
public  class BaseUserDetailService implements UserDetailsService {
    public static final String SUPPERADMIN ="supperAdmin";
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired(required = false)
    private UserClientService userClientService;
    @Autowired(required = false)
    private RoleClientService roleClientService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 返回带有用户权限信息的User
        List<RoleEntity> list=null;
        UserEntity data = userClientService.getUser(username).getData();
        LoginAppUser appUser = new LoginAppUser();
        if (data!=null) {
            if (data.getUsername().equals(SUPPERADMIN)){
                list=  new ArrayList<RoleEntity>();
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setRoleId(123l);
                roleEntity.setRoleName("admin");
                list.add(roleEntity);
                appUser.setEntity(data);
                appUser.setSysRoles(list);
            }else {
                list = roleClientService.getRoleByUserId(data.getUserId()).getData();
                appUser.setEntity(data);
                appUser.setSysRoles(list);
            }
        }else{
            logger.info("用戶不存在");
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        }
         return appUser;
    }





}
