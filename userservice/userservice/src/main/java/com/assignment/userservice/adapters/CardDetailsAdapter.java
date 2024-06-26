package com.assignment.userservice.adapters;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.assignment.userservice.dto.CardDetailsDto;
import com.assignment.userservice.dto.CardDetailsUpdateDto;
import com.assignment.userservice.entity.CardDetails;
import com.assignment.userservice.entity.User;

import jakarta.validation.Valid;

@Validated
@Component
public class CardDetailsAdapter {

	/**
	 * Maps entity to dto
	 * 
	 * @param productEntity
	 * @return new CardDetailsDto
	 */
	public CardDetailsDto convertEntityToModel(CardDetails productEntity) {
		CardDetailsDto cardDetails = new CardDetailsDto();
		cardDetails.setCardHolderName(productEntity.getCardHolderName());
		cardDetails.setCardNumber(productEntity.getCardNumber());
		cardDetails.setExpiryDate(productEntity.getExpiryDate());
		cardDetails.setRecordId(productEntity.getRecordId());
		return cardDetails;
	}

	/**
	 * Maps Dto to entity
	 * 
	 * @param cardDetailsDto
	 * @param user
	 * @return new CardDetails Entity
	 */

	public CardDetails convertModelToEntityForInsertion(@Valid CardDetailsDto cardDetailsDto, User user) {
		CardDetails cardDetails = new CardDetails();
		cardDetails.setCardHolderName(cardDetailsDto.getCardHolderName());
		cardDetails.setCardNumber(cardDetailsDto.getCardNumber());
		cardDetails.setCreatedBy(user.getUserId());
		cardDetails.setCreatedDate(new Date());
		cardDetails.setExpiryDate(cardDetailsDto.getExpiryDate());
		cardDetails.setUser(user);
		return cardDetails;
	}

	/**
	 * Maps dto values to entity
	 * 
	 * @param cardDetailsDto
	 * @param cardEntity
	 */
	public void mapModelValuesToEntityForUpdate(@Valid CardDetailsUpdateDto cardDetailsDto, CardDetails cardEntity) {
		if (cardDetailsDto.getCardHolderName() != null) {
			cardEntity.setCardHolderName(cardDetailsDto.getCardHolderName());
		}

		if (cardDetailsDto.getCardNumber() != null) {
			cardEntity.setCardNumber(cardDetailsDto.getCardNumber());
		}

		if (cardDetailsDto.getExpiryDate() != null) {
			cardEntity.setExpiryDate(cardDetailsDto.getExpiryDate());
		}

		cardEntity.setUpdatedBy(cardDetailsDto.getLoggedInUserId());
		cardEntity.setUpdatedDate(new Date());
	}

}
