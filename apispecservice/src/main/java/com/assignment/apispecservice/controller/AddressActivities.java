package com.assignment.apispecservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.apispecservice.dto.AddressDeleteDto;
import com.assignment.apispecservice.dto.AddressDto;
import com.assignment.apispecservice.dto.AddressFetchDto;
import com.assignment.apispecservice.dto.AddressUpdateDto;
import com.assignment.apispecservice.dto.ErrorDto;
import com.assignment.apispecservice.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
@Validated
@Tag(name = "Address API")
public class AddressActivities<T> {

	@Operation(summary = "Fetch Address details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetch the Address", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = AddressDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@GetMapping
	public ResponseEntity<T> getDetails( @Valid @RequestBody AddressFetchDto addressFetchDto)
			throws Exception {
		return null;
	}

	@Operation(summary = "Add address details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Add the address", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@PostMapping
	public ResponseEntity<T> addDetails(@Valid @RequestBody AddressDto productDto) throws Exception {
		return null;
	}

	@Operation(summary = "Update address details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update the address", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = AddressUpdateDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@PutMapping
	public ResponseEntity<T> updateDetails(@Valid @RequestBody AddressUpdateDto productUpdateDto) throws Exception {
		return null;
	}

	@Operation(summary = "Delete Address details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Delete the product", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@DeleteMapping
	public ResponseEntity<T> deleteDetails(@Valid @RequestBody AddressDeleteDto productFetchDto) throws Exception {
		return null;
	}
}
