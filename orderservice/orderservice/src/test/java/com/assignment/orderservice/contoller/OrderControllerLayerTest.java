package com.assignment.orderservice.contoller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.assignment.orderservice.OrderserviceApplication;
import com.assignment.orderservice.auth.TokenGenService;
import com.assignment.orderservice.constants.GatewayServiceConstants;
import com.assignment.orderservice.constants.OrdersConstant;
import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.dto.OrdersUpdateDto;
import com.assignment.orderservice.entity.Address;
import com.assignment.orderservice.entity.CardDetails;
import com.assignment.orderservice.entity.Product;
import com.assignment.orderservice.entity.User;
import com.assignment.orderservice.repository.AddressRepository;
import com.assignment.orderservice.repository.CardDetailsRepository;
import com.assignment.orderservice.repository.ProductRepository;
import com.assignment.orderservice.repository.UserRepository;
import com.assignment.orderservice.service.OrderOperationService;
import com.assignment.orderservice.util.JedisClientHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = OrderserviceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrderControllerLayerTest<T> {

	@Autowired
	private MockMvc mvc;

	@Value("${jwt.webtoken.secret}")
	private String secreString;

	@Autowired
	private JedisClientHelper clientHelper;

	@Autowired
	private OrderOperationService<T> orderOperationService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AddressRepository addressReposiory;

	@Autowired
	private CardDetailsRepository cardDetailsRepository;

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void createOrderDetailsWithStatusOk() throws JsonProcessingException, Exception {
		User userEnity = new User();
		userEnity.setFirstName("Shantanu");
		userEnity.setMidName(null);
		userEnity.setLastName("Kumar");
		userEnity.setEmail("kumar93782@gmail.com");
		userEnity.setPassword("abcdefg");
		userEnity.setCreatedBy(GatewayServiceConstants.GATEWAY_SERVICE);
		userEnity.setCreatedDate(new Date());
		userEnity.setDateOfBirth(new Date());
		userEnity.setMobileNumber("9521635420");
		userEnity.setStatus(GatewayServiceConstants.ACTIVE_FLAG);
		userEnity.setUserRole(GatewayServiceConstants.CUSTOMER_USER_ROLE);
		userRepository.save(userEnity);

		Address addressEntity = new Address();
		addressEntity.setAddressLine1("Address Line 1");
		addressEntity.setAddressLine2("Address Line 2");
		addressEntity.setCity("City");
		addressEntity.setPincode("302002");
		addressEntity.setState("State");
		addressEntity.setUser(userEnity);
		addressEntity.setCreatedBy(userEnity.getUserId());
		addressEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		addressReposiory.save(addressEntity);

		CardDetails cardDetails = new CardDetails();
		cardDetails.setCardHolderName("SHANTANU KUMAR");
		cardDetails.setCardNumber("12341234124412434");
		cardDetails.setCreatedBy(userEnity.getUserId());
		cardDetails.setCreatedDate(new Date());
		cardDetails.setExpiryDate(new Date(System.currentTimeMillis() + 60000000));
		cardDetails.setUser(userEnity);
		cardDetailsRepository.save(cardDetails);

		Product productEntity = new Product();
		productEntity.setCurrentStock(1);
		productEntity.setPrice(BigDecimal.valueOf(3000.0));
		productEntity.setProductDescription("7800 XT");
		productEntity.setProductName("78000 XT graphic card");
		productEntity.setSeller("Appario");
		productEntity.setSellerAddress("Jaipur");
		productEntity.setStatus("A");
		productEntity.setCreatedBy(userEnity.getUserId());
		productEntity.setCreatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		productRepository.save(productEntity);

		OrdersDto ordersDto = new OrdersDto();
		ordersDto.setProductId(productEntity.getProductId());
		ordersDto.setPaymentId(cardDetails.getRecordId());
		ordersDto.setPaymentSource("card");
		ordersDto.setPaymentType("Online");
		ordersDto.setToken(setUp("CUSTOMER", userEnity.getUserId()));
		ordersDto.setPaymentStatus("Pending");
		ordersDto.setAddressId(addressEntity.getRecordId());

		String result = mvc
				.perform(MockMvcRequestBuilders.post("/order").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(ordersDto)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(OrdersConstant.RECORD_CREATION_MESSAGE));
	}

	@Test
	public void createOrderDetailsWithStatusBadRequestForInvalidPayment() throws JsonProcessingException, Exception {
		User userEnity = new User();
		userEnity.setFirstName("Shantanu");
		userEnity.setMidName(null);
		userEnity.setLastName("Kumar");
		userEnity.setEmail("kumar93782@gmail.com");
		userEnity.setPassword("abcdefg");
		userEnity.setCreatedBy(GatewayServiceConstants.GATEWAY_SERVICE);
		userEnity.setCreatedDate(new Date());
		userEnity.setDateOfBirth(new Date());
		userEnity.setMobileNumber("9521635420");
		userEnity.setStatus(GatewayServiceConstants.ACTIVE_FLAG);
		userEnity.setUserRole(GatewayServiceConstants.CUSTOMER_USER_ROLE);
		userRepository.save(userEnity);

		Address addressEntity = new Address();
		addressEntity.setAddressLine1("Address Line 1");
		addressEntity.setAddressLine2("Address Line 2");
		addressEntity.setCity("City");
		addressEntity.setPincode("302002");
		addressEntity.setState("State");
		addressEntity.setUser(userEnity);
		addressEntity.setCreatedBy(userEnity.getUserId());
		addressEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		addressReposiory.save(addressEntity);

		CardDetails cardDetails = new CardDetails();
		cardDetails.setCardHolderName("SHANTANU KUMAR");
		cardDetails.setCardNumber("12341234124412434");
		cardDetails.setCreatedBy(userEnity.getUserId());
		cardDetails.setCreatedDate(new Date());
		cardDetails.setExpiryDate(new Date(System.currentTimeMillis() - 60000000));
		cardDetails.setUser(userEnity);
		cardDetailsRepository.save(cardDetails);

		Product productEntity = new Product();
		productEntity.setCurrentStock(1);
		productEntity.setPrice(BigDecimal.valueOf(3000.0));
		productEntity.setProductDescription("7800 XT");
		productEntity.setProductName("78000 XT graphic card");
		productEntity.setSeller("Appario");
		productEntity.setSellerAddress("Jaipur");
		productEntity.setStatus("A");
		productEntity.setCreatedBy(userEnity.getUserId());
		productEntity.setCreatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		productRepository.save(productEntity);

		OrdersDto ordersDto = new OrdersDto();
		ordersDto.setProductId(productEntity.getProductId());
		ordersDto.setPaymentId(cardDetails.getRecordId());
		ordersDto.setPaymentSource("card");
		ordersDto.setPaymentType("Online");
		ordersDto.setToken(setUp("CUSTOMER", userEnity.getUserId()));
		ordersDto.setPaymentStatus("Pending");
		ordersDto.setAddressId(addressEntity.getRecordId());

		String result = mvc
				.perform(MockMvcRequestBuilders.post("/order").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(ordersDto)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn().getResponse()
				.getContentAsString();
		assertTrue(result.contains(OrdersConstant.PAYMENT_NOT_ELIGIBLE_FOR_ORDER));
	}

	@Test
	public void createOrderDetailsAndUpdateDetails() throws JsonProcessingException, Exception {
		User userEnity = new User();
		userEnity.setFirstName("Shantanu");
		userEnity.setMidName(null);
		userEnity.setLastName("Kumar");
		userEnity.setEmail("kumar93782@gmail.com");
		userEnity.setPassword("abcdefg");
		userEnity.setCreatedBy(GatewayServiceConstants.GATEWAY_SERVICE);
		userEnity.setCreatedDate(new Date());
		userEnity.setDateOfBirth(new Date());
		userEnity.setMobileNumber("9521635420");
		userEnity.setStatus(GatewayServiceConstants.ACTIVE_FLAG);
		userEnity.setUserRole(GatewayServiceConstants.CUSTOMER_USER_ROLE);
		userRepository.save(userEnity);

		Address addressEntity = new Address();
		addressEntity.setAddressLine1("Address Line 1");
		addressEntity.setAddressLine2("Address Line 2");
		addressEntity.setCity("City");
		addressEntity.setPincode("302002");
		addressEntity.setState("State");
		addressEntity.setUser(userEnity);
		addressEntity.setCreatedBy(userEnity.getUserId());
		addressEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		addressReposiory.save(addressEntity);

		CardDetails cardDetails = new CardDetails();
		cardDetails.setCardHolderName("SHANTANU KUMAR");
		cardDetails.setCardNumber("12341234124412434");
		cardDetails.setCreatedBy(userEnity.getUserId());
		cardDetails.setCreatedDate(new Date());
		cardDetails.setExpiryDate(new Date(System.currentTimeMillis() + 60000000));
		cardDetails.setUser(userEnity);
		cardDetailsRepository.save(cardDetails);

		Product productEntity = new Product();
		productEntity.setCurrentStock(1);
		productEntity.setPrice(BigDecimal.valueOf(3000.0));
		productEntity.setProductDescription("7800 XT");
		productEntity.setProductName("78000 XT graphic card");
		productEntity.setSeller("Appario");
		productEntity.setSellerAddress("Jaipur");
		productEntity.setStatus("A");
		productEntity.setCreatedBy(userEnity.getUserId());
		productEntity.setCreatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		productRepository.save(productEntity);

		OrdersDto ordersDto = new OrdersDto();
		ordersDto.setProductId(productEntity.getProductId());
		ordersDto.setPaymentId(cardDetails.getRecordId());
		ordersDto.setPaymentSource("card");
		ordersDto.setPaymentType("Online");
		ordersDto.setToken(setUp("CUSTOMER", userEnity.getUserId()));
		ordersDto.setPaymentStatus("Pending");
		ordersDto.setAddressId(addressEntity.getRecordId());

		String result = mvc
				.perform(MockMvcRequestBuilders.post("/order").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(ordersDto)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(OrdersConstant.RECORD_CREATION_MESSAGE));

		JSONObject jsonObject = new JSONObject(result);

		OrdersUpdateDto ordersUpdateDto = new OrdersUpdateDto();
		ordersUpdateDto.setOrderId(jsonObject.getInt("id"));
		ordersUpdateDto.setOrderStatus("C");
		ordersUpdateDto.setToken(setUp("CUSTOMER", userEnity.getUserId()));

		String updateResult = mvc
				.perform(MockMvcRequestBuilders.put("/order").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(ordersUpdateDto)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		assertTrue(updateResult.contains(OrdersConstant.RECORD_UPDATED_SUCCESSFULLY));

	}

	@Test
	public void createOrderDetailsAndFetchDetails() throws JsonProcessingException, Exception {
		User userEnity = new User();
		userEnity.setFirstName("Shantanu");
		userEnity.setMidName(null);
		userEnity.setLastName("Kumar");
		userEnity.setEmail("kumar93782@gmail.com");
		userEnity.setPassword("abcdefg");
		userEnity.setCreatedBy(GatewayServiceConstants.GATEWAY_SERVICE);
		userEnity.setCreatedDate(new Date());
		userEnity.setDateOfBirth(new Date());
		userEnity.setMobileNumber("9521635420");
		userEnity.setStatus(GatewayServiceConstants.ACTIVE_FLAG);
		userEnity.setUserRole(GatewayServiceConstants.CUSTOMER_USER_ROLE);
		userRepository.save(userEnity);

		Address addressEntity = new Address();
		addressEntity.setAddressLine1("Address Line 1");
		addressEntity.setAddressLine2("Address Line 2");
		addressEntity.setCity("City");
		addressEntity.setPincode("302002");
		addressEntity.setState("State");
		addressEntity.setUser(userEnity);
		addressEntity.setCreatedBy(userEnity.getUserId());
		addressEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
		addressReposiory.save(addressEntity);

		CardDetails cardDetails = new CardDetails();
		cardDetails.setCardHolderName("SHANTANU KUMAR");
		cardDetails.setCardNumber("12341234124412434");
		cardDetails.setCreatedBy(userEnity.getUserId());
		cardDetails.setCreatedDate(new Date());
		cardDetails.setExpiryDate(new Date(System.currentTimeMillis() + 60000000));
		cardDetails.setUser(userEnity);
		cardDetailsRepository.save(cardDetails);

		Product productEntity = new Product();
		productEntity.setCurrentStock(1);
		productEntity.setPrice(BigDecimal.valueOf(3000.0));
		productEntity.setProductDescription("7800 XT");
		productEntity.setProductName("78000 XT graphic card");
		productEntity.setSeller("Appario");
		productEntity.setSellerAddress("Jaipur");
		productEntity.setStatus("A");
		productEntity.setCreatedBy(userEnity.getUserId());
		productEntity.setCreatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		productRepository.save(productEntity);

		OrdersDto ordersDto = new OrdersDto();
		ordersDto.setProductId(productEntity.getProductId());
		ordersDto.setPaymentId(cardDetails.getRecordId());
		ordersDto.setPaymentSource("card");
		ordersDto.setPaymentType("Online");
		ordersDto.setToken(setUp("CUSTOMER", userEnity.getUserId()));
		ordersDto.setPaymentStatus("Pending");
		ordersDto.setAddressId(addressEntity.getRecordId());

		String result = mvc
				.perform(MockMvcRequestBuilders.post("/order").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(ordersDto)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(OrdersConstant.RECORD_CREATION_MESSAGE));

		JSONObject jsonObject = new JSONObject(result);

		OrdersDto fetchDto = new OrdersDto();
		fetchDto.setOrderId(jsonObject.getInt("id"));
		fetchDto.setOrderStatus("C");
		fetchDto.setToken(setUp("CUSTOMER", userEnity.getUserId()));

		String updateResult = mvc
				.perform(MockMvcRequestBuilders.get("/order").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(fetchDto)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		assertTrue(updateResult.contains("Online"));
	}

	@Test
	public void expiredTokenTestWithStatus401()
			throws JsonProcessingException, UnsupportedEncodingException, Exception {
		OrdersDto fetchDto = new OrdersDto();
		fetchDto.setToken("xhacuisdhnquynvqweruficefrcwnheufgdwu");

		mvc.perform(MockMvcRequestBuilders.get("/order").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(fetchDto)))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	public void invalidTokenTestWithStatus401()
			throws JsonProcessingException, UnsupportedEncodingException, Exception {
		OrdersDto fetchDto = new OrdersDto();
		mvc.perform(MockMvcRequestBuilders.get("/order").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(fetchDto)))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}
	
	@Test
	public void invalidJSONTestWithStatus400()
			throws JsonProcessingException, UnsupportedEncodingException, Exception {
		mvc.perform(MockMvcRequestBuilders.get("/order").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString("{{{{{{{")))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void invalidUsrRoleTestWithStatus401()
			throws JsonProcessingException, UnsupportedEncodingException, Exception {
		OrdersDto fetchDto = new OrdersDto();
		fetchDto.setToken(setUp("ADMIN", 0));
		mvc.perform(MockMvcRequestBuilders.get("/order").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(fetchDto)))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized());
	}

	private String setUp(String userRole, int userId) {
		String token = TokenGenService.generateToken(userId, userRole, "abc@gmail.com", secreString);
		clientHelper.saveKeyPair(token, String.valueOf(true));
		return token;
	}
}
