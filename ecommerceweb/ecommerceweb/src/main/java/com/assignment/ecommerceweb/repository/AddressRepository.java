package com.assignment.ecommerceweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.ecommerceweb.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	Optional<Address> findByRecordId(int recordId);
}
