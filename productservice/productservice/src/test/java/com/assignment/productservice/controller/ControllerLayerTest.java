package com.assignment.productservice.controller;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.assignment.productservice.ProductserviceApplication;
import com.assignment.productservice.auth.TokenGenService;
import com.assignment.productservice.constants.GatewayServiceConstants;
import com.assignment.productservice.constants.ProductsConstants;
import com.assignment.productservice.dto.ProductDto;
import com.assignment.productservice.dto.ProductUpdateDto;
import com.assignment.productservice.service.ProductOperationService;
import com.assignment.productservice.util.JedisClientHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ProductserviceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = "/data.sql")
public class ControllerLayerTest<T> {

	@Autowired
	private MockMvc mvc;

	@Value("${jwt.webtoken.secret}")
	private String secreString;

	@Autowired
	private JedisClientHelper clientHelper;

	@Autowired
	private ProductOperationService<T> productOperationService;

	@Test
	public void createProductDetailsWithStatusOk() throws JsonProcessingException, Exception {
		ProductDto productDto = new ProductDto();
		productDto.setProductName("Asus RAM");
		productDto.setProductDescription("RAM");
		productDto.setCurrentStock(10);
		productDto.setStatus("A");
		productDto.setSeller("Appario");
		productDto.setSellerAddress("Jaipur");
		productDto.setPrice(BigDecimal.valueOf(200.0));
		String result = mvc
				.perform(MockMvcRequestBuilders.post("/product").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(productDto))
						.header(GatewayServiceConstants.TOKEN, setUp("ADMIN")))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();

		assertTrue(result.contains(ProductsConstants.RECORD_CREATION_MESSAGE));
	}

	@Test
	public void createProductDetailsAndThenFetchDetailsWithStatusOk() throws JsonProcessingException, Exception {
		ProductDto productDto = new ProductDto();
		productDto.setProductName("Asus RAM");
		productDto.setProductDescription("RAM");
		productDto.setCurrentStock(10);
		productDto.setStatus("A");
		productDto.setSeller("Appario");
		productDto.setSellerAddress("Jaipur");
		productDto.setPrice(BigDecimal.valueOf(200.0));
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post("/product").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(productDto))
						.header(GatewayServiceConstants.TOKEN, setUp("ADMIN")))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

		String resultString = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(resultString);
		Map<String, Object> requestMap = Map.of(GatewayServiceConstants.LOGGED_IN_USER_ID, 1, "productId",
				jsonObject.getInt("id"));
		ResponseEntity<T> responseEntity = productOperationService.getDetails(requestMap);
		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
		assertTrue(responseEntity.getBody().toString().contains("Asus RAM"));
	}

	@Test
	public void createProductAndUpdateWithStatusOk() throws JsonProcessingException, Exception {
		ProductDto productDto = new ProductDto();
		productDto.setProductName("Asus RAM");
		productDto.setProductDescription("RAM");
		productDto.setCurrentStock(10);
		productDto.setStatus("A");
		productDto.setSeller("Appario");
		productDto.setSellerAddress("Jaipur");
		productDto.setPrice(BigDecimal.valueOf(200.0));
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post("/product").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(productDto))
						.header(GatewayServiceConstants.TOKEN, setUp("ADMIN")))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

		String resultString = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(resultString);

		ProductUpdateDto productUpdateDto = new ProductUpdateDto();
		productUpdateDto.setCurrentStock(0);
		productUpdateDto.setPrice(BigDecimal.ZERO);
		productUpdateDto.setProductName("new prodcutName");
		productUpdateDto.setProductDescription("RAM");
		productUpdateDto.setCurrentStock(10);
		productUpdateDto.setStatus("O");
		productUpdateDto.setSeller("Appario");
		productUpdateDto.setSellerAddress("Jaipur");
		productUpdateDto.setProductId(jsonObject.getInt("id"));

		String result = mvc
				.perform(MockMvcRequestBuilders.put("/product").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(productUpdateDto))
						.header(GatewayServiceConstants.TOKEN, setUp("ADMIN")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();

		assertTrue(result.contains(ProductsConstants.RECORD_UPDATE_MESSAGE));
	}

	@Test
	public void createProductAndDeleteWithStatusOk() throws JsonProcessingException, Exception {
		ProductDto productDto = new ProductDto();
		productDto.setProductName("Asus RAM");
		productDto.setProductDescription("RAM");
		productDto.setCurrentStock(10);
		productDto.setStatus("A");
		productDto.setSeller("Appario");
		productDto.setSellerAddress("Jaipur");
		productDto.setPrice(BigDecimal.valueOf(200.0));
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post("/product").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(productDto))
						.header(GatewayServiceConstants.TOKEN, setUp("ADMIN")))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

		String resultString = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(resultString);

		ProductUpdateDto productUpdateDto = new ProductUpdateDto();
		productUpdateDto.setProductId(jsonObject.getInt("id"));

		String result = mvc
				.perform(MockMvcRequestBuilders.delete("/product").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(productUpdateDto))
						.header(GatewayServiceConstants.TOKEN, setUp("ADMIN")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(ProductsConstants.RECORD_DELETE_MESSAGE));

	}
	
	@Test
	public void fetchDetailsWithStatus400() throws JsonProcessingException, Exception {
		ProductDto productDto = new ProductDto();
		productDto.setProductId(0);
		mvc.perform(MockMvcRequestBuilders.get("/product").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(productDto))
						.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER")))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		productDto.setProductId(1000000);
		String result = mvc
				.perform(MockMvcRequestBuilders.get("/product").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(productDto))
						.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER"))
						)
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(ProductsConstants.INVALID_PRODUCT_ID));
	}
	
	@Test
	public void createProductDetailsAndValidateDetailsWithStatusOk() throws JsonProcessingException, Exception {
		ProductDto productDto = new ProductDto();
		productDto.setProductName("Asus RAM");
		productDto.setProductDescription("RAM");
		productDto.setCurrentStock(10);
		productDto.setStatus("A");
		productDto.setSeller("Appario");
		productDto.setSellerAddress("Jaipur");
		productDto.setPrice(BigDecimal.valueOf(200.0));
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post("/product").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(productDto))
						.header(GatewayServiceConstants.TOKEN, setUp("ADMIN")))

				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

		String resultString = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(resultString);
		Map<String, Object> requestMap = Map.of("productId",
				jsonObject.getInt("id"));
		String validateResult = mvc
				.perform(MockMvcRequestBuilders.get("/product/validate").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(requestMap))
						.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		assertTrue(validateResult.contains(String.valueOf(true)));
	}
	
	@Test
	public void createProductDetailsAndFailedValidateDetailsWithStatusOk() throws JsonProcessingException, Exception {
		ProductDto productDto = new ProductDto();
		productDto.setProductName("Asus RAM");
		productDto.setProductDescription("RAM");
		productDto.setCurrentStock(10);
		productDto.setStatus("A");
		productDto.setSeller("Appario");
		productDto.setSellerAddress("Jaipur");
		productDto.setPrice(BigDecimal.valueOf(200.0));
		MvcResult mvcResult = mvc
				.perform(MockMvcRequestBuilders.post("/product").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(productDto))
						.header(GatewayServiceConstants.TOKEN, setUp("ADMIN")))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

		String resultString = mvcResult.getResponse().getContentAsString();
		JSONObject jsonObject = new JSONObject(resultString);
		Map<String, Object> requestMap = Map.of( "productId",
				2000);
		String validateResult = mvc
				.perform(MockMvcRequestBuilders.get("/product/validate").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(requestMap))
						.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER")))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		assertTrue(validateResult.contains(String.valueOf(false)));
	}

	private String setUp(String userRole) {
		String token = TokenGenService.generateToken(1, userRole, "abc@gmail.com", secreString);
		clientHelper.saveKeyPair(token, String.valueOf(true));
		return token;
	}
}