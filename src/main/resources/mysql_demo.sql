-- pickles 示例脚本
-- 作者：张孟志
-- Email：104446930@qq.com
-- 日期：2016-01-23

-- 建库、建用户、授权相关语句已注释，请根据实际参考。
-- CREATE DATABASE `pickles` /*!40100 COLLATE 'utf8_unicode_ci' */
-- CREATE USER 'pickles'@'localhost' IDENTIFIED BY 'pickles';
-- GRANT USAGE ON *.* TO 'pickles'@'localhost';
-- GRANT SELECT, EXECUTE, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE, CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT, REFERENCES, TRIGGER, UPDATE, LOCK TABLES  ON `pickles`.* TO 'pickles'@'localhost' WITH GRANT OPTION;
-- FLUSH PRIVILEGES;

drop table if exists account;

create table account (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	phone varchar(20) NOT NULL,
	name varchar(64) DEFAULT '新用户',
	email varchar(64),
	hash_password varchar(128) NOT NULL,
	salt varchar(16) NOT NULL,
	register_date timestamp DEFAULT CURRENT_TIMESTAMP() COMMENT '注册日期',
	group_id INT(3) null DEFAULT 999 COMMENT '组ID，确认权限',
	status varchar(4) DEFAULT 'N',
	UNIQUE index_account_phone (phone),
	UNIQUE index_account_email (email)
);

insert into account (id,phone,email,name,hash_password,salt,group_id,status) values(1,'13000000001','1@gmail.com','管理员','48577990717ce63726608c92c6ab257f','4sG3vd',1,'Y');
insert into account (id,phone,email,name,hash_password,salt,status) values(2,'13000000002','2@gmail.com','东方不败','48577990717ce63726608c92c6ab257f','4sG3vd','Y');
insert into account (id,phone,email,name,hash_password,salt,status) values(3,'13000000003','3@gmail.com','张三','48577990717ce63726608c92c6ab257f','4sG3vd','Y');
insert into account (id,phone,email,name,hash_password,salt) values(4,'13000000004','4@gmail.com','李四','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(5,'13000000005','5@gmail.com','王五','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(6,'13000000006','6@gmail.com','赵六','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt,group_id) values(7,'13000000007','7@gmail.com','管理员2','48577990717ce63726608c92c6ab257f','4sG3vd',2);
insert into account (id,phone,email,name,hash_password,salt) values(8,'13000000008','8@gmail.com','东方不败','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(9,'13000000009','9@gmail.com','张三','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(10,'13000000010','10@gmail.com','李四','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(11,'13000000011','11@gmail.com','王五','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(12,'13000000012','12@gmail.com','赵六','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt,group_id) values(13,'13000000013','13@gmail.com','管理员3','48577990717ce63726608c92c6ab257f','4sG3vd',2);
insert into account (id,phone,email,name,hash_password,salt) values(14,'13000000014','14@gmail.com','东方不败','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(15,'13000000015','15@gmail.com','张三','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(16,'13000000016','16@gmail.com','李四','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(17,'13000000017','17@gmail.com','王五','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(18,'13000000018','18@gmail.com','赵六','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt,group_id) values(19,'13000000019','19@gmail.com','管理员4','48577990717ce63726608c92c6ab257f','4sG3vd',2);
insert into account (id,phone,email,name,hash_password,salt) values(20,'13000000020','20@gmail.com','东方不败','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(21,'13000000021','21@gmail.com','张三','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(22,'13000000022','22@gmail.com','李四','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(23,'13000000023','23@gmail.com','王五','48577990717ce63726608c92c6ab257f','4sG3vd');
insert into account (id,phone,email,name,hash_password,salt) values(24,'13000000024','24@gmail.com','赵六','48577990717ce63726608c92c6ab257f','4sG3vd');

commit;