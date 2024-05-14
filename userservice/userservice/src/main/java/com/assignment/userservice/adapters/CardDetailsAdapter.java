package com.assignment.userservice.adapters;

import java.util.Date;

import com.assignment.userservice.dto.CardDetailsDto;
import com.assignment.userservice.dto.CardDetailsUpdateDto;
import com.assignment.userservice.entity.CardDetails;
import com.assignment.userservice.entity.User;

public class CardDetailsAdapter {

	public static CardDetailsDto convertEntityToModel(CardDetails productEntity) {
		CardDetailsDto cardDetails = new CardDetailsDto();
		cardDetails.setCardHolderName(productEntity.getCardHolderName());
		cardDetails.setCardNumber(productEntity.getCardNumber());
		cardDetails.setExpiryDate(productEntity.getExpiryDate());
		cardDetails.setRecordId(productEntity.getRecordId());
		return cardDetails;
	}

	public static CardDetails convertModelToEntityForInsertion(CardDetailsDto cardDetailsDto, User user) {
		CardDetails cardDetails = new CardDetails();
		cardDetails.setCardHolderName(cardDetailsDto.getCardHolderName());
		cardDetails.setCardNumber(cardDetailsDto.getCardNumber());
		cardDetails.setCreatedBy(user.getUserId());
		cardDetails.setCreatedDate(new Date());
		cardDetails.setExpiryDate(cardDetailsDto.getExpiryDate());
		cardDetails.setUser(user);
		return cardDetails;
	}

	public static void mapModelValuesToEntityForUpdate(CardDetailsUpdateDto cardDetailsDto, CardDetails cardEntity) {
		if(cardDetailsDto.getCardHolderName()!=null) {
			cardEntity.setCardHolderName(cardDetailsDto.getCardHolderName());
		}
		
		if(cardDetailsDto.getCardNumber()!=null) {
			cardEntity.setCardNumber(cardDetailsDto.getCardNumber());
		}
		
		if(cardDetailsDto.getExpiryDate() != null) {
			cardEntity.setExpiryDate(cardDetailsDto.getExpiryDate());
		}
		
		cardEntity.setUpdatedBy(cardDetailsDto.getLoggedInUserId());
		cardEntity.setUpdatedDate(new Date());
	}

}
