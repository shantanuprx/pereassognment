package com.assignment.orderservice.adapters;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.assignment.orderservice.constants.OrdersConstant;
import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.dto.OrdersUpdateDto;
import com.assignment.orderservice.entity.Orders;
import com.assignment.orderservice.exception.BadRequestException;

import jakarta.validation.Valid;

@Component
@Validated
public class OrdersAdapter {

	/**
	 * * Converting Entity to model
	 * 
	 * @param orderEntity
	 * @return OrderDto the new order dto
	 */
	public OrdersDto convertEntityToModel(Orders orderEntity) {
		OrdersDto ordersDto = new OrdersDto();
		ordersDto.setAddressId(orderEntity.getAddressId());
		ordersDto.setCreateTimestamp(orderEntity.getCreateTimestamp());
		ordersDto.setOrderId(orderEntity.getOrderId());
		ordersDto.setOrderStatus(orderEntity.getOrderStatus());
		ordersDto.setPaymentId(orderEntity.getPaymentId());
		ordersDto.setPaymentSource(orderEntity.getPaymentSource());
		ordersDto.setPaymentStatus(orderEntity.getPaymentStatus());
		ordersDto.setPaymentType(orderEntity.getPaymentType());
		ordersDto.setProductId(orderEntity.getProductId());
		ordersDto.setRefundStatus(orderEntity.getRefundStatus());
		return ordersDto;
	}

	/**
	 * Converting model to entity for insertion
	 * 
	 * @param ordersDto
	 * @return Orders Entity
	 */
	public Orders convertModelToEntityForInsertion(@Valid OrdersDto ordersDto) {
		Orders orders = new Orders();
		orders.setAddressId(ordersDto.getAddressId());
		orders.setCreatedBy(ordersDto.getLoggedInUserId());
		orders.setCreateTimestamp(ordersDto.getCreateTimestamp());
		orders.setOrderId(ordersDto.getOrderId());
		orders.setOrderStatus(ordersDto.getOrderStatus());
		orders.setPaymentId(ordersDto.getPaymentId());
		orders.setPaymentSource(ordersDto.getPaymentSource());
		orders.setPaymentStatus(ordersDto.getPaymentStatus());
		orders.setPaymentType(ordersDto.getPaymentType());
		orders.setProductId(ordersDto.getProductId());
		orders.setRefundStatus(ordersDto.getRefundStatus());
		orders.setOrderStatus(OrdersConstant.ORDER_PLACED_STATUS);
		orders.setCreatedBy(ordersDto.getLoggedInUserId());
		orders.setCreateTimestamp(new Date());
		orders.setUserId(ordersDto.getLoggedInUserId());
		return orders;
	}

	/**
	 * Update entity for cancellation of order
	 * 
	 * @param orderEntity
	 * @param ordersDto
	 */

	public void mapValuesFromModelToEntityForUpdate(Orders orderEntity, @Valid OrdersUpdateDto ordersDto) {
		if (OrdersConstant.ORDER_CANCELLED_STATUS.equalsIgnoreCase(orderEntity.getOrderStatus())) {
			throw new BadRequestException("Order already Cancelled");
		}
		if (ordersDto.getOrderStatus() != null
				&& OrdersConstant.ORDER_CANCELLED_STATUS.equalsIgnoreCase(ordersDto.getOrderStatus())) {
			orderEntity.setOrderStatus(OrdersConstant.ORDER_CANCELLED_STATUS);
		}
		orderEntity.setUpdatedBy(ordersDto.getLoggedInUserId());
		orderEntity.setUpdateTimestamp(new Date());
	}

}
