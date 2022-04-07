Drop schema if exists PBL5_Web;
create schema PBL5_Web char set utf8mb4;
use PBL5_Web;

drop table if exists Role;
create table Role(
	role_id varchar(255),
    role_name varchar(255) unique not null
);
alter table role add primary key(role_id);

drop table if exists Account;
create table Account(
	account_id varchar(255),
    username varchar(255) unique not null,
    password varchar(255) not null,
    is_activate boolean default 0,
    role_id varchar(255) 
);
alter table account add primary key(account_id);
alter table account add constraint PK_Account_Role foreign key(role_id) references role(role_id);

drop table if exists User;
create table User(
	user_id varchar(255),
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    birthday date,
    gender enum('M','F','N') default 'N',
    address varchar(255),
    avatar VARCHAR(255),
    phone varchar(255) unique,
    created_at date,
    update_at date,
    account_id varchar(255) not null unique
);
alter table User add primary key(user_id);
alter table User add constraint PK_User_Account foreign key(account_id) references Account(account_id);

drop table if exists Product_Type;
create table Product_Type(
	product_Type_id varchar(255),
	Product_Type_Name varchar(255) unique not null
);
alter table Product_Type add primary key(Product_Type_id);

drop table if exists Product;
create table Product(
	product_id varchar(255),
    product_Name varchar(255) not null,
    color varchar(255) not null,
	product_Type_id varchar(255) not null
);
alter table Product add primary key(product_id);
alter table Product add constraint PK_Product_ProductType foreign key(product_Type_id) references Product_Type(product_Type_id);
alter table Product add UNIQUE unique_index(product_Name, color);

drop table if exists Size;
create table Size(
	size_id varchar(255),
    type enum('XS','S','M','L','XL','XXL') default 'XS',
    description text
);
alter table Size add primary key(size_id);

drop table if exists Store;
create table Store(
	store_id varchar(255),
    store_name varchar(255) not null,
    address varchar(255) not null unique,
    phone varchar(255) unique
);
alter table Store add primary key(store_id);

drop table if exists Product_details;
create table Product_details(
	product_id varchar(255) not null,
    store_id varchar(255) not null,
    size_id varchar(255) not null,
    image varchar(255) not null,
    price Double not null,
    amount Double not null
);
alter table  Product_details add primary key(product_id,size_id);
alter table  Product_details add index PK_Product_Size_Store_Product1_idx (product_id);
alter table  Product_details add index PK_Product_Size_Store_Size1_idx (size_id);
alter table  Product_details add index PK_Product_Size_Store_Store1_idx (store_id);
alter table Product_details add constraint PK_Product_Size_Store_Product1 foreign key(product_id) references Product(product_id);
alter table Product_details add constraint PK_Product_Size_Store_Size1 foreign key(size_id) references Size(size_id);
alter table Product_details add constraint PK_Product_Size_Store_Store1 foreign key(store_id) references Store(store_id);
 
 
 