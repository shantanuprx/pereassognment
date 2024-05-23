package com.assignment.apispecservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.apispecservice.dto.ErrorDto;
import com.assignment.apispecservice.dto.OrderFetchDto;
import com.assignment.apispecservice.dto.OrdersDto;
import com.assignment.apispecservice.dto.OrdersUpdateDto;
import com.assignment.apispecservice.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
@Validated
@Tag(name = "Orders API")
public class OrderActivities<T> {

	@Operation(summary = "Fetch order details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetch the order", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = OrdersDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@GetMapping
	public ResponseEntity<T> getDetails(@Valid @RequestBody OrderFetchDto orderFetchDto) throws Exception {
		return null;
	}

	@Operation(summary = "Add order details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Add the order", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@PostMapping
	public ResponseEntity<T> addDetails(@Valid @RequestBody OrdersDto ordersDto) throws Exception {
		return null;
	}

	@Operation(summary = "Update order details")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Update the order", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@PutMapping
	public ResponseEntity<T> updateDetails(@Valid @RequestBody OrdersUpdateDto ordersUpdateDto) throws Exception {
		return null;
	}
}
