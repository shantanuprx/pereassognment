create database pereassignment;

use pereassignment;

CREATE TABLE USER (
    USER_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_FIRST_NAME VARCHAR(50),
    USER_MID_NAME VARCHAR(50),
    USER_LAST_NAME VARCHAR(50),
    USER_PASSWORD VARCHAR(100),
    EMAIL_ID VARCHAR(100),
    MOBILE_NUMBER VARCHAR(15),
    DATE_OF_BIRTH DATE,
    PREFERRED_PAYMENT_METHOD VARCHAR(50),
    PREFERRED_PAYMENT_METHOD_ID INT,
    DEFAULT_ADDRESS VARCHAR(255),
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CREATED_BY VARCHAR(50) NOT NULL,
    UPDATED_DATE TIMESTAMP,
    UPDATED_BY VARCHAR(50),
    STATUS VARCHAR(100),
    LAST_LOGIN_DATE TIMESTAMP,
    LAST_LOGIN_IP_ADDRESS VARCHAR(50)
);
ALTER TABLE USER MODIFY ROLE VARCHAR(10) DEFAULT 'CUSTOMER';

CREATE TABLE PRODUCTS (
    PRODUCT_ID INT AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_NAME VARCHAR(100),
    PRODUCT_DESCRIPTION TEXT,
    CURRENT_STOCK INT,
    STATUS VARCHAR(100),
    SELLER VARCHAR(100),
    IS_DELETED BOOLEAN DEFAULT false,
    SELLER_ADDRESS VARCHAR(255),
    PRICE DECIMAL(10, 2),
    OLD_PRICE DECIMAL(10, 2),
    CREATED_TIMESTAMP TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_TIMESTAMP TIMESTAMP,
    CREATED_BY VARCHAR(50) NOT NULL,
    UPDATED_BY VARCHAR(50)
);


CREATE TABLE CARD_DETIALS (
    RECORD_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID INT,
    CARD_NUMBER VARCHAR(16),
    EXPIRY_DATE DATE,
    CARD_HOLDER_NAME VARCHAR(100),
    CREATE_TIMESTAMP TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATE_TIMESTAMP TIMESTAMP,
    CREATED_BY VARCHAR(50) NOT NULL,
	UPDATED_BY VARCHAR(50),
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)
);

CREATE TABLE ADDRESS (
    RECORD_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID INT,
    ADDRESS_LINE_1 VARCHAR(255) NOT NULL,
    ADDRESS_LINE_2 VARCHAR(255),
    CITY VARCHAR(100),
    STATE VARCHAR(100),
    PINCODE VARCHAR(20),
    CREATE_TIMESTAMP TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATE_TIMESTAMP TIMESTAMP,
    CREATED_BY VARCHAR(50) NOT NULL,
	UPDATED_BY VARCHAR(50),
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)
);


CREATE TABLE ORDERS (
    ORDER_ID INT AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_ID INT,
    USER_ID INT,
    PAYMENT_ID INT,
    PAYMENT_SOURCE VARCHAR(10),
    ADDRESS_ID INT,
    PAYMENT_TYPE varchar(100) NOT NULL,
    REFUND_STATUS VARCHAR(50),
    ORDER_STATUS VARCHAR(50),
    PAYMENT_STATUS VARCHAR(50),
    CREATE_TIMESTAMP TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATE_TIMESTAMP TIMESTAMP,
    CREATED_BY VARCHAR(50) NOT NULL,
	UPDATED_BY VARCHAR(50),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS(PRODUCT_ID),
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
    FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS(RECORD_ID)
);



create database pereassignmenttest;

use pereassignmenttest;

CREATE TABLE USER (
    USER_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_FIRST_NAME VARCHAR(50),
    USER_MID_NAME VARCHAR(50),
    USER_LAST_NAME VARCHAR(50),
    USER_PASSWORD VARCHAR(100),
    EMAIL_ID VARCHAR(100),
    MOBILE_NUMBER VARCHAR(15),
    DATE_OF_BIRTH DATE,
    PREFERRED_PAYMENT_METHOD VARCHAR(50),
    PREFERRED_PAYMENT_METHOD_ID INT,
    DEFAULT_ADDRESS VARCHAR(255),
    CREATED_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CREATED_BY VARCHAR(50) NOT NULL,
    UPDATED_DATE TIMESTAMP,
    UPDATED_BY VARCHAR(50),
    STATUS VARCHAR(100),
    LAST_LOGIN_DATE TIMESTAMP,
    LAST_LOGIN_IP_ADDRESS VARCHAR(50)
);
ALTER TABLE USER MODIFY ROLE VARCHAR(10) DEFAULT 'CUSTOMER';

CREATE TABLE PRODUCTS (
    PRODUCT_ID INT AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_NAME VARCHAR(100),
    PRODUCT_DESCRIPTION TEXT,
    CURRENT_STOCK INT,
    STATUS VARCHAR(100),
    SELLER VARCHAR(100),
    IS_DELETED BOOLEAN DEFAULT false,
    SELLER_ADDRESS VARCHAR(255),
    PRICE DECIMAL(10, 2),
    OLD_PRICE DECIMAL(10, 2),
    CREATED_TIMESTAMP TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATED_TIMESTAMP TIMESTAMP,
    CREATED_BY VARCHAR(50) NOT NULL,
    UPDATED_BY VARCHAR(50)
);


CREATE TABLE CARD_DETIALS (
    RECORD_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID INT,
    CARD_NUMBER VARCHAR(16),
    EXPIRY_DATE DATE,
    CARD_HOLDER_NAME VARCHAR(100),
    CREATE_TIMESTAMP TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATE_TIMESTAMP TIMESTAMP,
    CREATED_BY VARCHAR(50) NOT NULL,
	UPDATED_BY VARCHAR(50),
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)
);

CREATE TABLE ADDRESS (
    RECORD_ID INT AUTO_INCREMENT PRIMARY KEY,
    USER_ID INT,
    ADDRESS_LINE_1 VARCHAR(255) NOT NULL,
    ADDRESS_LINE_2 VARCHAR(255),
    CITY VARCHAR(100),
    STATE VARCHAR(100),
    PINCODE VARCHAR(20),
    CREATE_TIMESTAMP TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATE_TIMESTAMP TIMESTAMP,
    CREATED_BY VARCHAR(50) NOT NULL,
	UPDATED_BY VARCHAR(50),
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID)
);


CREATE TABLE ORDERS (
    ORDER_ID INT AUTO_INCREMENT PRIMARY KEY,
    PRODUCT_ID INT,
    USER_ID INT,
    PAYMENT_ID INT,
    PAYMENT_SOURCE VARCHAR(10),
    ADDRESS_ID INT,
    PAYMENT_TYPE varchar(100) NOT NULL,
    REFUND_STATUS VARCHAR(50),
    ORDER_STATUS VARCHAR(50),
    PAYMENT_STATUS VARCHAR(50),
    CREATE_TIMESTAMP TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UPDATE_TIMESTAMP TIMESTAMP,
    CREATED_BY VARCHAR(50) NOT NULL,
	UPDATED_BY VARCHAR(50),
    FOREIGN KEY (PRODUCT_ID) REFERENCES PRODUCTS(PRODUCT_ID),
    FOREIGN KEY (USER_ID) REFERENCES USER(USER_ID),
    FOREIGN KEY (ADDRESS_ID) REFERENCES ADDRESS(RECORD_ID)
);


