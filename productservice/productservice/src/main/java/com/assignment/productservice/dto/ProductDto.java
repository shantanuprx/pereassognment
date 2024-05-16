package com.assignment.productservice.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ProductDto extends BaseDto {	
	
	private Integer productId;

	@NotBlank
	@Size(max = 100)
	private String productName;

	@NotBlank
	@Size(max = 1000)
	private String productDescription;

	@NotNull
	private Integer currentStock;

	@NotNull
	@Size(max = 1, min = 1)
 	private String status;

	private String isDeleted;

	@NotNull
	@Size(max = 100)
	private String seller;

	@NotNull
	@Size(max = 255)
	private String sellerAddress;

	@NotNull
	private BigDecimal price;

	private BigDecimal oldPrice;

	private Timestamp createdTimestamp;

	private Timestamp updatedTimestamp;

	private String createdBy;

	private String updatedBy;
	
}
