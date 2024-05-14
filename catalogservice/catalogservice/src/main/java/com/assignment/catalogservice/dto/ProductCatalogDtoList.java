package com.assignment.catalogservice.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductCatalogDtoList {

	@JsonProperty("products")
	private List<ProductCatalogDto> productCatalogList;
	
	private int pageNumber;
	
	private int totalPages;
}
