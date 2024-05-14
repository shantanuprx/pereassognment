package com.assignment.ecommerceweb.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class ProductDto extends BaseDto {	
	private Integer productId;

	private String productName;

	private String productDescription;

	private Integer currentStock;

	private String status;

	private String isDeleted;

	private String seller;

	private String sellerAddress;

	private BigDecimal price;

	private BigDecimal oldPrice;

	private Timestamp createdTimestamp;

	private Timestamp updatedTimestamp;

	private String createdBy;

	private String updatedBy;
	
}
