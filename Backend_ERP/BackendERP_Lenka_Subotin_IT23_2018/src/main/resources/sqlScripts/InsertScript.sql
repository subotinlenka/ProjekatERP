INSERT INTO Admins 
VALUES (nextval('admins_seq'), 'Lenka', 'Subotin', '2000-01-28', '+38162494354', 'Milovana Glisica 3', 'Novi Sad', 'Serbia', 'subotinlenka@gmail.com', 'subotinlenka', 'lenkic2801');
INSERT INTO Admins 
VALUES (nextval('admins_seq'), 'Emilija', 'Subotin', '1996-03-02', '+38166552922', 'Gogoljeva 2', 'Novi Sad', 'Serbia', 'subotinemilija@gmail.com', 'emilijasubotin', 'ema020396');

INSERT INTO ProductCategory
VALUES (nextval('productCategory_seq'), 'Rackets');
INSERT INTO ProductCategory
VALUES(nextval('productCategory_seq'), 'Bags');
INSERT INTO ProductCategory
VALUES(nextval('productCategory_seq'), 'Shoes');
INSERT INTO ProductCategory
VALUES(nextval('productCategory_seq'), 'Clothes');
INSERT INTO ProductCategory
VALUES(nextval('productCategory_seq'), 'Grips');
INSERT INTO ProductCategory
VALUES(nextval('productCategory_seq'), 'Balls');
INSERT INTO ProductCategory
VALUES(nextval('productCategory_seq'), 'WristBands');

INSERT INTO ProductStatus
VALUES(nextval('productStatus_seq'), 'Available');
INSERT INTO ProductStatus
VALUES(nextval('productStatus_seq'), 'Out of stock');

INSERT INTO ProductManufacturer
VALUES(nextval('productManufacturer_seq'), 'Wilson', '0116457389', 'Bulevar kralja Aleksandra 100', 'Belgrade', 'Serbia', 'wilsonSerbia@gmail.com');
INSERT INTO ProductManufacturer
VALUES(nextval('productManufacturer_seq'), 'Head', '0113245619', 'Francuska 22', 'Belgrade', 'Serbia', 'headSerbia@yahoo.com');

INSERT INTO Products
VALUES(nextval('products_seq'), 'Speed Pro 2022 Tour Racket', 'HEAD Speed Pro 2022 Tour Racket, Head size: 645 cm x cm, String pattern: 18/20, Weight: 310g', '2', 1, 20000, 'https://tennisShop.com/images/headR1.jpg', false, null, 1, 1, 2, 1);
INSERT INTO Products
VALUES(nextval('products_seq'), 'Clash Super Tour Racket Bag', 'Wilson Clash Super Tour Racket Bag 15 Pack - Red, Racket compartments: 3, Shoulder strap: 2, Length (mm): 737, Width (mm): 400, Height (mm): 330', null, 1, 12000, 'https://tennisShop.com/images/wilsonBag1.jpg', false, null, 2, 2, 1, 1);
INSERT INTO Products
VALUES(nextval('products_seq'), 'Tour Racket Bag', 'Wilson Tour Racket Bag - Black, Racket compartments: 2, Shoulder strap: 2', null, 1, 12500, 'https://tennisShop.com/images/wilsonBag2.jpg', false, null, 1, 2, 1, 2);


INSERT INTO Customers
VALUES (nextval('customers_seq'), 'Anja', 'Delic', '2000-03-17', '0624532102', 'Petra Drapsina 2', 'Novi Sad', 'Serbia', 'anjadelic@gmail.com', 'delicanja', 'anja0317');
INSERT INTO Customers
VALUES (nextval('customers_seq'), 'Ksenija', 'Petrovic', '1999-02-16', '0631244787', 'Kotorska 7', 'Novi Sad', 'Serbia', 'ksenijap99@gmail.com', 'ksenijap99', 'ksenija0216');

INSERT INTO OrderStatus
VALUES(nextval('orderStatus_seq'), 'Delivered');
INSERT INTO OrderStatus
VALUES(nextval('orderStatus_seq'), 'In progress');


INSERT INTO Orders
VALUES(nextval('orders_seq'), '2022-04-03', 12000, 200, 12200, 'Petra Drapsina 2', 'Novi Sad', true, 'credit card', '2022-04-03',  2, 1);
INSERT INTO Orders
VALUES(nextval('orders_seq'), '2022-02-02', 20000, 200, 20200, 'Kotorska 7', 'Novi Sad', true, 'credit card', '2022-02-02', 2, 2);

INSERT INTO OrderItem
VALUES(nextval('orderItem_seq'), 1, 12000, 2, 1);
INSERT INTO OrderItem
VALUES(nextval('orderItem_seq'), 1, 20000, 1, 2);


