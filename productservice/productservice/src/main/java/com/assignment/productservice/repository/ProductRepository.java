package com.assignment.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.productservice.entity.Product;

/**
 * Generic JPA repository to perform CRUD operations on Product table.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
