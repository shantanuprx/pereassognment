package com.assignment.userservice.controller;

import static org.junit.Assert.assertTrue;

import java.util.Date;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.assignment.userservice.UserserviceApplication;
import com.assignment.userservice.auth.TokenGenService;
import com.assignment.userservice.constants.AddressConstants;
import com.assignment.userservice.constants.GatewayServiceConstants;
import com.assignment.userservice.dto.AddressDto;
import com.assignment.userservice.dto.AddressUpdateDto;
import com.assignment.userservice.entity.User;
import com.assignment.userservice.repository.UserRepository;
import com.assignment.userservice.service.AddressServices;
import com.assignment.userservice.util.JedisClientHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = UserserviceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = "/data.sql")
public class ControllerLayerAddressTest<T> {

	@Autowired
	private MockMvc mvc;

	@Value("${jwt.webtoken.secret}")
	private String secreString;
	
	@Autowired
	private JedisClientHelper clientHelper;

	@Autowired
	private AddressServices<T> addressServices;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void createAddressDetailsWithStatusOk() throws JsonProcessingException, Exception {
		User entity = new User();
		entity.setFirstName("Shantanu");
		entity.setMidName(null);
		entity.setLastName("Kumar");
		entity.setEmail("kumar93782@gmail.com");
		entity.setPassword("abcdefg");
		entity.setCreatedBy(GatewayServiceConstants.GATEWAY_SERVICE);
		entity.setCreatedDate(new Date());
		entity.setDateOfBirth(new Date());
		entity.setMobileNumber("9521635420");
		entity.setStatus(GatewayServiceConstants.ACTIVE_FLAG);
		entity.setUserRole(GatewayServiceConstants.CUSTOMER_USER_ROLE);
		
		userRepository.save(entity);
		
		AddressDto addressDto = new AddressDto();
		addressDto.setAddressLine1("Address Line 1");
		addressDto.setAddressLine2("Address Line 2");
		addressDto.setCity("City");
		addressDto.setLoggedInUserId(1);
		addressDto.setPincode("PINCODE");
		addressDto.setState("State");
		addressDto.setToken(setUp("CUSTOMER", entity.getUserId()));
		addressDto.setUserRole("CUSTOMER");
		String result = mvc.perform(MockMvcRequestBuilders.post("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressDto))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(AddressConstants.RECORD_CREATION_MESSAGE));
	}
	
	@Test
	public void createAddressDetailsAndUpdateWithStatusOk() throws JsonProcessingException, Exception {
		User entity = new User();
		entity.setFirstName("Shantanu");
		entity.setMidName(null);
		entity.setLastName("Kumar");
		entity.setEmail("kumar93782@gmail.com");
		entity.setPassword("abcdefg");
		entity.setCreatedBy(GatewayServiceConstants.GATEWAY_SERVICE);
		entity.setCreatedDate(new Date());
		entity.setDateOfBirth(new Date());
		entity.setMobileNumber("9521635420");
		entity.setStatus(GatewayServiceConstants.ACTIVE_FLAG);
		entity.setUserRole(GatewayServiceConstants.CUSTOMER_USER_ROLE);
		
		userRepository.save(entity);
		
		AddressDto addressDto = new AddressDto();
		addressDto.setAddressLine1("Address Line 1");
		addressDto.setAddressLine2("Address Line 2");
		addressDto.setCity("City");
		addressDto.setLoggedInUserId(1);
		addressDto.setPincode("PINCODE");
		addressDto.setState("State");
		addressDto.setToken(setUp("CUSTOMER", entity.getUserId()));
		addressDto.setUserRole("CUSTOMER");
		String result = mvc.perform(MockMvcRequestBuilders.post("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressDto))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(AddressConstants.RECORD_CREATION_MESSAGE));
		
		JSONObject jsonObject = new JSONObject(result);
		
		AddressUpdateDto addressUpdateDto = new AddressUpdateDto();
		addressUpdateDto.setAddressLine1("Address Line 1");
		addressUpdateDto.setAddressLine2("Address Line 2");
		addressUpdateDto.setCity("City");
		addressUpdateDto.setLoggedInUserId(1);
		addressUpdateDto.setPincode("PINCODE");
		addressUpdateDto.setState("State");
		addressUpdateDto.setToken(setUp("CUSTOMER", entity.getUserId()));
		addressUpdateDto.setUserRole("CUSTOMER");
		addressUpdateDto.setRecordId(jsonObject.getInt("id"));
		String updateResult = mvc.perform(MockMvcRequestBuilders.put("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressUpdateDto))
			).andExpect(MockMvcResultMatchers.status().isOk())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(updateResult.contains(AddressConstants.RECORD_UPDATED_SUCCESSFULLY));
	}
	
	@Test
	public void createAddressDetailsAndDeleteWithStatusOk() throws JsonProcessingException, Exception {
		User entity = new User();
		entity.setFirstName("Shantanu");
		entity.setMidName(null);
		entity.setLastName("Kumar");
		entity.setEmail("kumar93782@gmail.com");
		entity.setPassword("abcdefg");
		entity.setCreatedBy(GatewayServiceConstants.GATEWAY_SERVICE);
		entity.setCreatedDate(new Date());
		entity.setDateOfBirth(new Date());
		entity.setMobileNumber("9521635420");
		entity.setStatus(GatewayServiceConstants.ACTIVE_FLAG);
		entity.setUserRole(GatewayServiceConstants.CUSTOMER_USER_ROLE);
		
		userRepository.save(entity);
		
		AddressDto addressDto = new AddressDto();
		addressDto.setAddressLine1("Address Line 1");
		addressDto.setAddressLine2("Address Line 2");
		addressDto.setCity("City");
		addressDto.setLoggedInUserId(1);
		addressDto.setPincode("PINCODE");
		addressDto.setState("State");
		addressDto.setToken(setUp("CUSTOMER", entity.getUserId()));
		addressDto.setUserRole("CUSTOMER");
		String result = mvc.perform(MockMvcRequestBuilders.post("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressDto))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(AddressConstants.RECORD_CREATION_MESSAGE));
		
		JSONObject jsonObject = new JSONObject(result);
		
		AddressUpdateDto addressUpdateDto = new AddressUpdateDto();
		addressUpdateDto.setToken(setUp("CUSTOMER", entity.getUserId()));
		addressUpdateDto.setUserRole("CUSTOMER");
		addressUpdateDto.setRecordId(jsonObject.getInt("id"));
		String updateResult = mvc.perform(MockMvcRequestBuilders.delete("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressUpdateDto))
			).andExpect(MockMvcResultMatchers.status().isOk())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(updateResult.contains(AddressConstants.RECORD_DELETED_SUCCESSFULLY));
	}
	
	@Test
	public void createAddressDetailsAndFetchWithStatusOk() throws JsonProcessingException, Exception {
		User entity = new User();
		entity.setFirstName("Shantanu");
		entity.setMidName(null);
		entity.setLastName("Kumar");
		entity.setEmail("kumar93782@gmail.com");
		entity.setPassword("abcdefg");
		entity.setCreatedBy(GatewayServiceConstants.GATEWAY_SERVICE);
		entity.setCreatedDate(new Date());
		entity.setDateOfBirth(new Date());
		entity.setMobileNumber("9521635420");
		entity.setStatus(GatewayServiceConstants.ACTIVE_FLAG);
		entity.setUserRole(GatewayServiceConstants.CUSTOMER_USER_ROLE);
		
		userRepository.save(entity);
		
		AddressDto addressDto = new AddressDto();
		addressDto.setAddressLine1("Address Line 1");
		addressDto.setAddressLine2("Address Line 2");
		addressDto.setCity("City");
		addressDto.setLoggedInUserId(1);
		addressDto.setPincode("PINCODE");
		addressDto.setState("State");
		addressDto.setToken(setUp("CUSTOMER", entity.getUserId()));
		addressDto.setUserRole("CUSTOMER");
		String result = mvc.perform(MockMvcRequestBuilders.post("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressDto))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(AddressConstants.RECORD_CREATION_MESSAGE));
		
		JSONObject jsonObject = new JSONObject(result);
		
		ResponseEntity<T> response = addressServices.getDetails(Map.of(GatewayServiceConstants.LOGGED_IN_USER_ID, entity.getUserId(),
																"recordId", jsonObject.getInt("id")));
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertTrue(response.getBody().toString().contains("Address Line 1"));
	}
	
//	@Test
//	public void createProductDetailsAndThenFetchDetailsWithStatusOk() throws JsonProcessingException, Exception {
//		ProductDto productDto = new ProductDto();
//		productDto.setToken(setUp("ADMIN"));
//		productDto.setProductName("Asus RAM");
//		productDto.setProductDescription("RAM");
//		productDto.setCurrentStock(10);
//		productDto.setStatus("A");
//		productDto.setSeller("Appario");
//		productDto.setSellerAddress("Jaipur");
//		productDto.setPrice(BigDecimal.valueOf(200.0));
//		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/product")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(new ObjectMapper().writeValueAsString(productDto))
//			).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
//		
//		String resultString = mvcResult.getResponse().getContentAsString();
//		JSONObject jsonObject = new JSONObject(resultString);
//		Map<String, Object> requestMap = Map.of(GatewayServiceConstants.LOGGED_IN_USER_ID, 1,
//												"productId", jsonObject.getInt("id"));
//		ResponseEntity<T> responseEntity = productOperationService.getDetails(requestMap);
//		assertTrue(responseEntity.getStatusCode().equals(HttpStatus.OK));
//	}
//	
//	@Test
//	public void createProductAndUpdateWithStatusOk() throws JsonProcessingException, Exception {
//		String token = setUp("ADMIN");
//		ProductDto productDto = new ProductDto();
//		productDto.setToken(token);
//		productDto.setProductName("Asus RAM");
//		productDto.setProductDescription("RAM");
//		productDto.setCurrentStock(10);
//		productDto.setStatus("A");
//		productDto.setSeller("Appario");
//		productDto.setSellerAddress("Jaipur");
//		productDto.setPrice(BigDecimal.valueOf(200.0));
//		MvcResult mvcResult =  mvc.perform(MockMvcRequestBuilders.post("/product")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(new ObjectMapper().writeValueAsString(productDto))
//			).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
//		
//		String resultString = mvcResult.getResponse().getContentAsString();
//		JSONObject jsonObject = new JSONObject(resultString);
//		
//		ProductUpdateDto productUpdateDto = new ProductUpdateDto();
//		productUpdateDto.setCurrentStock(0);
//		productUpdateDto.setToken(token);
//		productUpdateDto.setPrice(BigDecimal.ZERO);
//		productUpdateDto.setProductName("new prodcutName");
//		productUpdateDto.setProductDescription("RAM");
//		productUpdateDto.setCurrentStock(10);
//		productUpdateDto.setStatus("O");
//		productUpdateDto.setSeller("Appario");
//		productUpdateDto.setSellerAddress("Jaipur");
//		productUpdateDto.setProductId(jsonObject.getInt("id"));
//		
//		mvc.perform(MockMvcRequestBuilders.put("/product")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(new ObjectMapper().writeValueAsString(productUpdateDto))
//			).andExpect(MockMvcResultMatchers.status().isOk());
//	}
//	
//	@Test
//	public void createProductAndDeleteWithStatusOk() throws JsonProcessingException, Exception {
//		String token = setUp("ADMIN");
//		ProductDto productDto = new ProductDto();
//		productDto.setToken(token);
//		productDto.setProductName("Asus RAM");
//		productDto.setProductDescription("RAM");
//		productDto.setCurrentStock(10);
//		productDto.setStatus("A");
//		productDto.setSeller("Appario");
//		productDto.setSellerAddress("Jaipur");
//		productDto.setPrice(BigDecimal.valueOf(200.0));
//		MvcResult mvcResult =  mvc.perform(MockMvcRequestBuilders.post("/product")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(new ObjectMapper().writeValueAsString(productDto))
//			).andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();
//		
//		String resultString = mvcResult.getResponse().getContentAsString();
//		JSONObject jsonObject = new JSONObject(resultString);
//		
//		ProductUpdateDto productUpdateDto = new ProductUpdateDto();
//		productUpdateDto.setProductId(jsonObject.getInt("id"));
//		productUpdateDto.setToken(token);
//		
//		mvc.perform(MockMvcRequestBuilders.delete("/product")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(new ObjectMapper().writeValueAsString(productUpdateDto))
//			).andExpect(MockMvcResultMatchers.status().isOk());
//	}
//	
//	
	private String setUp(String userRole, int userId) {
		String token = TokenGenService.generateToken(userId, userRole, "abc@gmail.com", secreString);
		clientHelper.saveKeyPair(token, String.valueOf(true));
		return token;
	}
}