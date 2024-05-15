package com.assignment.gatewayservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.gatewayservice.entity.User;
/**
 * Generic CRUD repository for USER entity.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);
	
	/**
	 * Checks if email id is already registered or not
	 * 
	 * @param email
	 * @return boolean 
	 */
	boolean existsByEmail(String email);

}
