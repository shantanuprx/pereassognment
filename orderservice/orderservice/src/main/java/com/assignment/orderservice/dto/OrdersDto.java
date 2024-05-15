package com.assignment.orderservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    private int productId;

	@Min(1)
    private int paymentId;

	@Nonnull
    private String paymentSource;

	@Min(1)
    private int addressId;

	@Nonnull
    private String paymentType;

	@Null
    private String refundStatus;

    @Null
    private String orderStatus;

    @NotNull
    private String paymentStatus;

    @Null
    private Date createTimestamp;

    @Max(0)
    private int createdBy;

}
