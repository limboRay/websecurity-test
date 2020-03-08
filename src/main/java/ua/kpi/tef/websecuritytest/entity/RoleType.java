package ua.kpi.tef.websecuritytest.entity;


import org.springframework.security.core.GrantedAuthority;

public enum RoleType implements GrantedAuthority {
	ADMIN,
	USER;

	@Override
	public String getAuthority() {
		return null;
	}
}