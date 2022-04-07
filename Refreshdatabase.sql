use pbl5_web;
SET FOREIGN_KEY_CHECKS = 0;
-- Information Role
INSERT INTO Role 
VALUES('R_E','Employee'),
	  ('R_M','Manager'),
      ('R_A','Admin');

-- Information Account
INSERT INTO Account
VALUES('A_A','Admin','123456789',1,'R_A'),
	  ('A_M1','Manager_1','123456789',1,'R_M'),
      ('A_M2','Manager_2','123456789',1,'R_M'),
      ('A_E1','Employee_1','123456789',1,'R_E');

-- Information User
INSERT INTO User
VALUES('U_1','Lê Gia','Anh Khoa','2001-1-1','M','54 Nguyễn Lương Bằng',null,'090909009',cast(now() as date),cast(now() as date),'A_A'),
	  ('U_2','Lê','Công Huấn','2001-1-2','M','54 Nguyễn Lương Bằng',null,'090909008',cast(now() as date),cast(now() as date),'A_M1'),
      ('U_3','Nguyễn','Nhân Đức','2001-1-3','M','54 Nguyễn Lương Bằng',null,'090909007',cast(now() as date),cast(now() as date),'A_M2'),
      ('U_4','Nguyễn','Viết Bửu','2001-1-4','M','54 Nguyễn Lương Bằng',null,'090909006',cast(now() as date),cast(now() as date),'A_E1');

-- Information Product_Type
INSERT INTO Product_Type
VALUES('PT_1','Áo'),
      ('PT_2','Quần'),
	  ('PT_3','Giày dép'),
	  ('PT_4','Thắt lưng'),
	  ('PT_5','Mũ');

-- Information Product
INSERT INTO Product
VALUES ('P_1','Áo sơ mi Nam','Trắng','PT_1'),
	   ('P_2','Áo sơ mi Nữ','Đen','PT_1'), 
       ('P_3','Quần tây Nam','Xanh','PT_2'),
       ('P_4','Quần jean Nam','Nâu','PT_2'),
       ('P_5','Quần jean Nữ','Trắng','PT_2'),
       ('P_6','Giày da Nam','Đen','PT_3'),
       ('P_7','Giày thể thao Nữ','Vàng','PT_3'),
       ('P_8','Thắt lưng Nam','Hồng','PT_4'),
       ('P_9','Thắt lưng Nữ','Xanh','PT_4'),
       ('P_10','Mũ thể thao Nam','Trắng','PT_5'), 
       ('P_11','Áo khoác','Hồng','PT_1'),
       ('P_12','Áo khoác','Trắng','PT_1');

-- Information Size
INSERT INTO size
VALUES ('S_L' ,'L','L - Nam Nữ 41 - 50KG'),
('S_M' ,'M','M - Nam Nữ 31 - 40KG'),
('S_S' ,'S','S - Nam Nữ 21 - 30KG'),
('S_XL' ,'XL','XL - Nam Nữ 51 - 60KG'),
('S_XXL' ,'XXL','XXL - Nam Nữ 61 - 70KG');


-- Information Store
INSERT INTO Store
VALUES('ST_1','Shop_1','51 Nguyễn Lương Bằng','090909090909'),
      ('ST_2','Shop_2','52 Nguyễn Lương Bằng','090909090908'),
      ('ST_3','Shop_3','53 Nguyễn Lương Bằng','090909090907'),
      ('ST_4','Shop_4','54 Nguyễn Lương Bằng','090909090906');

-- Infomation Product_details
INSERT INTO Product_Details
VALUES('P_1', 'ST_1', 'S_S', 'undefined.png', 120000, 80),
	  ('P_1', 'ST_2', 'S_M', 'undefined.png', 280000, 190),
      ('P_1', 'ST_3', 'S_L', 'undefined.png', 240000, 220),
      ('P_2', 'ST_4', 'S_S', 'undefined.png', 150000, 130),
      ('P_2', 'ST_1', 'S_M', 'undefined.png', 200000, 180),
      ('P_3', 'ST_2', 'S_L', 'undefined.png', 620000, 550),
      ('P_3', 'ST_3', 'S_XL', 'undefined.png', 190000, 130),
      ('P_4', 'ST_4', 'S_XL', 'undefined.png', 630000, 580),
      ('P_4', 'ST_1', 'S_XXL', 'undefined.png', 200000, 180),
      ('P_5', 'ST_2', 'S_S', 'undefined.png', 120000, 80),
      ('P_6', 'ST_3', 'S_S', 'undefined.png', 140000, 120),
      ('P_6', 'ST_4', 'S_M', 'undefined.png', 410000, 350),
      ('P_7', 'ST_1', 'S_S', 'undefined.png', 180000, 170),
      ('P_7', 'ST_2', 'S_M', 'undefined.png', 230000, 200),
      ('P_8', 'ST_3', 'S_S', 'undefined.png', 230000, 180),
      ('P_9', 'ST_4', 'S_M', 'undefined.png', 140000, 120),
      ('P_9', 'ST_1', 'S_L', 'undefined.png', 320000, 190),
      ('P_10','ST_2', 'S_S', 'undefined.png', 560000, 520),
      ('P_10','ST_3', 'S_M', 'undefined.png', 80000, 60);

SET FOREIGN_KEY_CHECKS = 1;