package com.assignment.ecommerceweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.ecommerceweb.entity.Product;

/**
 * Generic JPA repository to perform CRUD operations on Product table.
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
