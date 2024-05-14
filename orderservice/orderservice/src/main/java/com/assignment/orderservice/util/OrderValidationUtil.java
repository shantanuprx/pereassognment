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

	public boolean validateOrderDetails(OrdersDto ordersDto) {
		Optional<Product> product = productRepository.findByProductId(ordersDto.getProductId());
		if (product.isEmpty() || !"A".equalsIgnoreCase(product.get().getStatus())
				|| product.get().getCurrentStock() == 0) {
			throw new BadRequestException("Product not eligible for orders");
		}
		if("card".equalsIgnoreCase(ordersDto.getPaymentSource())) {
			Optional<CardDetails> cardDetails = cardDetailsRepository.findByRecordId(ordersDto.getPaymentId());
			if(cardDetails.isEmpty() || cardDetails.get().getExpiryDate().before(new Date())) {
				throw new BadRequestException("Payment method not eligible for orders");
			}
		}
		Optional<Address> addressOptional = addressRepository.findByRecordId(ordersDto.getAddressId());
		if(addressOptional.isEmpty()) {
			throw new BadRequestException("Address not eligible for orders");
		}
		return true;
	}

	public void reduceStockForProduct(Orders orderEntity) {
		Product product = productRepository.findByProductId(orderEntity.getProductId()).get();
		product.setCurrentStock(product.getCurrentStock() -1);
		if(product.getCurrentStock()==0) {
			product.setStatus(OrdersConstant.OUT_OF_STOCK_STATUS);
		}
		product.setUpdatedBy(OrdersConstant.SYSTEM_USER_ID);
		product.setUpdatedTimestamp(Timestamp.valueOf(LocalDateTime.now()));
		productRepository.save(product);
	}
}
