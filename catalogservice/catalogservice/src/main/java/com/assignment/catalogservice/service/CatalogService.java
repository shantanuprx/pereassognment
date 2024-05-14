package com.assignment.catalogservice.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.catalogservice.repository.ProductCatalogRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CatalogService<T> {

	@Autowired
	private ProductCatalogRepository productCatalogRepository;

	@Value("${catalog.page.size:10}")
	private int configuredPageSize;

	public ResponseEntity<T> getDetails(String query, int pageNumber) {
		log.debug("Fetching details for query {} ", query);
//		Iterable<ProductCatalog> iterable = productCatalogRepository.findByProductName(query, PageRequest.of(pageNumber, configuredPageSize));
//		Iterable<ProductCatalog> iterable = productCatalogRepository.findAllProductCatalogs(query, PageRequest.of(pageNumber, configuredPageSize));
//		Iterator<ProductCatalog> iterator = iterable.iterator();
//		while(iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
//		
//		List<ProductCatalogDto> resultList = result.stream().map(a -> new ProductCatalogDto(a.getProductId(), a.getProductName(), a.getProductDescription(), a.getPrice(), a.getOldPrice())).collect(Collectors.toList());
//		ProductCatalogDtoList productCatalogDtoList = new ProductCatalogDtoList(resultList, pageNumber, result.getTotalPages());
//		return (ResponseEntity<T>) new ResponseEntity<>(productCatalogDtoList, HttpStatus.OK);
		return null;
	}

	public ResponseEntity<T> addDetails(Map<String, Object> requestBody) {
		log.debug("Adding details for data {} ", requestBody);
//		try {
//			ProductCatalog productCatalogEntity = (new ObjectMapper()).convertValue(requestBody, ProductCatalog.class);
//			productCatalogRepository.save(productCatalogEntity);
//			return null;
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			return null;
//		} finally {
//			
//		}
		return null;
	}

	public ResponseEntity<T> updateDetails(Map<String, Object> requestBody) {
		log.debug("Updating details for data {} ", requestBody);
		// TODO Auto-generated method stub
		return null;
	}

	public ResponseEntity<T> deleteDetails(Map<String, Object> requestBody) {
		log.debug("Deleting details for data {} ", requestBody);
		// TODO Auto-generated method stub
		return null;
	}

}
