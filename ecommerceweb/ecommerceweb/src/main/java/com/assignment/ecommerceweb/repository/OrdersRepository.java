package com.assignment.ecommerceweb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.ecommerceweb.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer>{

	Optional<Orders> findByOrderIdAndUserId(int orderId, int userId);

	List<Orders> findAllByUserId(int userId, Pageable pageRequest);
}
