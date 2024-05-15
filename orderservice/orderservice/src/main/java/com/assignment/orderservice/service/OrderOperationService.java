package com.assignment.orderservice.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.orderservice.adapters.OrdersAdapter;
import com.assignment.orderservice.constants.OrdersConstant;
import com.assignment.orderservice.dto.OrdersDto;
import com.assignment.orderservice.dto.OrdersUpdateDto;
import com.assignment.orderservice.dto.ResponseDto;
import com.assignment.orderservice.entity.Orders;
import com.assignment.orderservice.exception.BadRequestException;
import com.assignment.orderservice.repository.OrdersRepository;
import com.assignment.orderservice.util.OrderValidationUtil;
import com.assignment.orderservice.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unchecked")
@Service("order")
@Slf4j
public class OrderOperationService<T> implements BaseService<T> {

	private static final String OPERATION_NOT_SUPPORTED = "Operation Not Supported";

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private ResponseUtil<T> responseUtil;

	@Value("${order.page.pagesize:10}")
	private int pageSize;
	
	@Autowired
	private OrderValidationUtil orderValidationUtil;

	@Override
	public ResponseEntity<T> getDetails(Map<String, Object> requestData) {
		log.info("Entering getDetails Method at {} ", System.currentTimeMillis());
		try {
			OrdersDto ordersDto = new ObjectMapper().convertValue(requestData, OrdersDto.class);
			Optional<Orders> orderEntityOptional = ordersRepository.findByOrderId(ordersDto.getOrderId());
			if (orderEntityOptional.isEmpty()
					|| orderEntityOptional.get().getUserId() != ordersDto.getLoggedInUserId()) {
				throw new BadRequestException(OrdersConstant.INVALID_RECORD_REQUEST);
			}
			Orders orderEntity = orderEntityOptional.get();
			OrdersDto orderDto = OrdersAdapter.convertEntityToModel(orderEntity);
			log.info("Order details {} successfully fetched ", orderEntity.getOrderId());
			return responseUtil.prepareResponse((T) orderDto, HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while fetching order details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting getDetails method at  {} ", System.currentTimeMillis());
		}
	}

	@Override
	public ResponseEntity<T> addDetails(Map<String, Object> requestData) {
		log.info("Entering addDetails Method at {} ", System.currentTimeMillis());
		try {
			OrdersDto ordersDto = new ObjectMapper().convertValue(requestData, OrdersDto.class);
			orderValidationUtil.validateOrderDetails(ordersDto);
			Orders orderEntity = OrdersAdapter.convertModelToEntityForInsertion(ordersDto);
			ordersRepository.save(orderEntity);
			orderValidationUtil.reduceStockForProduct(orderEntity);
			log.info("Order details {} successfully created ", orderEntity.getOrderId());
			return responseUtil.prepareResponse((T) new ResponseDto(orderEntity.getOrderId(), HttpStatus.CREATED,
					OrdersConstant.RECORD_CREATION_MESSAGE), HttpStatus.CREATED);
		} catch (Exception ex) {
			log.error("Exception occurred while adding order details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting addDetails method at  {} ", System.currentTimeMillis());
		}
	}

	@Override
	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) {
		log.info("Entering updateDetails Method at {} ", System.currentTimeMillis());
		try {
			OrdersUpdateDto ordersDto = new ObjectMapper().convertValue(requestData, OrdersUpdateDto.class);
			Optional<Orders> orderEntityOptional = ordersRepository.findByOrderId(ordersDto.getOrderId());
			if (orderEntityOptional.isEmpty()
					|| orderEntityOptional.get().getUserId() != ordersDto.getLoggedInUserId()) {
				throw new BadRequestException(OrdersConstant.INVALID_RECORD_REQUEST);
			}
			Orders orderEntity = orderEntityOptional.get();
			OrdersAdapter.mapValuesFromModelToEntityForUpdate(orderEntity, ordersDto);
			log.info("Order details {} successfully updated by {}", orderEntity.getOrderId(), orderEntity.getUserId());
			return responseUtil.prepareResponse((T) new ResponseDto(orderEntity.getOrderId(), HttpStatus.OK,
					OrdersConstant.RECORD_UPDATED_SUCCESSFULLY), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while updating order details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting updateDetails method at  {} ", System.currentTimeMillis());
		}
	}

	@Override
	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) {
		return responseUtil.prepareResponse((T) new ResponseDto(0, HttpStatus.BAD_REQUEST, OPERATION_NOT_SUPPORTED),
				HttpStatus.BAD_REQUEST);
	}

}
