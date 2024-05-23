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

import com.assignment.apispecservice.dto.CardDetailsDeleteDto;
import com.assignment.apispecservice.dto.CardDetailsDto;
import com.assignment.apispecservice.dto.CardDetailsFetchDto;
import com.assignment.apispecservice.dto.CardDetailsUpdateDto;
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
@RequestMapping("/payment")
@Validated
@Tag(name = "Payment API - Debit/Credit specific")
public class CardDetailsActivities<T> {

	@Operation(summary = "Fetch Card details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetch the card details", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CardDetailsDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@GetMapping
	public ResponseEntity<T> getDetails(@Valid @RequestBody CardDetailsFetchDto cardDetailsFetchDto) throws Exception {
		return null;
	}

	@Operation(summary = "Add Card details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Add the card details", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@PostMapping
	public ResponseEntity<T> addDetails(@Valid @RequestBody CardDetailsDto cardDetailsDto) throws Exception {
		return null;
	}

	@Operation(summary = "Update Card details")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update the card details", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@PutMapping
	public ResponseEntity<T> updateDetails(@Valid @RequestBody CardDetailsUpdateDto cardDetailsUpdateDto) throws Exception {
		return null;
	}

	@Operation(summary = "Delete Card details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Delete the product", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@DeleteMapping
	public ResponseEntity<T> deleteDetails(@Valid @RequestBody CardDetailsDeleteDto cardDetailsDeleteDto) throws Exception {
		return null;
	}
}
