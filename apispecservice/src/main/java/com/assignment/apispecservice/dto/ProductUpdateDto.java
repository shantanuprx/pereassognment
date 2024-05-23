package com.assignment.apispecservice.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ProductUpdateDto extends BaseDto {

	@Min(1)
	@NotNull
	@Schema(example = "1")
	private Integer productId;

	@Size(max = 100)
	@Schema(example = "7800xt 16 GB")
	private String productName;

	@Size(max = 1000)
	@Schema(example = "Graphic Card")
	private String productDescription;

	@Schema(example = "10")
	private int currentStock;

	@Size(max = 1, min = 1)
	@Schema(example = "O", allowableValues = { "O - Out Of stock",
			"A - Available, U - unavailable" })
	private String status;

	@Size(max = 100)
	@Schema(example = "Apparrio Ltd.")
	private String seller;

	@Size(max = 255)
	@Schema(example = "Jaipur")
	private String sellerAddress;

	@Schema(example = "300.0")
	private BigDecimal price;
	
	@NotBlank
	@Schema(example = "JWT Token from auth service")
	private String token;

}