package com.assignment.orderservice.constants;
/**
 * Constant file to manage string literals related to order service.
 */
public class OrdersConstant {

	private OrdersConstant() {

	}

	public static final String INVALID_RECORD_REQUEST = "Invalid Order Id or Order doesn't belong to the logged in user";
	public static final String RECORD_CREATION_MESSAGE = "Order created successfully";
	public static final String RECORD_UPDATED_SUCCESSFULLY = "Order updated successfully";
	public static final String RECORD_DELETED_SUCCESSFULLY = "Order cancelled successfully";
	public static final String USER_ID_NOT_EXIST = "Invalid User Id";
	public static final String ORDER_PLACED_STATUS = "P";
	public static final String ORDER_CANCELLED_STATUS = "C";
	public static final String OUT_OF_STOCK_STATUS = "O";
	public static final String STOCK_AVAILABLE_STATUS = "A";
	public static final int SYSTEM_USER_ID = -1000;
	public static final String PRODUCT_NOT_ELIGIBLE_FOR_ORDER = "Product not eligible for orders";
	public static final String PAYMENT_NOT_ELIGIBLE_FOR_ORDER = "Payment method not eligible for orders";
	public static final String ADDRESS_NOT_ELIGIBLE_FOR_ORDER = "Address not eligible for orders";
	public static final String PAYMENT_TYPE_NOT_ALLOWED = "Payment type not allowed";
	public static final String SERVICE_UNAVAILABLE = "Service unavailable - Order could not be processed at the moment";

}
