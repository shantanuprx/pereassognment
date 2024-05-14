package com.assignment.catalogservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCatalogDto {

	private long productId;

	private String productName;

	private String productDescription;
	
	private int price;
	
	private int oldPrice;
}
