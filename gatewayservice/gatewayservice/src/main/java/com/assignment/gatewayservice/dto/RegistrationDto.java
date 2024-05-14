package com.assignment.gatewayservice.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationDto {

	@NotEmpty
	@Size(max = 50, message = "Max length for first name is 50")
    private String firstName;

	@Size(max = 50, message = "Max length for middle name is 50")
    private String midName;

	@Size(max = 50, message = "Max length for last name is 50")
    private String lastName;

	@NotEmpty
	@Email
    private String emailId;

	@NotEmpty
	@Pattern(regexp = "^[6-9][0-9]{9}", message = "Not a valid mobile number")
    private String mobileNumber;

	@Past(message = "Date of birth should not be of future")
    private Date dateOfBirth;
    
	@NotEmpty
//	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message="Minimum eight characters, at least one letter and one number")
    private String password;

}
