package com.assignment.apispecservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * DTO class to map update fields.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersUpdateDto extends BaseDto {

	@Min(1)
	@Schema(example = "1")
	private int orderId;

	@Nonnull
	@Schema(example = "C", allowableValues = {"C - Cancel order"})
    private String orderStatus;
	
	@NotBlank
	@Schema(example = "JWT Token from auth service")
	private String token;

}
