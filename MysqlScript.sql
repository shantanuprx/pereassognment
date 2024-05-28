create database pereassignment;

use pereassignment;

create table user (
    user_id int auto_increment primary key,
    user_first_name varchar(50),
    user_mid_name varchar(50),
    user_last_name varchar(50),
    user_password varchar(100),
    email_id varchar(100),
    mobile_number varchar(15),
    date_of_birth date,
    preferred_payment_method varchar(50),
    preferred_payment_method_id int,
    default_address varchar(255),
    created_date timestamp default current_timestamp,
    created_by varchar(50) not null,
    updated_date timestamp,
    updated_by varchar(50),
    status varchar(100),
    last_login_date timestamp,
    last_login_ip_address varchar(50)
);
alter table user modify role varchar(10) default 'customer';

create table products (
    product_id int auto_increment primary key,
    product_name varchar(100),
    product_description text,
    current_stock int,
    status varchar(100),
    seller varchar(100),
    is_deleted boolean default false,
    seller_address varchar(255),
    price decimal(10, 2),
    old_price decimal(10, 2),
    created_timestamp timestamp default current_timestamp,
    updated_timestamp timestamp,
    created_by varchar(50) not null,
    updated_by varchar(50)
);


create table card_details (
    record_id int auto_increment primary key,
    user_id int,
    card_number varchar(16),
    expiry_date date,
    card_holder_name varchar(100),
    create_timestamp timestamp default current_timestamp,
    update_timestamp timestamp,
    created_by varchar(50) not null,
	updated_by varchar(50),
    foreign key (user_id) references user(user_id)
);

create table address (
    record_id int auto_increment primary key,
    user_id int,
    address_line_1 varchar(255) not null,
    address_line_2 varchar(255),
    city varchar(100),
    state varchar(100),
    pincode varchar(20),
    create_timestamp timestamp default current_timestamp,
    update_timestamp timestamp,
    created_by varchar(50) not null,
	updated_by varchar(50),
    foreign key (user_id) references user(user_id)
);


create table orders (
    order_id int auto_increment primary key,
    product_id int,
    user_id int,
    payment_id int,
    payment_source varchar(10),
    address_id int,
    payment_type varchar(100) not null,
    refund_status varchar(50),
    order_status varchar(50),
    payment_status varchar(50),
    create_timestamp timestamp default current_timestamp,
    update_timestamp timestamp,
    created_by varchar(50) not null,
	updated_by varchar(50),
    foreign key (product_id) references products(product_id),
    foreign key (user_id) references user(user_id),
    foreign key (address_id) references address(record_id)
);



create database pereassignmenttest;

use pereassignmenttest;

create table user (
    user_id int auto_increment primary key,
    user_first_name varchar(50),
    user_mid_name varchar(50),
    user_last_name varchar(50),
    user_password varchar(100),
    email_id varchar(100),
    mobile_number varchar(15),
    date_of_birth date,
    preferred_payment_method varchar(50),
    preferred_payment_method_id int,
    default_address varchar(255),
    created_date timestamp default current_timestamp,
    created_by varchar(50) not null,
    updated_date timestamp,
    updated_by varchar(50),
    status varchar(100),
    last_login_date timestamp,
    last_login_ip_address varchar(50)
);
alter table user modify role varchar(10) default 'customer';

create table products (
    product_id int auto_increment primary key,
    product_name varchar(100),
    product_description text,
    current_stock int,
    status varchar(100),
    seller varchar(100),
    is_deleted boolean default false,
    seller_address varchar(255),
    price decimal(10, 2),
    old_price decimal(10, 2),
    created_timestamp timestamp default current_timestamp,
    updated_timestamp timestamp,
    created_by varchar(50) not null,
    updated_by varchar(50)
);


create table card_details (
    record_id int auto_increment primary key,
    user_id int,
    card_number varchar(16),
    expiry_date date,
    card_holder_name varchar(100),
    create_timestamp timestamp default current_timestamp,
    update_timestamp timestamp,
    created_by varchar(50) not null,
	updated_by varchar(50),
    foreign key (user_id) references user(user_id)
);

create table address (
    record_id int auto_increment primary key,
    user_id int,
    address_line_1 varchar(255) not null,
    address_line_2 varchar(255),
    city varchar(100),
    state varchar(100),
    pincode varchar(20),
    create_timestamp timestamp default current_timestamp,
    update_timestamp timestamp,
    created_by varchar(50) not null,
	updated_by varchar(50),
    foreign key (user_id) references user(user_id)
);


create table orders (
    order_id int auto_increment primary key,
    product_id int,
    user_id int,
    payment_id int,
    payment_source varchar(10),
    address_id int,
    payment_type varchar(100) not null,
    refund_status varchar(50),
    order_status varchar(50),
    payment_status varchar(50),
    create_timestamp timestamp default current_timestamp,
    update_timestamp timestamp,
    created_by varchar(50) not null,
	updated_by varchar(50),
    foreign key (product_id) references products(product_id),
    foreign key (user_id) references user(user_id),
    foreign key (address_id) references address(record_id)
);

