package com.assignment.productservice.util;

import org.springframework.stereotype.Component;

import com.assignment.productservice.dto.ProductDto;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ElasticSearchUtil<T> {

//	@Autowired
//	private CatalogServiceFeignClient catalogServiceFeignClient;

	public void indexObject(ProductDto request) {
		log.info("Entering indexObject Method at {} ", System.currentTimeMillis());
//		try {
//			catalogServiceFeignClient.addDetails(prepareRequest(request));
//		} catch (Exception ex) {
//			log.error("Exception occurred while indexing product details with exception ", ex);
//			throw ex;
//		} finally {
//			log.info("Exiting indexObject method at  {} ", System.currentTimeMillis());
//		}
	}

	public void updateObject(ProductDto request) {
		log.info("Entering updateObject Method at {} ", System.currentTimeMillis());
//		try {
//			catalogServiceFeignClient.updateDetails(prepareRequest(request));
//		} catch (Exception ex) {
//			log.error("Exception occurred while indexing product details with exception ", ex);
//			throw ex;
//		} finally {
//			log.info("Exiting updateObject method at  {} ", System.currentTimeMillis());
//		}
	}

//	private Map<String, Object> prepareRequest(ProductDto request) {
//		Map<String, Object> requestMap = new HashMap<>();
//		requestMap.put("productId", request.getProductId());
//		requestMap.put("productName", request.getProductName());
//		requestMap.put("productDescription", request.getProductDescription());
//		requestMap.put("price", request.getPrice());
//		requestMap.put("oldPrice", request.getOldPrice());
//		return requestMap;
//	}

	public void deleteItemFromIndex(int productId) {
		log.info("Entering deleteItemFromIndex Method at {} ", System.currentTimeMillis());
//		try {
//			catalogServiceFeignClient.deleteDetails(productId);
//		} catch (Exception ex) {
//			log.error("Exception occurred while deleting product details with exception ", ex);
//			throw ex;
//		} finally {
//			log.info("Exiting deleteItemFromIndex method at  {} ", System.currentTimeMillis());
//		}
	}

}
