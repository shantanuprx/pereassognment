package com.assignment.ecommerceweb.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.ecommerceweb.adapters.ProductAdapter;
import com.assignment.ecommerceweb.constants.ProductsConstants;
import com.assignment.ecommerceweb.dto.ProductDto;
import com.assignment.ecommerceweb.dto.ProductUpdateDto;
import com.assignment.ecommerceweb.dto.ResponseDto;
import com.assignment.ecommerceweb.entity.Product;
import com.assignment.ecommerceweb.repository.ProductRepository;
import com.assignment.ecommerceweb.util.ElasticSearchUtil;
import com.assignment.ecommerceweb.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service("product")
@Slf4j
@SuppressWarnings("unchecked")
public class ProductOperationService<T> implements BaseService<T> {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ElasticSearchUtil<T> elasticSearchUtil;

	@Autowired
	private ResponseUtil<T> responseUtil;

	public ResponseEntity<T> getDetails(Map<String, Object> requestData) {
		log.info("Entering getDetails Method at {} ", System.currentTimeMillis());

		try {
			int productId = (int) requestData.get("productId");
			Optional<Product> productEntityOptional = productRepository.findById(productId);
			if (productEntityOptional.isEmpty()) {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, ProductsConstants.INVALID_PRODUCT_ID),
						HttpStatus.BAD_REQUEST);
			}
			Product productEntity = productEntityOptional.get();
			ProductDto productDto = ProductAdapter.convertEntityToModel(productEntity);
			log.info("Product {} successfully fetched ", productEntity.getProductId());
			return responseUtil.prepareResponse((T) productDto, HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while fetching product details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting getDetails method at  {} ", System.currentTimeMillis());
		}
	}

	public ResponseEntity<T> addDetails(Map<String, Object> requestData) {
		log.info("Entering addProduct Method at {} ", System.currentTimeMillis());
		try {
			ProductDto request = new ObjectMapper().convertValue(requestData, ProductDto.class);
			Product productEntityToPersist = ProductAdapter.convertModelToEntityForInsertion(request);
			productEntityToPersist.setCreatedBy(request.getLoggedInUser());
			productRepository.save(productEntityToPersist);
			elasticSearchUtil.indexObject(request);
			log.info("Product {} successfully created by {} ", productEntityToPersist.getProductId(),
					request.getLoggedInUser());
			return responseUtil.prepareResponse(
					(T) new ResponseDto(HttpStatus.CREATED, ProductsConstants.RECORD_CREATION_MESSAGE),
					HttpStatus.CREATED);
		} catch (Exception ex) {
			log.error("Exception occurred while adding new product with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting addProduct method at  {} ", System.currentTimeMillis());
		}
	}

	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) {
		log.info("Entering updateProduct Method at {} ", System.currentTimeMillis());
		try {
			ProductUpdateDto request = new ObjectMapper().convertValue(requestData, ProductUpdateDto.class);
			Optional<Product> productEntityOptional = productRepository.findById(request.getProductId());
			if (productEntityOptional.isEmpty()) {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, ProductsConstants.INVALID_PRODUCT_ID),
						HttpStatus.BAD_REQUEST);
			}
			Product productEntity = productEntityOptional.get();
			ProductAdapter.mapModelValuesToEntityForUpdate(request, productEntity);
			productEntity.setUpdatedBy(request.getLoggedInUser());
			productRepository.save(productEntity);
			elasticSearchUtil.updateObject(ProductAdapter.convertEntityToModel(productEntity));
			log.info("Product {} successfully updated by {} ", productEntity.getProductId(), request.getLoggedInUser());
			return responseUtil.prepareResponse(
					(T) new ResponseDto(HttpStatus.OK, ProductsConstants.RECORD_UPDATE_MESSAGE), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while updating product with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting updateProduct method at  {} ", System.currentTimeMillis());
		}
	}

	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) {
		log.info("Entering deleteProduct Method at {} ", System.currentTimeMillis());
		try {
			ProductUpdateDto request = new ObjectMapper().convertValue(requestData, ProductUpdateDto.class);
			Optional<Product> productEntityOptional = productRepository.findById(request.getProductId());
			if (productEntityOptional.isEmpty()) {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, ProductsConstants.INVALID_PRODUCT_ID),
						HttpStatus.BAD_REQUEST);
			}
			Product productEntity = productEntityOptional.get();
			ProductAdapter.updateEntityValuesForDeletion(productEntity);
			productEntity.setUpdatedBy(request.getLoggedInUser());
			productRepository.save(productEntity);
			elasticSearchUtil.deleteItemFromIndex(productEntity.getProductId());
			log.info("Product {} successfully deleted by {} ", productEntity.getProductId(), request.getLoggedInUser());
			return responseUtil.prepareResponse(
					(T) new ResponseDto(HttpStatus.OK, ProductsConstants.RECORD_CREATION_MESSAGE), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while deleting the product with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting deleteProduct method at  {} ", System.currentTimeMillis());
		}
	}

}
