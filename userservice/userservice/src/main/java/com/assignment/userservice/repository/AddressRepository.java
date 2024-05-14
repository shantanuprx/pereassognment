package com.assignment.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.userservice.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	Optional<Address> findByRecordId(int recordId);
}
