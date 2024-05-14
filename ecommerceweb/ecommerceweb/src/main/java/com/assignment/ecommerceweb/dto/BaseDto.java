package com.assignment.ecommerceweb.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Data
public class BaseDto {

	private int loggedInUser;
	
	private String token;
}
