package com.assignment.productservice.service;

import java.util.Map;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.productservice.adapters.ProductAdapter;
import com.assignment.productservice.constants.GatewayServiceConstants;
import com.assignment.productservice.constants.ProductsConstants;
import com.assignment.productservice.dto.ProductDto;
import com.assignment.productservice.dto.ProductUpdateDto;
import com.assignment.productservice.dto.ResponseDto;
import com.assignment.productservice.entity.Product;
import com.assignment.productservice.repository.ProductRepository;
import com.assignment.productservice.util.ElasticSearchUtil;
import com.assignment.productservice.util.JedisClientHelper;
import com.assignment.productservice.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/* *
 * Service class implementation for the product related services
 * getDetails -  for fetching details of a single product
 * addDetails -  for adding new product details.
 * updateDetails - for updating existing product details.
 * deleteDetails - for deleting the product details - Soft delete
 * */

@Service("product")
@Slf4j
@Setter
@SuppressWarnings("unchecked")
public class ProductOperationService<T> implements BaseService<T> {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ElasticSearchUtil<T> elasticSearchUtil;

	@Autowired
	private ResponseUtil<T> responseUtil;
	
	@Autowired
	private ProductAdapter productAdapter;
	
	@Autowired
	private JedisClientHelper jedisClientHelper;

	public ResponseEntity<T> getDetails(Map<String, Object> requestData) throws Exception {
		log.info("Entering getDetails Method at {} ", System.currentTimeMillis());

		try {
			ProductDto request = new ObjectMapper().convertValue(requestData, ProductDto.class);
			String productFromCache = jedisClientHelper.loadFromCache(request);
			if (productFromCache != null) {
				return responseUtil.prepareResponse(
						(T) new ObjectMapper().readValue(productFromCache.getBytes(), ProductDto.class), HttpStatus.OK);
			}
			Optional<Product> productEntityOptional = productRepository.findById(request.getProductId());
			if (productEntityOptional.isEmpty()) {
				throw new BadRequestException(ProductsConstants.INVALID_PRODUCT_ID);
			}
			Product productEntity = productEntityOptional.get();
			ProductDto productDto = productAdapter.convertEntityToModel(productEntity);
			log.info("Product {} successfully fetched ", productEntity.getProductId());
			jedisClientHelper.saveProductInCache(productDto);
			return responseUtil.prepareResponse((T) productDto, HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while fetching product details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting getDetails method at  {} ", System.currentTimeMillis());
		}
	}

	public ResponseEntity<T> addDetails(Map<String, Object> requestData) throws Exception {
		log.info("Entering addProduct Method at {} ", System.currentTimeMillis());
		try {
			ProductDto request = new ObjectMapper().convertValue(requestData, ProductDto.class);
			if (!GatewayServiceConstants.ADMIN_USER_ROLE.equalsIgnoreCase(request.getUserRole())) {
				throw new BadRequestException(ProductsConstants.ONLY_ADMIN_USER_IS_ALLOWED);
			}
			Product productEntityToPersist = productAdapter.convertModelToEntityForInsertion(request);
			productEntityToPersist.setCreatedBy(request.getLoggedInUserId());
			productRepository.save(productEntityToPersist);
			elasticSearchUtil.indexObject(request);
			log.info("Product {} successfully created by {} ", productEntityToPersist.getProductId(),
					request.getLoggedInUserId());
			return responseUtil.prepareResponse((T) new ResponseDto(productEntityToPersist.getProductId(),
					HttpStatus.CREATED, ProductsConstants.RECORD_CREATION_MESSAGE), HttpStatus.CREATED);
		} catch (Exception ex) {
			log.error("Exception occurred while adding new product with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting addProduct method at  {} ", System.currentTimeMillis());
		}
	}

	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) throws Exception {
		log.info("Entering updateProduct Method at {} ", System.currentTimeMillis());
		try {
			ProductUpdateDto request = new ObjectMapper().convertValue(requestData, ProductUpdateDto.class);
			if (!GatewayServiceConstants.ADMIN_USER_ROLE.equalsIgnoreCase(request.getUserRole())) {
				throw new BadRequestException(ProductsConstants.ONLY_ADMIN_USER_IS_ALLOWED);
			}
			Optional<Product> productEntityOptional = productRepository.findById(request.getProductId());
			if (productEntityOptional.isEmpty()) {
				throw new BadRequestException(ProductsConstants.INVALID_PRODUCT_ID);
			}
			Product productEntity = productEntityOptional.get();
			productAdapter.mapModelValuesToEntityForUpdate(request, productEntity);
			productEntity.setUpdatedBy(request.getLoggedInUserId());
			productRepository.save(productEntity);
			elasticSearchUtil.updateObject(productAdapter.convertEntityToModel(productEntity));
			log.info("Product {} successfully updated by {} ", productEntity.getProductId(),
					request.getLoggedInUserId());
			return responseUtil.prepareResponse((T) new ResponseDto(productEntity.getProductId(), HttpStatus.OK,
					ProductsConstants.RECORD_UPDATE_MESSAGE), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while updating product with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting updateProduct method at  {} ", System.currentTimeMillis());
		}
	}

	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) throws Exception {
		log.info("Entering deleteProduct Method at {} ", System.currentTimeMillis());
		try {

			ProductUpdateDto request = new ObjectMapper().convertValue(requestData, ProductUpdateDto.class);
			if (!GatewayServiceConstants.ADMIN_USER_ROLE.equalsIgnoreCase(request.getUserRole())) {
				throw new BadRequestException(ProductsConstants.ONLY_ADMIN_USER_IS_ALLOWED);
			}
			Optional<Product> productEntityOptional = productRepository.findById(request.getProductId());
			if (productEntityOptional.isEmpty()) {
				throw new BadRequestException(ProductsConstants.INVALID_PRODUCT_ID);
			}
			Product productEntity = productEntityOptional.get();
			productAdapter.updateEntityValuesForDeletion(productEntity, request);
			productEntity.setUpdatedBy(request.getLoggedInUserId());
			productRepository.save(productEntity);
			elasticSearchUtil.deleteItemFromIndex(productEntity.getProductId());
			log.info("Product {} successfully deleted by {} ", productEntity.getProductId(),
					request.getLoggedInUserId());
			return responseUtil.prepareResponse((T) new ResponseDto(productEntity.getProductId(), HttpStatus.OK,
					ProductsConstants.RECORD_DELETE_MESSAGE), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while deleting the product with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting deleteProduct method at  {} ", System.currentTimeMillis());
		}
	}

}
