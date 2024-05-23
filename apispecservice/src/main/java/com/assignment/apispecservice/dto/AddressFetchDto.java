package com.assignment.apispecservice.dto;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class AddressFetchDto {
	
	@Schema(example = "1")
	@NotNull(message = "Address id should not be blank")
	@Min(0)
	private Integer recordId;
	
	@NotBlank
	@Schema(example = "JWT Token from auth service")
	private String token;
}
