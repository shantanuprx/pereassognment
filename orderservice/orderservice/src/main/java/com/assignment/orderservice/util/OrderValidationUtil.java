package com.assignment.orderservice.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.assignment.orderservice.constants.OrdersConstant;
import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.entity.Address;
import com.assignment.orderservice.entity.CardDetails;
import com.assignment.orderservice.entity.Orders;
import com.assignment.orderservice.entity.Product;
import com.assignment.orderservice.exception.BadRequestException;
import com.assignment.orderservice.repository.AddressRepository;
import com.assignment.orderservice.repository.CardDetailsRepository;
import com.assignment.orderservice.repository.ProductRepository;

@Component
public class OrderValidationUtil {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private CardDetailsRepository cardDetailsRepository;

	@Autowired
	private ProductRepository productRepository;

	/**
	 * Checks and informs whether order could be placed or not
	 * Valid Address
	 * Valid Product
	 * Valid payment 
	 * Above points are checked.
	 * 
	 * @param ordersDto
	 * @return boolean 
	 */
	public boolean validateOrderDetails(OrdersDto ordersDto) {
		Optional<Product> product = productRepository.findByProductId(ordersDto.getProductId());
		if (product.isEmpty() || !"A".equalsIgnoreCase(product.get().getStatus())
				|| product.get().getCurrentStock() == 0 || "Y".equalsIgnoreCase(product.get().getIsDeleted())) {
			throw new BadRequestException(OrdersConstant.PRODUCT_NOT_ELIGIBLE_FOR_ORDER);
		}
		if ("card".equalsIgnoreCase(ordersDto.getPaymentSource())) {
			Optional<CardDetails> cardDetails = cardDetailsRepository.findByRecordId(ordersDto.getPaymentId());
			if (cardDetails.isEmpty() || cardDetails.get().getExpiryDate().before(new Date())) {
				throw new BadRequestException(OrdersConstant.PAYMENT_NOT_ELIGIBLE_FOR_ORDER);
			}
		} else {
			throw new BadRequestException(OrdersConstant.PAYMENT_TYPE_NOT_ALLOWED);
		}

		Optional<Address> addressOptional = addressRepository.findByRecordId(ordersDto.getAddressId());
		if (addressOptional.isEmpty()) {
			throw new BadRequestException(OrdersConstant.ADDRESS_NOT_ELIGIBLE_FOR_ORDER);
		}
		return true;
	}

	/**
	 * Reduce the stock of product for which order is place.
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
