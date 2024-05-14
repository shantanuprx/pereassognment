package com.assignment.gatewayservice.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@RedisHash("User")
@Getter
@Setter
@AllArgsConstructor
public class RedisUserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5736743804739748405L;
	
	@Id
	private String sessionId;

	private int userId;
	
	private String role;
}
