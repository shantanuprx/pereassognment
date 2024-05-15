package com.assignment.orderservice.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PRODUCTS")
@Getter
@Setter
@ToString
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private int productId;

	@Column(name = "PRODUCT_NAME", nullable = false)
	private String productName;

	@Column(name = "PRODUCT_DESCRIPTION")
	private String productDescription;

	@Column(name = "CURRENT_STOCK", nullable = false)
	private int currentStock;

	@Column(name = "STATUS", nullable = false, length = 1)
	private String status;

	@Column(name = "IS_DELETED", length = 1, columnDefinition = "char default 'N'")
	private String isDeleted;

	@Column(name = "SELLER", nullable = false)
	private String seller;

	@Column(name = "SELLER_ADDRESS", nullable = false)
	private String sellerAddress;

	@Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	@Column(name = "OLD_PRICE", precision = 10, scale = 2)
	private BigDecimal oldPrice;

	@Column(name = "CREATED_TIMESTAMP", nullable = false, updatable = false)
	private Timestamp createdTimestamp;

	@Column(name = "UPDATED_TIMESTAMP")
	private Timestamp updatedTimestamp;

	@Column(name = "CREATED_BY", nullable = false)
	private int createdBy;

	@Column(name = "UPDATED_BY")
	private int updatedBy;
}
