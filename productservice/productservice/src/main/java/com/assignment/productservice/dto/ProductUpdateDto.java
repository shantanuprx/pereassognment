package com.assignment.productservice.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Min;
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

	@Min(0)
	private int productId;

	@Size(max = 100)
	private String productName;

	@Size(max = 1000)
	private String productDescription;

	private int currentStock;

	@Size(max = 1, min = 1)
	private String status;

	@Size(max = 100)
	private String seller;

	@Size(max = 255)
	private String sellerAddress;

	private BigDecimal price;

}