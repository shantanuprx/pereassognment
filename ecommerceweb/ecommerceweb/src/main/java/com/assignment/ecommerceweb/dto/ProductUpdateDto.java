package com.assignment.ecommerceweb.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class ProductUpdateDto extends BaseDto{

	private int productId;

	private String productName;

	private String productDescription;

	private int currentStock;

	private String status;

	private String seller;

	private String sellerAddress;

	private BigDecimal price;

	private String session;
	
}