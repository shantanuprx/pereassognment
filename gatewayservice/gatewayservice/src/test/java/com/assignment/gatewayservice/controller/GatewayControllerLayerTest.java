package com.assignment.gatewayservice.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.assignment.gatewayservice.GatewayserviceApplication;
import com.assignment.gatewayservice.constants.GatewayServiceConstants;
import com.assignment.gatewayservice.dto.AuthenticationRequestDto;
import com.assignment.gatewayservice.dto.RegistrationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = GatewayserviceApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class GatewayControllerLayerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	@Sql(scripts = "/data.sql")
	public void registerUserWithStatus200() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		RegistrationDto registrationDto = new RegistrationDto();
		registrationDto.setEmailId("kumar93782@gmail.com");
		registrationDto.setDateOfBirth(new Date(System.currentTimeMillis()-60000000));
		registrationDto.setFirstName("Shantnu");
		registrationDto.setLastName("Kumar");
		registrationDto.setMobileNumber("9999999999");
		registrationDto.setPassword("Oracle@123");
		
		String result = mvc
				.perform(MockMvcRequestBuilders.post("/auth/register").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(registrationDto)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(GatewayServiceConstants.RECORD_CREATION_MESSAGE));
	}
	
	@Test
	@Sql(scripts = "/data.sql")
	public void registerAndValidateUserWithStatus200() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		RegistrationDto registrationDto = new RegistrationDto();
		registrationDto.setEmailId("kumar93782@gmail.com");
		registrationDto.setDateOfBirth(new Date(System.currentTimeMillis()-60000000));
		registrationDto.setFirstName("Shantnu");
		registrationDto.setLastName("Kumar");
		registrationDto.setMobileNumber("9999999999");
		registrationDto.setPassword("Oracle@123");
		
		String result = mvc
				.perform(MockMvcRequestBuilders.post("/auth/register").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(registrationDto)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(GatewayServiceConstants.RECORD_CREATION_MESSAGE));
		
		AuthenticationRequestDto authenticationDto = new AuthenticationRequestDto();
		authenticationDto.setEmailId("kumar93782@gmail.com");
		authenticationDto.setPassword("Oracle@123");
		
		String loginResult = mvc
				.perform(MockMvcRequestBuilders.post("/auth/token").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(authenticationDto)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		assertTrue(loginResult.contains(GatewayServiceConstants.TOKEN));
	}
	
	@Test
	@Sql(scripts = "/data.sql")
	public void reRegisterAndValidateUserWithStatus400() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		RegistrationDto registrationDto = new RegistrationDto();
		registrationDto.setEmailId("kumar93782@gmail.com");
		registrationDto.setDateOfBirth(new Date(System.currentTimeMillis()-60000000));
		registrationDto.setFirstName("Shantnu");
		registrationDto.setLastName("Kumar");
		registrationDto.setMobileNumber("9999999999");
		registrationDto.setPassword("Oracle@123");
		
		String result = mvc
				.perform(MockMvcRequestBuilders.post("/auth/register").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(registrationDto)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(GatewayServiceConstants.RECORD_CREATION_MESSAGE));
		
		String erroResult = mvc
				.perform(MockMvcRequestBuilders.post("/auth/register").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(registrationDto)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn().getResponse().getContentAsString();
		assertTrue(erroResult.contains(GatewayServiceConstants.EMAIL_ALREADY_REGISTERED));
	}
	
	@Test
	@Sql(scripts = "/data.sql")
	public void invalidCredsWithStatus401() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		RegistrationDto registrationDto = new RegistrationDto();
		registrationDto.setEmailId("kumar93782@gmail.com");
		registrationDto.setDateOfBirth(new Date(System.currentTimeMillis()-60000000));
		registrationDto.setFirstName("Shantnu");
		registrationDto.setLastName("Kumar");
		registrationDto.setMobileNumber("9999999999");
		registrationDto.setPassword("Oracle@123");
		
		String result = mvc
				.perform(MockMvcRequestBuilders.post("/auth/register").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(registrationDto)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getContentAsString();
		assertTrue(result.contains(GatewayServiceConstants.RECORD_CREATION_MESSAGE));
		
		AuthenticationRequestDto authenticationDto = new AuthenticationRequestDto();
		authenticationDto.setEmailId("kumar93782@gmail.com");
		authenticationDto.setPassword("Oracle@12");
		
		String loginResult = mvc
				.perform(MockMvcRequestBuilders.post("/auth/token").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(authenticationDto)))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized()).andReturn().getResponse().getContentAsString();
		assertTrue(loginResult.contains(GatewayServiceConstants.INVALID_USER_NAME_OR_PASSWORD));
	}
	
	@Test
	@Sql(scripts = "/data.sql")
	public void invalidUserWithStatus401() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		AuthenticationRequestDto authenticationDto = new AuthenticationRequestDto();
		authenticationDto.setEmailId("kumar93782@gmail.com");
		authenticationDto.setPassword("Oracle@12");
		
		String loginResult = mvc
				.perform(MockMvcRequestBuilders.post("/auth/token").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(authenticationDto)))
				.andExpect(MockMvcResultMatchers.status().isUnauthorized()).andReturn().getResponse().getContentAsString();
		assertTrue(loginResult.contains(GatewayServiceConstants.INVALID_USER_NAME_OR_PASSWORD));
	}
}
