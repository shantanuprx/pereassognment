package com.assignment.orderservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.orderservice.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	Optional<Address> findByRecordId(int recordId);
}
