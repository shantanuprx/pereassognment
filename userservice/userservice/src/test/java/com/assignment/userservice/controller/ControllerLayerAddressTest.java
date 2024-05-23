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
		addressDto.setUserRole("CUSTOMER");
		String result = mvc.perform(MockMvcRequestBuilders.post("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressDto))
				.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER", entity.getUserId()))
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
		addressDto.setUserRole("CUSTOMER");
		String result = mvc.perform(MockMvcRequestBuilders.post("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressDto))
				.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER", entity.getUserId()))
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
		addressUpdateDto.setUserRole("CUSTOMER");
		addressUpdateDto.setRecordId(jsonObject.getInt("id"));
		String updateResult = mvc.perform(MockMvcRequestBuilders.put("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressUpdateDto))
				.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER", entity.getUserId()))
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
		addressDto.setUserRole("CUSTOMER");
		String result = mvc.perform(MockMvcRequestBuilders.post("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressDto))
				.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER", entity.getUserId()))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(AddressConstants.RECORD_CREATION_MESSAGE));
		
		JSONObject jsonObject = new JSONObject(result);
		
		AddressUpdateDto addressUpdateDto = new AddressUpdateDto();
		addressUpdateDto.setUserRole("CUSTOMER");
		addressUpdateDto.setRecordId(jsonObject.getInt("id"));
		String updateResult = mvc.perform(MockMvcRequestBuilders.delete("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressUpdateDto))
				.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER", entity.getUserId()))
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
		addressDto.setUserRole("CUSTOMER");
		String result = mvc.perform(MockMvcRequestBuilders.post("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressDto))
				.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER", entity.getUserId()))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(AddressConstants.RECORD_CREATION_MESSAGE));
		
		JSONObject jsonObject = new JSONObject(result);
		
		ResponseEntity<T> response = addressServices.getDetails(Map.of(GatewayServiceConstants.LOGGED_IN_USER_ID, entity.getUserId(),
																"recordId", jsonObject.getInt("id")));
		assertTrue(response.getStatusCode().equals(HttpStatus.OK));
		assertTrue(response.getBody().toString().contains("Address Line 1"));
	}
	
	
	@Test
	public void createAddressDetailsAndSuccessValidateWithStatusOk() throws JsonProcessingException, Exception {
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
		addressDto.setUserRole("CUSTOMER");
		String result = mvc.perform(MockMvcRequestBuilders.post("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressDto))
				.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER", entity.getUserId()))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(AddressConstants.RECORD_CREATION_MESSAGE));
		
		JSONObject jsonObject = new JSONObject(result);
		
		Map<String, Object> requestMap = Map.of(GatewayServiceConstants.LOGGED_IN_USER_ID, entity.getUserId(),
																"recordId", jsonObject.getInt("id"));
		String validateResult = mvc.perform(MockMvcRequestBuilders.get("/address/validate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(requestMap))
				.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER", entity.getUserId()))
			).andExpect(MockMvcResultMatchers.status().isOk())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(validateResult.contains(String.valueOf(true)));
	}
	
	@Test
	public void createAddressDetailsAndfailedValidateWithStatusOk() throws JsonProcessingException, Exception {
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
		addressDto.setUserRole("CUSTOMER");
		String result = mvc.perform(MockMvcRequestBuilders.post("/address")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(addressDto))
				.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER", entity.getUserId()))
			).andExpect(MockMvcResultMatchers.status().isCreated())
		     .andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(AddressConstants.RECORD_CREATION_MESSAGE));
		
		JSONObject jsonObject = new JSONObject(result);
		
		Map<String, Object> requestMap = Map.of(GatewayServiceConstants.LOGGED_IN_USER_ID, entity.getUserId(),
																"recordId", 20000);
		String validateResult = mvc.perform(MockMvcRequestBuilders.get("/address/validate")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(requestMap))
				.header(GatewayServiceConstants.TOKEN, setUp("CUSTOMER", entity.getUserId()))
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