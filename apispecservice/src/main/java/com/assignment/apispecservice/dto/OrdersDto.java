package com.assignment.apispecservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class OrdersDto extends BaseDto {

	private int orderId;

	@Min(1)
	@Schema(example = "1", description = "Product for which user want to place the order")
    private int productId;

	@Min(1)
	@Schema(example = "1", description = "Payment using which user want to place the order")
    private int paymentId;

	@NotNull
	@Schema(example = "card", description = "Payment type using which user want to place the order", allowableValues = {"card"})
    private String paymentSource;

	@Min(1)
	@Schema(example = "1", description = "Address at which user want to receive the order")
    private int addressId;

	@NotNull
	@Schema(example = "Online", allowableValues = {"Online", "Cash on delievery"})
    private String paymentType;

    @Null
    @Schema(example = "A", allowableValues = {"A -active", "C - cancelled"})
    private String orderStatus;

    @NotNull
    @Schema(example = "Pending")
    private String paymentStatus;
    
    @NotBlank
	@Schema(example = "JWT Token from auth service")
	private String token;
}
