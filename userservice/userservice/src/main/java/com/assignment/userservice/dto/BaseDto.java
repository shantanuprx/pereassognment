package com.assignment.userservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * Base DTO, needs to be implemented by every DTO. Contains info about logged in user
 */
@Setter
@Getter
@ToString
@Data
public class BaseDto {

	private Integer loggedInUserId;
	
	private String token;
	
	private String userRole;
}
