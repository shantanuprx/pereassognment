package com.assignment.orderservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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

    private int productId;

    private int paymentId;

    private String paymentSource;

    private int addressId;

    private String paymentType;

    private String refundStatus;

    private String orderStatus;

    private String paymentStatus;

    private Date createTimestamp;

    private int createdBy;

}
