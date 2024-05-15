package com.assignment.userservice.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.userservice.adapters.CardDetailsAdapter;
import com.assignment.userservice.constants.PaymentConstants;
import com.assignment.userservice.dto.CardDetailsDto;
import com.assignment.userservice.dto.CardDetailsUpdateDto;
import com.assignment.userservice.dto.ResponseDto;
import com.assignment.userservice.entity.CardDetails;
import com.assignment.userservice.exception.BadRequestException;
import com.assignment.userservice.repository.CardDetailsRepository;
import com.assignment.userservice.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/* *
 * Service class implementation for the card related services
 * getDetails -  for fetching details of a single card
 * addDetails -  for adding new card details.
 * updateDetails - for updating existing card details.
 * deleteDetails - for deleting the card details - permanent delete
 * */
@SuppressWarnings("unchecked")
@Service("card")
@Slf4j
public class CardDetailService<T> extends PaymentOperationService<T> {

	@Autowired
	private CardDetailsRepository cardDetailsRepository;

	@Autowired
	private ResponseUtil<T> responseUtil;

	@Autowired
	private ProfileServices<T> profileServices;

	@Override
	public ResponseEntity<T> getDetails(Map<String, Object> requestData) {
		log.info("Entering getDetails Method at {} ", System.currentTimeMillis());
		try {
			CardDetailsDto cardDetailsDto = new ObjectMapper().convertValue(requestData, CardDetailsDto.class);
			Optional<CardDetails> cardEntityOptional = cardDetailsRepository
					.findByRecordId(cardDetailsDto.getRecordId());
			if (cardEntityOptional.isEmpty()
					|| cardEntityOptional.get().getUser().getUserId() != cardDetailsDto.getLoggedInUserId()) {
				throw new BadRequestException(PaymentConstants.INVALID_RECORD_REQUEST);
			}
			CardDetails cardEntity = cardEntityOptional.get();
			CardDetailsDto cardDto = CardDetailsAdapter.convertEntityToModel(cardEntity);
			log.info("Card details {} successfully fetched ", cardEntity.getRecordId());
			return responseUtil.prepareResponse((T) cardDto, HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while fetching card details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting getDetails method at  {} ", System.currentTimeMillis());
		}
	}

	@Override
	public ResponseEntity<T> addDetails(Map<String, Object> requestData) {
		log.info("Entering addDetails Method at {} ", System.currentTimeMillis());
		try {
			CardDetailsDto cardDetailsDto = new ObjectMapper().convertValue(requestData, CardDetailsDto.class);
			CardDetails cardEntityToPersist = CardDetailsAdapter.convertModelToEntityForInsertion(cardDetailsDto,
					profileServices.fetchUserEntity(cardDetailsDto.getLoggedInUserId()));
			cardDetailsRepository.save(cardEntityToPersist);
			log.info("Card details {} successfully created by {} ", cardEntityToPersist.getRecordId(),
					cardDetailsDto.getLoggedInUserId());
			return responseUtil.prepareResponse((T) new ResponseDto(cardEntityToPersist.getRecordId(),
					HttpStatus.CREATED, PaymentConstants.RECORD_CREATION_MESSAGE), HttpStatus.CREATED);
		} catch (Exception ex) {
			log.error("Exception occurred while adding new card details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting addDetails method at  {} ", System.currentTimeMillis());
		}
	}

	@Override
	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) {
		log.info("Entering updateDetails Method at {} ", System.currentTimeMillis());
		try {
			CardDetailsUpdateDto cardDetailsDto = new ObjectMapper().convertValue(requestData, CardDetailsUpdateDto.class);
			Optional<CardDetails> cardEntityOptional = cardDetailsRepository
					.findByRecordId(cardDetailsDto.getRecordId());
			if (cardEntityOptional.isEmpty()
					|| cardEntityOptional.get().getUser().getUserId() != cardDetailsDto.getLoggedInUserId()) {
				throw new BadRequestException(PaymentConstants.INVALID_RECORD_REQUEST);
			}
			CardDetails cardEntity = cardEntityOptional.get();
			CardDetailsAdapter.mapModelValuesToEntityForUpdate(cardDetailsDto, cardEntity);
			cardDetailsRepository.save(cardEntity);
			log.info("Card details {} successfully updated by {} ", cardEntity.getRecordId(),
					cardDetailsDto.getUserId());
			return responseUtil.prepareResponse((T) new ResponseDto(cardEntity.getRecordId(), HttpStatus.OK,
					PaymentConstants.RECORD_UPDATED_SUCCESSFULLY), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while updating card details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting updateDetails method at  {} ", System.currentTimeMillis());
		}
	}

	@Override
	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) {
		log.info("Entering deleteDetails Method at {} ", System.currentTimeMillis());
		try {
			CardDetailsUpdateDto cardDetailsDto = new ObjectMapper().convertValue(requestData, CardDetailsUpdateDto.class);
			Optional<CardDetails> cardEntityOptional = cardDetailsRepository
					.findByRecordId(cardDetailsDto.getRecordId());
			if (cardEntityOptional.isEmpty()
					|| cardEntityOptional.get().getUser().getUserId() != cardDetailsDto.getLoggedInUserId()) {
				throw new BadRequestException(PaymentConstants.INVALID_RECORD_REQUEST);
			}
			CardDetails cardEntity = cardEntityOptional.get();
			CardDetailsAdapter.mapModelValuesToEntityForUpdate(cardDetailsDto, cardEntity);
			cardDetailsRepository.delete(cardEntity);
			log.info("Card Details {} successfully deleted by {} ", cardEntity.getRecordId(),
					cardDetailsDto.getUserId());
			return responseUtil.prepareResponse((T) new ResponseDto(cardEntity.getRecordId(), HttpStatus.OK,
					PaymentConstants.RECORD_DELETED_SUCCESSFULLY), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while deleting card details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting deleteDetails method at  {} ", System.currentTimeMillis());
		}
	}
}