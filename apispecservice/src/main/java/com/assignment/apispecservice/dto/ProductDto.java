package com.assignment.apispecservice.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/* *
 * Product DTO for handling inbound and outbound data.
 * */
@JsonInclude(value = Include.NON_NULL)
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class ProductDto extends BaseDto implements Serializable{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9177132688827244784L;

	@Schema(example = "1")
	private Integer productId;

	@NotBlank
	@Size(max = 100)
	@Schema(example = "7800xt 16 GB")
	private String productName;

	@NotBlank
	@Size(max = 1000)
	@Schema(example = "Graphic Card")
	private String productDescription;

	@NotNull
	@Schema(example = "10")
	private Integer currentStock;

	@NotNull
	@Size(max = 1, min = 1)
	@Schema(example = "O", allowableValues = { "O - Out Of stock",
	"A - Available, U - unavailable" })
 	private String status;

	@NotNull
	@Size(max = 100)
	@Schema(example = "Apparrio Ltd.")
	private String seller;

	@NotNull
	@Size(max = 255)
	@Schema(example = "Jaipur")
	private String sellerAddress;

	@NotNull
	@Schema(example = "300.0")
	private BigDecimal price;

	@Null
	@Schema(example = "300.0")
	private BigDecimal oldPrice;
	
	@NotBlank
	@Schema(example = "JWT Token from auth service")
	private String token;
	
}
