package com.assignment.orderservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.orderservice.entity.CardDetails;

public interface CardDetailsRepository extends JpaRepository<CardDetails, Integer>{

	public Optional<CardDetails> findByRecordId(int recordId);

}
