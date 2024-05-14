package com.assignment.ecommerceweb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.ecommerceweb.entity.CardDetails;

public interface CardDetailsRepository extends JpaRepository<CardDetails, Integer>{

	public Optional<CardDetails> findByRecordId(int recordId);

}
