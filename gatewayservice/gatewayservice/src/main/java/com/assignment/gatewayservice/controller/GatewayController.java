package com.assignment.gatewayservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.gatewayservice.constants.GatewayServiceConstants;
import com.assignment.gatewayservice.dto.AuthenticationRequestDto;
import com.assignment.gatewayservice.dto.ErrorDto;
import com.assignment.gatewayservice.dto.RegistrationDto;
import com.assignment.gatewayservice.security.service.AuthorizationService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
/**
 * Main controller to handle user registration and token generation call.
 * @param <T>
 */
@RestController
@RequestMapping("/auth")
@Validated
@Slf4j
public class GatewayController<T> {

	@Autowired
	private AuthorizationService<T> authorizationService;

	@Autowired
	private AuthenticationManager authenticationManager;

	/**
	 * Method to register new users
	 * 
	 * @param registrationDto
	 * @return response of registration
	 * @throws Exception
	 */
	@PostMapping("/register")
	public ResponseEntity<T> registerUser(@Valid @RequestBody RegistrationDto registrationDto) throws Exception {
		return authorizationService.createNewUser(registrationDto);
	}

	/**
	 * 
	 * @param authenticationRequestDto
	 * @return newly generated token if authentication is successful
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/token")
	public ResponseEntity<T> generateToken(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) {
		log.debug("Entering generateToken method for {}", authenticationRequestDto.getEmailId());
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequestDto.getEmailId(), authenticationRequestDto.getPassword()));
			if (authentication.isAuthenticated()) {
				String jwtToken = authorizationService.generateToken(authenticationRequestDto.getEmailId());
				authorizationService.saveUserSession(jwtToken);
				return ResponseEntity.status(HttpStatus.OK).body((T) Map.of(GatewayServiceConstants.TOKEN, jwtToken, "status", "200"));
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body((T) new ErrorDto(HttpStatus.UNAUTHORIZED,
						GatewayServiceConstants.INVALID_CREDS, null, System.currentTimeMillis()));
			}
		} catch (LockedException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
					(T) new ErrorDto(HttpStatus.UNAUTHORIZED, GatewayServiceConstants.ACCOUNT_LOCKED, null, System.currentTimeMillis()));
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
					(T) new ErrorDto(HttpStatus.UNAUTHORIZED, GatewayServiceConstants.INVALID_USER_NAME_OR_PASSWORD, null, System.currentTimeMillis()));
		} catch (Exception ex) {
			log.error("Exception occurred while validating the user {} with exception {}",
					authenticationRequestDto.getEmailId(), ex);
			throw ex;
		} finally {
			log.debug("Entering generateToken method for {}", authenticationRequestDto.getEmailId());
		}
	}

}
