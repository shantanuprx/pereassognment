package com.assignment.ecommerceweb.adapters;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.assignment.ecommerceweb.constants.ProductsConstants;
import com.assignment.ecommerceweb.dto.ProductDto;
import com.assignment.ecommerceweb.dto.ProductUpdateDto;
import com.assignment.ecommerceweb.entity.Product;

public class ProductAdapter {

	private ProductAdapter() {

	}

	public static Product convertModelToEntityForInsertion(ProductDto model) {
		Product productEntity = new Product();
		productEntity.setCurrentStock(model.getCurrentStock());
		productEntity.setPrice(model.getPrice());
		productEntity.setProductDescription(model.getProductDescription());
		productEntity.setProductName(model.getProductName());
		productEntity.setSeller(model.getSeller());
		productEntity.setSellerAddress(model.getSellerAddress());
		productEntity.setStatus(ProductsConstants.ACTIVE_FLAG);
		return productEntity;
	}

	public static void mapModelValuesToEntityForUpdate(ProductUpdateDto request, Product product) {
		if (request.getPrice() != null) {
			if(!product.getPrice().equals(request.getPrice())) {
				product.setOldPrice(product.getPrice());
			}
			product.setPrice(request.getPrice());
		}

		if (request.getProductName() != null) {
			product.setProductName(request.getProductName());
		}

		if (request.getProductDescription() != null) {
			product.setProductDescription(request.getProductDescription());
		}

		if (request.getSeller() != null) {
			product.setSeller(request.getSeller());
		}

		if (request.getSellerAddress() != null) {
			product.setSellerAddress(request.getSellerAddress());
		}

		if (request.getCurrentStock() >= 0) {
			product.setCurrentStock(request.getCurrentStock());
			if (request.getCurrentStock() == 0) {
				product.setStatus("Out Of Stock");
			}
		}

		if (request.getStatus() != null) {
			product.setStatus(request.getStatus());
		}
	}

	public static void updateEntityValuesForDeletion(Product productEntity) {
		productEntity.setIsDeleted(ProductsConstants.YES_FLAG);
		productEntity.setUpdatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		productEntity.setStatus(ProductsConstants.DELETED_FLAG);
	}

	public static ProductDto convertEntityToModel(Product productEntity) {
		ProductDto productDto = new ProductDto();
		productDto.setProductId(productEntity.getProductId());
		productDto.setPrice(productEntity.getPrice());
		productDto.setOldPrice(productEntity.getOldPrice());
		productDto.setProductDescription(productEntity.getProductDescription());
		productDto.setProductName(productEntity.getProductName());
		productDto.setSeller(productEntity.getSeller());
		productDto.setSellerAddress(productEntity.getSellerAddress());
		productDto.setStatus(productEntity.getStatus());
		return productDto;
	}
}
