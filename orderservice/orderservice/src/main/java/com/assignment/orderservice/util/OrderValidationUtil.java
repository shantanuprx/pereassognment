package com.assignment.orderservice.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.orderservice.constants.OrdersConstant;
import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.entity.Orders;
import com.assignment.orderservice.entity.Product;
import com.assignment.orderservice.exception.BadRequestException;
import com.assignment.orderservice.repository.ProductRepository;
import com.assignment.orderservice.service.AddressService;
import com.assignment.orderservice.service.PaymentService;
import com.assignment.orderservice.service.ProductService;

@Component
public class OrderValidationUtil {

	@Autowired
	private ProductService productService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private ProductRepository productRepository;

	/**
	 * Checks and informs whether order could be placed or not Valid Address Valid
	 * Product Valid payment Above points are checked.
	 * 
	 * @param ordersDto
	 * @return boolean
	 * @throws Exception
	 */
	public boolean validateOrderDetails(OrdersDto ordersDto) throws Exception {
		String productValidationError = productService.validateProduct(ordersDto);
		if (productValidationError != null) {
			throw new BadRequestException(productValidationError);
		}

		String paymentValidationError = paymentService.validatePayment(ordersDto);
		if (paymentValidationError != null) {
			throw new BadRequestException(paymentValidationError);
		}

		String addressValidationError = addressService.validateAddress(ordersDto);
		if (addressValidationError != null) {
			throw new BadRequestException(addressValidationError);
		}
		return true;
	}

	/**
	 * Reduce the stock of product for which order is place.
	 * 
	 * @param orderEntity
	 */
	public void reduceStockForProduct(Orders orderEntity) {
		Product product = productRepository.findByProductId(orderEntity.getProductId()).get();
		product.setCurrentStock(product.getCurrentStock() - 1);
		if (product.getCurrentStock() == 0) {
			product.setStatus(OrdersConstant.OUT_OF_STOCK_STATUS);
		}
		product.setUpdatedBy(OrdersConstant.SYSTEM_USER_ID);
		product.setUpdatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		productRepository.save(product);
	}

	/**
	 * increase the stock of product for which order is cancelled.
	 * 
	 * @param orderEntity
	 */
	public void increaseStockValue(Orders orderEntity) {
		Product product = productRepository.findByProductId(orderEntity.getProductId()).get();
		if (product.getCurrentStock() == 0) {
			product.setStatus(OrdersConstant.STOCK_AVAILABLE_STATUS);
		}
		product.setCurrentStock(product.getCurrentStock() + 1);
		product.setUpdatedBy(OrdersConstant.SYSTEM_USER_ID);
		product.setUpdatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		productRepository.save(product);
	}
}
