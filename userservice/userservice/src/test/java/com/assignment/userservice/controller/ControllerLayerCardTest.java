package com.assignment.userservice.controller;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
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
import com.assignment.userservice.constants.GatewayServiceConstants;
import com.assignment.userservice.constants.PaymentConstants;
import com.assignment.userservice.entity.User;
import com.assignment.userservice.repository.UserRepository;
import com.assignment.userservice.service.CardDetailService;
import com.assignment.userservice.util.JedisClientHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = UserserviceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@Sql(scripts = "/data.sql")
public class ControllerLayerCardTest<T> {

	@Autowired
	private MockMvc mvc;

	@Value("${jwt.webtoken.secret}")
	private String secreString;
	
	@Autowired
	private JedisClientHelper clientHelper;

	@Autowired
	private CardDetailService<T> cardDetailService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void createCardDetailsWithStatusOk() throws JsonProcessingException, Exception {
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
		
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("cardHolderName","SHANTANU KUMAR");
		requestMap.put("token",(setUp("CUSTOMER", entity.getUserId())));
		requestMap.put("cardNumber", "1234123412341234");
		requestMap.put("expiryDate", "12/12/2025");
		requestMap.put(PaymentConstants.PAYMENT_TYPE, "card");
		String result = mvc.perform(MockMvcRequestBuilders.post("/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(requestMap))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(PaymentConstants.RECORD_CREATION_MESSAGE));
	}
	

	
	@Test
	public void createCardDetailsAndUpdateWithStatusOk() throws JsonProcessingException, Exception {
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
		
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("cardHolderName","SHANTANU KUMAR");
		requestMap.put("token",(setUp("CUSTOMER", entity.getUserId())));
		requestMap.put("cardNumber", "1234123412341234");
		requestMap.put("expiryDate", "12/12/2025");
		requestMap.put(PaymentConstants.PAYMENT_TYPE, "card");
		String result = mvc.perform(MockMvcRequestBuilders.post("/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(requestMap))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(PaymentConstants.RECORD_CREATION_MESSAGE));
		
		JSONObject jsonObject = new JSONObject(result);
		
		Map<String, Object> updateRequestMap = new HashMap<>();
		updateRequestMap.put("recordId", jsonObject.getInt("id"));
		updateRequestMap.put("cardHolderName","SHANTANU KUMAR");
		updateRequestMap.put("token",(setUp("CUSTOMER", entity.getUserId())));
		updateRequestMap.put("cardNumber", "1234123412341234");
		updateRequestMap.put("expiryDate", "12/12/2025");
		updateRequestMap.put(PaymentConstants.PAYMENT_TYPE, "card");
		String updateResult = mvc.perform(MockMvcRequestBuilders.put("/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(updateRequestMap))
			).andExpect(MockMvcResultMatchers.status().isOk())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(updateResult.contains(PaymentConstants.RECORD_UPDATED_SUCCESSFULLY));
	}
	
	@Test
	public void createCardDetailsAndDeleteWithStatusOk() throws JsonProcessingException, Exception {
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
		
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("cardHolderName","SHANTANU KUMAR");
		requestMap.put("token",(setUp("CUSTOMER", entity.getUserId())));
		requestMap.put("cardNumber", "1234123412341234");
		requestMap.put("expiryDate", "12/12/2025");
		requestMap.put(PaymentConstants.PAYMENT_TYPE, "card");
		String result = mvc.perform(MockMvcRequestBuilders.post("/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(requestMap))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(PaymentConstants.RECORD_CREATION_MESSAGE));
		
		JSONObject jsonObject = new JSONObject(result);
		
		Map<String, Object> updateRequestMap = new HashMap<>();
		updateRequestMap.put("recordId", jsonObject.getInt("id"));
		updateRequestMap.put("token",(setUp("CUSTOMER", entity.getUserId())));
		updateRequestMap.put(PaymentConstants.PAYMENT_TYPE, "card");
		String updateResult = mvc.perform(MockMvcRequestBuilders.delete("/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(updateRequestMap))
			).andExpect(MockMvcResultMatchers.status().isOk())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(updateResult.contains(PaymentConstants.RECORD_DELETED_SUCCESSFULLY));
	}
	
	@Test
	public void createCardDetailsAndFetchWithStatusOk() throws JsonProcessingException, Exception {
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
		
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("cardHolderName","SHANTANU KUMAR");
		requestMap.put("token",(setUp("CUSTOMER", entity.getUserId())));
		requestMap.put("cardNumber", "1234123412341234");
		requestMap.put("expiryDate", "12/12/2025");
		requestMap.put(PaymentConstants.PAYMENT_TYPE, "card");
		String result = mvc.perform(MockMvcRequestBuilders.post("/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(requestMap))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(PaymentConstants.RECORD_CREATION_MESSAGE));
		
		JSONObject jsonObject = new JSONObject(result);
		
		Map<String, Object> getRequestMap = new HashMap<>();
		getRequestMap.put("recordId", jsonObject.getInt("id"));
		getRequestMap.put(GatewayServiceConstants.LOGGED_IN_USER_ID, entity.getUserId());
		getRequestMap.put("token",(setUp("CUSTOMER", entity.getUserId())));
		
		ResponseEntity<T> response = cardDetailService.getDetails(getRequestMap);
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertTrue(response.getBody().toString().contains("1234123412341234"));
		
	}
	
	@Test
	public void createCardDetailsAndValidateWithStatusOk() throws JsonProcessingException, Exception {
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
		
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("cardHolderName","SHANTANU KUMAR");
		requestMap.put("token",(setUp("CUSTOMER", entity.getUserId())));
		requestMap.put("cardNumber", "1234123412341234");
		requestMap.put("expiryDate", "12/12/2025");
		requestMap.put(PaymentConstants.PAYMENT_TYPE, "card");
		String result = mvc.perform(MockMvcRequestBuilders.post("/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(requestMap))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(PaymentConstants.RECORD_CREATION_MESSAGE));
		
		JSONObject jsonObject = new JSONObject(result);
		
		Map<String, Object> getRequestMap = new HashMap<>();
		getRequestMap.put("recordId", jsonObject.getInt("id"));
		getRequestMap.put(GatewayServiceConstants.LOGGED_IN_USER_ID, entity.getUserId());
		getRequestMap.put("token",(setUp("CUSTOMER", entity.getUserId())));
		getRequestMap.put(PaymentConstants.PAYMENT_TYPE, "card");
		
		String validateResult = mvc.perform(MockMvcRequestBuilders.get("/payment/validate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(getRequestMap))
			).andExpect(MockMvcResultMatchers.status().isOk())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(validateResult.contains(String.valueOf(true)));
		
	}
	
	@Test
	public void createCardDetailsAndFailedValidateWithStatusOk() throws JsonProcessingException, Exception {
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
		
		Map<String, Object> requestMap = new HashMap<>();
		requestMap.put("cardHolderName","SHANTANU KUMAR");
		requestMap.put("token",(setUp("CUSTOMER", entity.getUserId())));
		requestMap.put("cardNumber", "1234123412341234");
		requestMap.put("expiryDate", "12/12/2025");
		requestMap.put(PaymentConstants.PAYMENT_TYPE, "card");
		String result = mvc.perform(MockMvcRequestBuilders.post("/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(requestMap))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(PaymentConstants.RECORD_CREATION_MESSAGE));
		
		JSONObject jsonObject = new JSONObject(result);
		
		Map<String, Object> getRequestMap = new HashMap<>();
		getRequestMap.put("recordId", 20000);
		getRequestMap.put(GatewayServiceConstants.LOGGED_IN_USER_ID, entity.getUserId());
		getRequestMap.put("token",(setUp("CUSTOMER", entity.getUserId())));
		getRequestMap.put(PaymentConstants.PAYMENT_TYPE, "card");
		
		String validateResult = mvc.perform(MockMvcRequestBuilders.get("/payment/validate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(getRequestMap))
			).andExpect(MockMvcResultMatchers.status().isOk())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(validateResult.contains(String.valueOf(false)));
		
	}
	
	private String setUp(String userRole, int userId) {
		String token = TokenGenService.generateToken(userId, userRole, "abc@gmail.com", secreString);
		clientHelper.saveKeyPair(token, String.valueOf(true));
		return token;
	}
}