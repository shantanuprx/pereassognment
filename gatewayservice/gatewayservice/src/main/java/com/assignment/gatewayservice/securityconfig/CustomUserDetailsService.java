package com.assignment.gatewayservice.securityconfig;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.assignment.gatewayservice.entity.User;
import com.assignment.gatewayservice.repository.UserRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Optional<User> userEntity = userRepository.findByEmail(emailId);
		return userEntity
				.map(a -> new CustomeUserDetails(a.getEmail(), a.getPassword(), a.getUserRole(), a.getStatus()))
				.orElseThrow(() -> new UsernameNotFoundException("Email Id is not registered :" + emailId));
	}

}
