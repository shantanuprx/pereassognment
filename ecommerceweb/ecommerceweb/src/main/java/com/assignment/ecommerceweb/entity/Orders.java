package com.assignment.ecommerceweb.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private int orderId;

    @Column(name = "PRODUCT_ID")
    private int productId;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "PAYMENT_ID")
    private int paymentId;

    @Column(name = "PAYMENT_SOURCE")
    private String paymentSource;

    @Column(name = "ADDRESS_ID")
    private int addressId;

    @Column(name = "PAYMENT_TYPE", nullable = false)
    private String paymentType;

    @Column(name = "REFUND_STATUS")
    private String refundStatus;

    @Column(name = "ORDER_STATUS")
    private String orderStatus;

    @Column(name = "PAYMENT_STATUS")
    private String paymentStatus;

    @Column(name = "CREATE_TIMESTAMP")
    private Date createTimestamp;

    @Column(name = "UPDATE_TIMESTAMP")
    private Date updateTimestamp;

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

}
