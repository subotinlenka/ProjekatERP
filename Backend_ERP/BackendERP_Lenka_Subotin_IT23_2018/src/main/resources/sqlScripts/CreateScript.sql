DROP TABLE IF EXISTS Admins CASCADE;
DROP TABLE IF EXISTS ProductCategory CASCADE;
DROP TABLE IF EXISTS ProductStatus CASCADE;
DROP TABLE IF EXISTS ProductManufacturer CASCADE;
DROP TABLE IF EXISTS Products CASCADE;
DROP TABLE IF EXISTS Customers CASCADE;
DROP TABLE IF EXISTS ProductReview CASCADE;
DROP TABLE IF EXISTS OrderStatus CASCADE;
DROP TABLE IF EXISTS Orders CASCADE;
DROP TABLE IF EXISTS OrderItem CASCADE;

DROP SEQUENCE IF EXISTS admins_seq;
DROP SEQUENCE IF EXISTS productCategory_seq;
DROP SEQUENCE IF EXISTS productStatus_seq;
DROP SEQUENCE IF EXISTS productManufacturer_seq;
DROP SEQUENCE IF EXISTS products_seq;
DROP SEQUENCE IF EXISTS customers_seq;
DROP SEQUENCE IF EXISTS productReview_seq;
DROP SEQUENCE IF EXISTS orderStatus_seq;
DROP SEQUENCE IF EXISTS orders_seq;
DROP SEQUENCE IF EXISTS orderItem_seq;


CREATE TABLE Admins(
	adminID integer not null,
	adminFirstName varchar(30) not null,
	adminLastName varchar(30) not null,
	adminDateofBirth date not null,
	adminPhoneNumber varchar(25) null,
	adminAddress varchar(30) not null,
	adminCity varchar(25) not null,
	adminCountry varchar(25) not null,
	adminEmail varchar(30) not null,
	adminUserName varchar(25) not null,
	adminPassword varchar(25) not null,
	CONSTRAINT pk_admins PRIMARY KEY(adminID)
);

CREATE TABLE ProductCategory(
	productCategoryID integer not null,
	productCategoryName varchar(30) not null,
	CONSTRAINT pk_productCategory PRIMARY KEY(productCategoryID)
);

CREATE TABLE ProductStatus(
	productStatusID integer not null,
	productStatusName varchar(30) not null,
	CONSTRAINT pk_productStatus PRIMARY KEY(productStatusID)
);

CREATE TABLE ProductManufacturer(
	productManufacturerID integer not null,
	manufacturerName varchar(30) not null,
	manufacturerPhoneNumber varchar(25) not null,
	manufacturerAddress varchar(30) not null,
	manufacturerCity varchar(25) not null,
	manufacturerCountry varchar(25) not null,
	manufacturerEmail varchar(30) not null,
	CONSTRAINT pk_productManufacturer PRIMARY KEY(productManufacturerID)
);

CREATE TABLE Products(
	productID integer not null,
	productName varchar(100) not null,
	productDescription varchar(300) null,
	productSize varchar(10) null,
	productQuantity integer not null,
	productPrice real not null,
	productImage varchar(150) not null,
	productDiscount boolean not null,
	discountAmount real null,
	productStatusID integer not null,
	productCategoryID integer not null,
	productManufacturerID integer not null,
	adminID integer not null,
	CONSTRAINT pk_products PRIMARY KEY(productID),
	CONSTRAINT fk_productStatus_products FOREIGN KEY(productStatusID) REFERENCES ProductStatus(productStatusID),
	CONSTRAINT fk_productCategory_products FOREIGN KEY(productCategoryID) REFERENCES ProductCategory(productCategoryID),
	CONSTRAINT fk_productManufacturer_products FOREIGN KEY(productManufacturerID) REFERENCES ProductManufacturer(productManufacturerID),
	CONSTRAINT fk_admins_products FOREIGN KEY(adminID) REFERENCES Admins(adminID) 
);

CREATE TABLE Customers(
	customerID integer not null,
	customerFirstName varchar(30) not null,
	customerLastName varchar(30) not null,
	customerDateofBirth date not null,
	customerPhoneNumber varchar(25) null,
	customerAddress varchar(30) not null,
	customerCity varchar(25) not null,
	customerCountry varchar(25) not null,
	customerEmail varchar(30) not null,
	customerUserName varchar(25) not null,
	customerPassword varchar(25) not null,
	CONSTRAINT pk_customers PRIMARY KEY(customerID)
);

CREATE TABLE ProductReview(
	productReviewID integer not null,
	productReviewText varchar(300) null,
	productReviewDate date not null,
	productID integer not null,
	customerID integer not null,
	CONSTRAINT pk_productReview PRIMARY KEY(productReviewID),
	CONSTRAINT fk_products_productReview FOREIGN KEY(productID) REFERENCES Products(productID),
	CONSTRAINT fk_customers_productReview FOREIGN KEY(customerID) REFERENCES Customers(customerID)
);

CREATE TABLE OrderStatus(
	orderStatusID integer not null,
	orderStatusName varchar(30) not null,
	CONSTRAINT pk_orderStatus PRIMARY KEY(orderStatusID)
);

CREATE TABLE Orders(
	orderID integer not null,
	orderDate date not null,
	orderAmount real not null,
	orderDeliveryFee real not null,
	orderTotalAmount real not null,
	orderAddress varchar(30) not null,
	orderCity varchar(25) not null,
	orderPaid boolean not null,
	orderPaymentType varchar(20) not null,
	orderPaymentDate date null CHECK(orderPaymentDate>=orderDate),
	orderStatusID integer not null,
	customerID integer not null,
	CONSTRAINT pk_orders PRIMARY KEY(orderID),
	CONSTRAINT fk_orderStatus_orders FOREIGN KEY(orderStatusID) REFERENCES OrderStatus(orderStatusID),
	CONSTRAINT fk_customers_orders FOREIGN KEY(customerID) REFERENCES Customers(customerID)
);

CREATE TABLE OrderItem(
	orderItemID integer not null,
	orderItemQuantity integer not null,
	orderItemTotalPrice real not null,
	productID integer not null,
	orderID integer not null,
	CONSTRAINT pk_orderItem PRIMARY KEY(orderItemID),
	CONSTRAINT fk_products_orderItem FOREIGN KEY(productID) REFERENCES Products(productID),
	CONSTRAINT fk_orders_orderItem FOREIGN KEY(orderID) REFERENCES Orders(orderID) 
);

CREATE SEQUENCE admins_seq INCREMENT 1;
CREATE SEQUENCE productCategory_seq INCREMENT 1;
CREATE SEQUENCE productStatus_seq INCREMENT 1;
CREATE SEQUENCE productManufacturer_seq INCREMENT 1;
CREATE SEQUENCE products_seq INCREMENT 1;
CREATE SEQUENCE customers_seq INCREMENT 1;
CREATE SEQUENCE productReview_seq INCREMENT 1;
CREATE SEQUENCE orderStatus_seq INCREMENT 1;
CREATE SEQUENCE orders_seq INCREMENT 1;
CREATE SEQUENCE orderItem_seq INCREMENT 1;

