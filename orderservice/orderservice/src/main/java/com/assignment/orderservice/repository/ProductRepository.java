package com.assignment.orderservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.orderservice.entity.Product;

/**
 * Generic JPA repository to perform CRUD operations on Product table.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Optional<Product> findByProductId(Integer productId);
}
