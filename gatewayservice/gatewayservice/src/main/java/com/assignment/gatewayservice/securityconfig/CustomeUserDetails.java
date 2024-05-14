package com.assignment.gatewayservice.securityconfig;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.assignment.gatewayservice.constants.GatewayServiceConstants;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CustomeUserDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5744426325971538001L;

	private String userName;

	private String password;

	private String userRole;

	private String status;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority(userRole));
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !GatewayServiceConstants.LOCKED_ACCOUNT_STATUS.equals(status);
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return GatewayServiceConstants.ACTIVE_FLAG.equals(status);
	}

}
