DROP TABLE IF EXISTS Roles CASCADE;
DROP TABLE IF EXISTS ProductCategory CASCADE;
DROP TABLE IF EXISTS ProductStatus CASCADE;
DROP TABLE IF EXISTS ProductManufacturer CASCADE;
DROP TABLE IF EXISTS Products CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS OrderStatus CASCADE;
DROP TABLE IF EXISTS Orders CASCADE;
DROP TABLE IF EXISTS OrderItem CASCADE;

DROP SEQUENCE IF EXISTS roles_seq;
DROP SEQUENCE IF EXISTS productCategory_seq;
DROP SEQUENCE IF EXISTS productStatus_seq;
DROP SEQUENCE IF EXISTS productManufacturer_seq;
DROP SEQUENCE IF EXISTS products_seq;
DROP SEQUENCE IF EXISTS users_seq;
DROP SEQUENCE IF EXISTS orderStatus_seq;
DROP SEQUENCE IF EXISTS orders_seq;
DROP SEQUENCE IF EXISTS orderItem_seq;

CREATE TABLE Roles(
	roleID integer not null,
	roleName varchar(30) not null,
	CONSTRAINT pk_roles PRIMARY KEY(roleID)

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
	productPriceWithDiscount real null,
	productStatusID integer not null,
	productCategoryID integer not null,
	productManufacturerID integer not null,
	priceStripe varchar(100) not null,
	CONSTRAINT pk_products PRIMARY KEY(productID),
	CONSTRAINT fk_productStatus_products FOREIGN KEY(productStatusID) REFERENCES ProductStatus(productStatusID),
	CONSTRAINT fk_productCategory_products FOREIGN KEY(productCategoryID) REFERENCES ProductCategory(productCategoryID),
	CONSTRAINT fk_productManufacturer_products FOREIGN KEY(productManufacturerID) REFERENCES ProductManufacturer(productManufacturerID)
);

CREATE TABLE Users(
	userID integer not null,
	userFirstName varchar(30) not null,
	userLastName varchar(30) not null,
	userDateofBirth date not null,
	userPhoneNumber varchar(25) null,
	userAddress varchar(30) not null,
	userCity varchar(25) not null,
	userCountry varchar(25) not null,
	userEmail varchar(30) not null,
	userUserName varchar(25) not null,
	userPassword varchar(60) not null,
	roleID integer not null,
	CONSTRAINT pk_users PRIMARY KEY(userID),
	CONSTRAINT fk_roles_users FOREIGN KEY(roleID) REFERENCES Roles(roleID)
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
	userID integer not null,
	CONSTRAINT pk_orders PRIMARY KEY(orderID),
	CONSTRAINT fk_orderStatus_orders FOREIGN KEY(orderStatusID) REFERENCES OrderStatus(orderStatusID),
	CONSTRAINT fk_users_orders FOREIGN KEY(userID) REFERENCES Users(userID)
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

CREATE SEQUENCE roles_seq INCREMENT 1;
CREATE SEQUENCE productCategory_seq INCREMENT 1;
CREATE SEQUENCE productStatus_seq INCREMENT 1;
CREATE SEQUENCE productManufacturer_seq INCREMENT 1;
CREATE SEQUENCE products_seq INCREMENT 1;
CREATE SEQUENCE users_seq INCREMENT 1;
CREATE SEQUENCE orderStatus_seq INCREMENT 1;
CREATE SEQUENCE orders_seq INCREMENT 1;
CREATE SEQUENCE orderItem_seq INCREMENT 1;