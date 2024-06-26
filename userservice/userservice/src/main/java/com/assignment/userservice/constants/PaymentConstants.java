package com.assignment.userservice.constants;

/**
 * Constant file to manage string literals related to payment service.
 */
public class PaymentConstants {

	private PaymentConstants() {

	}

	public static final String PAYMENT_TYPE = "paymentType";
	public static final String INVALID_RECORD_REQUEST = "Invalid Payment Id or Payment Id doesn't belong to the logged in user";
	public static final String RECORD_CREATION_MESSAGE = "Payment method added successfully";
	public static final String RECORD_UPDATED_SUCCESSFULLY = "Payment method updated successfully";
	public static final String RECORD_DELETED_SUCCESSFULLY = "Payment method deleted successfully";
	public static final String USER_ID_NOT_EXIST = "Invalid User Id";
	public static final String PAYMENT_NOT_ELIGIBLE_FOR_ORDER = "Payment method not eligible for orders";


}
