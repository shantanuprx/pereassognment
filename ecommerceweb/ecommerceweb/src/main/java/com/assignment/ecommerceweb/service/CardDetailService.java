package com.assignment.ecommerceweb.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.ecommerceweb.adapters.CardDetailsAdapter;
import com.assignment.ecommerceweb.constants.PaymentConstants;
import com.assignment.ecommerceweb.dto.CardDetailsDto;
import com.assignment.ecommerceweb.dto.ErrorDto;
import com.assignment.ecommerceweb.dto.ResponseDto;
import com.assignment.ecommerceweb.entity.CardDetails;
import com.assignment.ecommerceweb.repository.CardDetailsRepository;
import com.assignment.ecommerceweb.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unchecked")
@Service("card")
@Slf4j
public class CardDetailService<T> extends PaymentOperationService<T> {

	@Autowired
	private CardDetailsRepository cardDetailsRepository;

	@Autowired
	private ResponseUtil<T> responseUtil;

	@Override
	public ResponseEntity<T> getDetails(Map<String, Object> requestData) {
		log.info("Entering getDetails Method at {} ", System.currentTimeMillis());
		try {

			CardDetailsDto cardDetailsDto = new ObjectMapper().convertValue(requestData, CardDetailsDto.class);
			Optional<CardDetails> cardEntityOptional = cardDetailsRepository
					.findByRecordId(cardDetailsDto.getRecordId());
			if (cardEntityOptional.isEmpty()
					|| cardEntityOptional.get().getUser().getUserId() != cardDetailsDto.getUserId()) {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, PaymentConstants.INVALID_CARD_ID),
						HttpStatus.BAD_REQUEST);
			}
			CardDetails cardEntity = cardEntityOptional.get();
			CardDetailsDto cardDto = CardDetailsAdapter.convertEntityToModel(cardEntity);
			log.info("Product {} successfully fetched ", cardEntity.getRecordId());
			return responseUtil.prepareResponse((T) cardDto, HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while fetching address details with exception ", ex);
			return responseUtil
					.prepareResponse(
							(T) new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
									ex.getLocalizedMessage(), System.currentTimeMillis()),
							HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			log.info("Exiting getDetails method at  {} ", System.currentTimeMillis());
		}
	}

	@Override
	public ResponseEntity<T> addDetails(Map<String, Object> requestData) {
		log.info("Entering addDetails Method at {} ", System.currentTimeMillis());
		try {
			CardDetailsDto cardDetailsDto = new ObjectMapper().convertValue(requestData, CardDetailsDto.class);
			CardDetails cardEntityToPersist = CardDetailsAdapter.convertModelToEntityForInsertion(cardDetailsDto);
			cardDetailsRepository.save(cardEntityToPersist);
			log.info("Address {} successfully created by {} ", cardEntityToPersist.getRecordId(),
					cardDetailsDto.getUserId());

			return responseUtil.prepareResponse(
					(T) new ResponseDto(HttpStatus.CREATED, PaymentConstants.RECORD_CREATION_MESSAGE),
					HttpStatus.CREATED);
		} catch (Exception ex) {
			log.error("Exception occurred while adding new address with exception ", ex);
			return responseUtil
					.prepareResponse(
							(T) new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
									ex.getLocalizedMessage(), System.currentTimeMillis()),
							HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			log.info("Exiting addDetails method at  {} ", System.currentTimeMillis());
		}
	}

	@Override
	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) {
		log.info("Entering updateDetails Method at {} ", System.currentTimeMillis());
		try {
			CardDetailsDto cardDetailsDto = new ObjectMapper().convertValue(requestData, CardDetailsDto.class);
			Optional<CardDetails> cardEntityOptional = cardDetailsRepository
					.findByRecordId(cardDetailsDto.getRecordId());
			if (cardEntityOptional.isEmpty()
					|| cardEntityOptional.get().getUser().getUserId() != cardDetailsDto.getUserId()) {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, PaymentConstants.INVALID_CARD_ID),
						HttpStatus.BAD_REQUEST);
			}
			CardDetails cardEntity = cardEntityOptional.get();

			CardDetailsAdapter.mapModelValuesToEntityForUpdate(cardDetailsDto, cardEntity);
			cardDetailsRepository.save(cardEntity);
			log.info("Card {} successfully updated by {} ", cardEntity.getRecordId(), cardDetailsDto.getUserId());
			return responseUtil.prepareResponse(
					(T) new ResponseDto(HttpStatus.OK, PaymentConstants.RECORD_UPDATE_MESSAGE), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while updating card details with exception ", ex);
			return responseUtil
					.prepareResponse(
							(T) new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
									ex.getLocalizedMessage(), System.currentTimeMillis()),
							HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			log.info("Exiting updateDetails method at  {} ", System.currentTimeMillis());
		}
	}

	@Override
	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) {
		log.info("Entering deleteDetails Method at {} ", System.currentTimeMillis());
		try {
			CardDetailsDto cardDetailsDto = new ObjectMapper().convertValue(requestData, CardDetailsDto.class);
			Optional<CardDetails> cardEntityOptional = cardDetailsRepository
					.findByRecordId(cardDetailsDto.getRecordId());
			if (cardEntityOptional.isEmpty()
					|| cardEntityOptional.get().getUser().getUserId() != cardDetailsDto.getUserId()) {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, PaymentConstants.INVALID_CARD_ID),
						HttpStatus.BAD_REQUEST);
			}
			CardDetails cardEntity = cardEntityOptional.get();
			CardDetailsAdapter.mapModelValuesToEntityForUpdate(cardDetailsDto, cardEntity);
			cardDetailsRepository.delete(cardEntity);
			log.info("Address {} successfully deleted by {} ", cardEntity.getRecordId(), cardDetailsDto.getUserId());
			return responseUtil.prepareResponse(
					(T) new ResponseDto(HttpStatus.OK, PaymentConstants.RECORD_UPDATE_MESSAGE), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while deleting card details with exception ", ex);
			return responseUtil
					.prepareResponse(
							(T) new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(),
									ex.getLocalizedMessage(), System.currentTimeMillis()),
							HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			log.info("Exiting deleteDetails method at  {} ", System.currentTimeMillis());
		}
	}

}
