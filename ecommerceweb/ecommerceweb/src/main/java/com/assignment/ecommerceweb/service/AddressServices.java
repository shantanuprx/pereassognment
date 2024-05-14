package com.assignment.ecommerceweb.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.ecommerceweb.adapters.AddressAdapter;
import com.assignment.ecommerceweb.constants.AddressConstants;
import com.assignment.ecommerceweb.dto.AddressDto;
import com.assignment.ecommerceweb.dto.ErrorDto;
import com.assignment.ecommerceweb.dto.ResponseDto;
import com.assignment.ecommerceweb.entity.Address;
import com.assignment.ecommerceweb.repository.AddressRepository;
import com.assignment.ecommerceweb.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unchecked")
@Service("address")
@Slf4j
public class AddressServices<T> implements BaseService<T> {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ResponseUtil<T> responseUtil;

	@Override
	public ResponseEntity<T> getDetails(Map<String, Object> requestData) {
		log.info("Entering getDetails Method at {} ", System.currentTimeMillis());
		try {

			AddressDto addressDto = new ObjectMapper().convertValue(requestData, AddressDto.class);

			Optional<Address> addressEntityOptional = addressRepository.findByRecordId(addressDto.getId());
			if (addressEntityOptional.isEmpty() || addressEntityOptional.get().getUser().getUserId() != addressDto.getUserId()) {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, AddressConstants.INVALID_RECORD_REQUEST),
						HttpStatus.BAD_REQUEST);
			}
			Address addressEntity = addressEntityOptional.get();
			addressDto = AddressAdapter.convertEntityToModel(addressEntity);
			log.info("Address {} successfully fetched ", addressEntity.getRecordId());
			return responseUtil.prepareResponse((T) addressDto, HttpStatus.OK);
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
			AddressDto addressDto = new ObjectMapper().convertValue(requestData, AddressDto.class);
			Address addressEntityToPersist = AddressAdapter.convertModelToEntityForInsertion(addressDto);
			addressRepository.save(addressEntityToPersist);

			log.info("Address {} successfully created by {} ", addressEntityToPersist.getRecordId(),
					addressDto.getUserId());
			return responseUtil.prepareResponse(
					(T) new ResponseDto(HttpStatus.CREATED, AddressConstants.RECORD_CREATION_MESSAGE),
					HttpStatus.CREATED);
		} catch (Exception ex) {
			log.error("Exception occurred while adding address details with exception ", ex);
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
			AddressDto addressDto = new ObjectMapper().convertValue(requestData, AddressDto.class);
			Optional<Address> addressEntityOptional = addressRepository.findByRecordId(addressDto.getId());
			if (addressEntityOptional.isEmpty() || addressEntityOptional.get().getUser().getUserId() != addressDto.getUserId()) {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, AddressConstants.INVALID_RECORD_REQUEST),
						HttpStatus.BAD_REQUEST);
			}
			Address addressEntity = addressEntityOptional.get();
			AddressAdapter.convertModelToEntityForUpdate(addressEntity, addressDto);
			addressRepository.save(addressEntity);
			return responseUtil.prepareResponse(
					(T) new ResponseDto(HttpStatus.OK, AddressConstants.RECORD_UPDATED_SUCCESSFULLY), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while updating address details with exception ", ex);
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
			AddressDto addressDto = new ObjectMapper().convertValue(requestData, AddressDto.class);
			Optional<Address> addressEntityOptional = addressRepository.findByRecordId(addressDto.getId());
			if (addressEntityOptional.isEmpty() ||addressEntityOptional.get().getUser().getUserId() != addressDto.getUserId()) {
				return responseUtil.prepareResponse(
						(T) new ResponseDto(HttpStatus.BAD_REQUEST, AddressConstants.INVALID_RECORD_REQUEST),
						HttpStatus.BAD_REQUEST);
			}
			Address addressEntity = addressEntityOptional.get();
			addressRepository.delete(addressEntity);
			return responseUtil.prepareResponse(
					(T) new ResponseDto(HttpStatus.OK, AddressConstants.RECORD_DELETED_SUCCESSFULLY), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while deleting address details with exception ", ex);
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
