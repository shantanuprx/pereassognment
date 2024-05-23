package com.assignment.apispecservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO class to map registration fields.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class RegistrationDto {

	@NotEmpty
	@Size(max = 50, message = "Max length for first name is 50")
	@Schema(example = "Shantanu")
    private String firstName;

	@Size(max = 50, message = "Max length for middle name is 50")
    private String midName;

	@Schema(example = "Kumar")
	@Size(max = 50, message = "Max length for last name is 50")
    private String lastName;

	@NotEmpty
	@Email
	@Size(max = 100)
	@Schema(example = "kumar937@gmail.com")
    private String emailId;

	@NotEmpty
	@Pattern(regexp = "^[6-9][0-9]{9}", message = "Not a valid mobile number")
	@Schema(example = "9521635421")
    private String mobileNumber;

	@Past(message = "Date of birth should not be of future")
	@JsonSerialize(as = Date.class)
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "IST")
	@Schema(example = "12/12/1988")
	@NotNull
	private Date dateOfBirth;
    
	@NotEmpty
	@Schema(example = "Oracle@123")
    private String password;

}
