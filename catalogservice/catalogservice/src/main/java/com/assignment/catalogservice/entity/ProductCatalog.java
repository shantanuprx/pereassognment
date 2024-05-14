package com.assignment.catalogservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/* *
 * This is a index mapping that will help to index products in ES
 * We will further use it to search items at faster speeds
 * 
 * Putting price in indexing to allow filter as well as showing discount on list screen.
 * */

@Document(indexName = "productcatalog")
@Getter
@Setter
@ToString
public class ProductCatalog {

	@Id
	private long productId;

	private String productName;

	private String productDescription;
	
	private int price;
	
	private int oldPrice;
}
