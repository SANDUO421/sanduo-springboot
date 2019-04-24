package com.auth2.permit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * spring security当前登录对象
 */
@Getter
@Setter
public class LoginAppUser  implements UserDetails {
	private static final long serialVersionUID = 1753977564987556640L;
	private UserEntity entity;
	private List<RoleEntity> sysRoles;
	private Set<String> permissions;
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new HashSet<>();
		if (!CollectionUtils.isEmpty(sysRoles)) {
			sysRoles.forEach(role -> {
				String roleId = role.getRoleId()+"";
				if (roleId.startsWith("ROLE_")) {
					collection.add(new SimpleGrantedAuthority(roleId));
				} else {
					collection.add(new SimpleGrantedAuthority("ROLE_" + roleId));
				}
			});
		}
		if (!CollectionUtils.isEmpty(permissions)) {
			permissions.forEach(per -> {
				collection.add(new SimpleGrantedAuthority(per));
			});
		}

		return collection;
	}

	@Override
	public String getPassword() {
		return entity.getPassword();
	}

	@Override
	public String getUsername() {
		return entity.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
