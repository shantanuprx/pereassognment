package com.assignment.productservice.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

	@NotNull
	private String productName;

	@NotNull
	private String productDescription;

	@Min(0)
	private Integer currentStock;

	@NotNull
	private String status;

	private String isDeleted;

	@NotNull
	private String seller;

	@NotNull
	private String sellerAddress;

	@NotNull
	private BigDecimal price;

	private BigDecimal oldPrice;

	private Timestamp createdTimestamp;

	private Timestamp updatedTimestamp;

	private String createdBy;

	private String updatedBy;
	
}
