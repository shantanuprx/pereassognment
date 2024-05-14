package com.assignment.ecommerceweb.entity;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CARD_DETAILS")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class CardDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private int recordId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user.userId")
    private User user;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "expiry_date")
    private Date expiryDate;

    @Column(name = "card_holder_name")
    private String cardHolderName;

    @Column(name = "CREATE_TIMESTAMP")
    private Timestamp createdDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "UPDATE_TIMESTAMP")
    private Timestamp updatedDate;

    @Column(name = "updated_by")
    private String updatedBy;

}
