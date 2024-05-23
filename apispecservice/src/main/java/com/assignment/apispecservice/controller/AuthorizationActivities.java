package com.assignment.apispecservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.apispecservice.dto.AuthenticationRequestDto;
import com.assignment.apispecservice.dto.AuthenticationResponseDto;
import com.assignment.apispecservice.dto.ErrorDto;
import com.assignment.apispecservice.dto.RegistrationDto;
import com.assignment.apispecservice.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
@Tag(name = "Authorization API")
public class AuthorizationActivities<T> {

	@Operation(summary = "Register new user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "New User registration", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@PostMapping("/register")
	public ResponseEntity<T> register(@Valid @RequestBody RegistrationDto registrationDto) throws Exception {
		return null;
	}
	
	@Operation(summary = "Generate new Token")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Generate new Token", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@PostMapping("/token")
	public ResponseEntity<T> generateToken(@Valid @RequestBody AuthenticationRequestDto authenticationRequestDto) throws Exception {
		return null;
	}

	
}
