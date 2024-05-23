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

import com.assignment.apispecservice.dto.ErrorDto;
import com.assignment.apispecservice.dto.ProductDeleteDto;
import com.assignment.apispecservice.dto.ProductDto;
import com.assignment.apispecservice.dto.ProductFetchDto;
import com.assignment.apispecservice.dto.ProductUpdateDto;
import com.assignment.apispecservice.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@Validated
@Tag(name = "Products API")
public class ProductActivities<T> {

	@Operation(summary = "Fetch product details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Fetch the product", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@GetMapping
	public ResponseEntity<T> getDetails(@Valid @RequestBody ProductFetchDto productFetchDto)
			throws Exception {
		return null;
	}

	@Operation(summary = "Add product details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Add the product", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@PostMapping
	public ResponseEntity<T> addDetails(@Valid @RequestBody ProductDto productDto) throws Exception {
		return null;
	}

	@Operation(summary = "Update product details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Update the product", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ProductUpdateDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@PutMapping
	public ResponseEntity<T> updateDetails(@Valid @RequestBody ProductUpdateDto productUpdateDto) throws Exception {
		return null;
	}

	@Operation(summary = "Delete product details")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Delete the product", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDto.class)) }), })
	@DeleteMapping
	public ResponseEntity<T> deleteDetails(@Valid @RequestBody ProductDeleteDto productFetchDto) throws Exception {
		return null;
	}
}
