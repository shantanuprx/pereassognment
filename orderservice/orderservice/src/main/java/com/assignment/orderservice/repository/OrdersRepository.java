package com.assignment.orderservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.orderservice.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	Optional<Orders> findByOrderId(int orderId);

	List<Orders> findAllByUserId(int userId, Pageable pageRequest);

}
