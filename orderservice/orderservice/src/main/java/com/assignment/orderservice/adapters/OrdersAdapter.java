package com.assignment.orderservice.adapters;

import java.util.Date;

import com.assignment.orderservice.constants.OrdersConstant;
import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.dto.OrdersUpdateDto;
import com.assignment.orderservice.entity.Orders;

public class OrdersAdapter {

	public static OrdersDto convertEntityToModel(Orders orderEntity) {
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

	public static Orders convertModelToEntityForInsertion(OrdersDto ordersDto) {
		Orders orders = new Orders();
		orders.setAddressId(ordersDto.getAddressId());
		orders.setCreatedBy(ordersDto.getCreatedBy());
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

	public static void mapValuesFromModelToEntityForUpdate(Orders orderEntity, OrdersUpdateDto ordersDto) {
		if (ordersDto.getOrderStatus() != null
				&& OrdersConstant.ORDER_CANCELLED_STATUS.equalsIgnoreCase(ordersDto.getOrderStatus())) {
			orderEntity.setOrderStatus(OrdersConstant.ORDER_CANCELLED_STATUS);
		}
		orderEntity.setUpdatedBy(ordersDto.getLoggedInUserId());
		orderEntity.setUpdateTimestamp(new Date());
	}

}