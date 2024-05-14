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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USER", schema = "PEREASSIGNMENT")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "USER_FIRST_NAME")
    private String firstName;

    @Column(name = "USER_MID_NAME")
    private String midName;

    @Column(name = "USER_LAST_NAME")
    private String lastName;

    @Column(name = "USER_PASSWORD")
    private String password;

    @Column(name = "EMAIL_ID")
    private String email;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;

    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;

    @Column(name = "PREFERRED_PAYMENT_METHOD")
    private String preferredPaymentMethod;

    @Column(name = "PREFERRED_PAYMENT_METHOD_ID")
    private int preferredPaymentMethodId;

    @Column(name = "DEFAULT_ADDRESS")
    private String defaultAddress;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "LAST_LOGIN_DATE")
    private Date lastLoginDate;

    @Column(name = "LAST_LOGIN_IP_ADDRESS")
    private String lastLoginIpAddress;

    @Column(name = "ROLE")
    private String userRole;
}


