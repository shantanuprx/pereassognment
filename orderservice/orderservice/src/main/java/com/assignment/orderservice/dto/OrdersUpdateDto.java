package com.assignment.orderservice.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
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
	private int orderId;

	@Nonnull
    private String orderStatus;

}
