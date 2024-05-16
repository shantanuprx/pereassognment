package com.assignment.userservice.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.assignment.userservice.adapters.AddressAdapter;
import com.assignment.userservice.constants.AddressConstants;
import com.assignment.userservice.dto.AddressDto;
import com.assignment.userservice.dto.AddressUpdateDto;
import com.assignment.userservice.dto.ResponseDto;
import com.assignment.userservice.entity.Address;
import com.assignment.userservice.exception.BadRequestException;
import com.assignment.userservice.repository.AddressRepository;
import com.assignment.userservice.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
/* *
 * Service class implementation for the address related services
 * getDetails -  for fetching details of a single address
 * addDetails -  for adding new address details.
 * updateDetails - for updating existing address details.
 * deleteDetails - for deleting the address details - permanent delete
 * */
@SuppressWarnings("unchecked")
@Service("address")
@Slf4j
public class AddressServices<T> implements BaseService<T> {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ProfileServices<T> profileServices;

	@Autowired
	private ResponseUtil<T> responseUtil;
	
	@Autowired
	private AddressAdapter addressAdapter;

	@Override
	public ResponseEntity<T> getDetails(Map<String, Object> requestData) {
		log.info("Entering getDetails Method at {} ", System.currentTimeMillis());
		try {
			AddressDto addressDto = new ObjectMapper().convertValue(requestData, AddressDto.class);
			Optional<Address> addressEntityOptional = addressRepository.findByRecordId(addressDto.getRecordId());
			if (addressEntityOptional.isEmpty()
					|| addressEntityOptional.get().getUser().getUserId() != addressDto.getLoggedInUserId()) {
				throw new BadRequestException(AddressConstants.INVALID_RECORD_REQUEST);
			}
			Address addressEntity = addressEntityOptional.get();
			addressDto = addressAdapter.convertEntityToModel(addressEntity);
			log.info("Address {} successfully fetched ", addressEntity.getRecordId());
			return responseUtil.prepareResponse((T) addressDto, HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while fetching address details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting getDetails method at  {} ", System.currentTimeMillis());
		}
	}

	@Override
	public ResponseEntity<T> addDetails(Map<String, Object> requestData) {
		log.info("Entering addDetails Method at {} ", System.currentTimeMillis());
		try {
			AddressDto addressDto = new ObjectMapper().convertValue(requestData, AddressDto.class);
			Address addressEntityToPersist = addressAdapter.convertModelToEntityForInsertion(addressDto,
					profileServices.fetchUserEntity(addressDto.getLoggedInUserId()));
			addressRepository.save(addressEntityToPersist);
			log.info("Address {} successfully created by {} ", addressEntityToPersist.getRecordId(),
					addressDto.getLoggedInUserId());
			return responseUtil.prepareResponse((T) new ResponseDto(addressEntityToPersist.getRecordId(),
					HttpStatus.CREATED, AddressConstants.RECORD_CREATION_MESSAGE), HttpStatus.CREATED);
		} catch (Exception ex) {
			log.error("Exception occurred while adding address details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting addDetails method at  {} ", System.currentTimeMillis());
		}
	}

	@Override
	public ResponseEntity<T> updateDetails(Map<String, Object> requestData) {
		log.info("Entering updateDetails Method at {} ", System.currentTimeMillis());
		try {
			AddressUpdateDto addressDto = new ObjectMapper().convertValue(requestData, AddressUpdateDto.class);
			Optional<Address> addressEntityOptional = addressRepository.findByRecordId(addressDto.getRecordId());
			if (addressEntityOptional.isEmpty()
					|| addressEntityOptional.get().getUser().getUserId() != addressDto.getLoggedInUserId()) {
				throw new BadRequestException(AddressConstants.INVALID_RECORD_REQUEST);
			}
			Address addressEntity = addressEntityOptional.get();
			addressAdapter.convertModelToEntityForUpdate(addressEntity, addressDto);
			addressRepository.save(addressEntity);
			return responseUtil.prepareResponse((T) new ResponseDto(addressDto.getRecordId(), HttpStatus.OK,
					AddressConstants.RECORD_UPDATED_SUCCESSFULLY), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while updating address details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting updateDetails method at  {} ", System.currentTimeMillis());
		}
	}

	@Override
	public ResponseEntity<T> deleteDetails(Map<String, Object> requestData) {
		log.info("Entering deleteDetails Method at {} ", System.currentTimeMillis());
		try {
			AddressUpdateDto addressDto = new ObjectMapper().convertValue(requestData, AddressUpdateDto.class);
			Optional<Address> addressEntityOptional = addressRepository.findByRecordId(addressDto.getRecordId());
			if (addressEntityOptional.isEmpty()
					|| addressEntityOptional.get().getUser().getUserId() != addressDto.getLoggedInUserId()) {
				throw new BadRequestException(AddressConstants.INVALID_RECORD_REQUEST);
			}
			Address addressEntity = addressEntityOptional.get();
			addressRepository.delete(addressEntity);
			return responseUtil.prepareResponse((T) new ResponseDto(addressDto.getRecordId(), HttpStatus.OK,
					AddressConstants.RECORD_DELETED_SUCCESSFULLY), HttpStatus.OK);
		} catch (Exception ex) {
			log.error("Exception occurred while deleting address details with exception ", ex);
			throw ex;
		} finally {
			log.info("Exiting deleteDetails method at  {} ", System.currentTimeMillis());
		}
	}

}
