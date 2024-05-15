package com.assignment.productservice.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assignment.productservice.entity.Product;
import com.assignment.productservice.repository.ProductRepository;
import com.assignment.productservice.util.ElasticSearchUtil;
import com.assignment.productservice.util.ResponseUtil;

public class ProductOperationServiceTest<T> {

//	@Mock
//	private ResponseUtil<T> responseUtil;
//	
//	@Mock
//	private ProductRepository productRepository;
//	
//	@Mock
//	private ElasticSearchUtil<T> elasticSearchUtil;
//
//	@InjectMocks
//	private ProductOperationService<T> productOperationService;
//	
//	@Before
//	public void setUp() {
//		MockitoAnnotations.openMocks(this);
//	}
//	
//	@SuppressWarnings("unchecked")
//	@Test
//	public void getDetailsForValidProductId() throws Exception {
//		Map<String, Object> requestData = Map.of("productId", 1, "loggedInUserId", 1);
//		Product entity = new Product();
//		entity.setProductName("sample product");
//		when(productRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(entity));
//		ResponseUtil<T> responseUtil = mock(ResponseUtil.class);
//		productOperationService.setResponseUtil(responseUtil);
//		ResponseEntity<String> response = (ResponseEntity<String>) productOperationService.getDetails(requestData);
//		assertTrue(response.getBody().contains("sample product"));
//		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
//		
//	}
}
