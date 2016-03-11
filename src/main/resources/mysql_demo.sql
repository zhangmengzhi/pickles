-- pickles 示例脚本
-- 作者：张孟志
-- Email：104446930@qq.com
-- 日期：2016-01-23

-- 建库、建用户、授权相关语句已注释，请根据实际参考。
-- CREATE DATABASE `pickles` /*!40100 COLLATE 'utf8_unicode_ci' */
-- CREATE USER 'pickles'@'localhost' IDENTIFIED BY 'pickles';
-- GRANT USAGE ON *.* TO 'pickles'@'localhost';
-- GRANT SELECT, EXECUTE, SHOW VIEW, ALTER, ALTER ROUTINE, CREATE, CREATE ROUTINE, 
-- 		 CREATE TEMPORARY TABLES, CREATE VIEW, DELETE, DROP, EVENT, INDEX, INSERT, 
--		 REFERENCES, TRIGGER, UPDATE, LOCK TABLES  
--		 ON `pickles`.* TO 'pickles'@'localhost' WITH GRANT OPTION;
-- FLUSH PRIVILEGES;

-- --------------------------------------------------------------------------------
-- 用户组
drop table if exists groups;

create table groups (
	id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	code varchar(16) NOT NULL COMMENT '用户组编码',
	group_name varchar(64) DEFAULT '新用户组',
	admin_name varchar(64) DEFAULT '新管理员',
	phone varchar(20) NOT NULL COMMENT '联系人手机号码',
	register_date timestamp DEFAULT CURRENT_TIMESTAMP() COMMENT '注册日期',
	status varchar(4) DEFAULT 'No' COMMENT '是否有效',
	note varchar(255) COMMENT '备注',
	UNIQUE index_groups_code (code),
	UNIQUE index_groups_group_name (group_name),
	UNIQUE index_groups_phone (phone)
);

insert into groups (id,code,group_name,admin_name,phone,status,note) values(1,'root','超级管理员组','超级管理员','13000000001','Yes','超级管理员组，最高权限群体');
insert into groups (id,code,group_name,admin_name,phone,status,note) values(2,'admin','管理员组','管理员','13000000002','Yes','管理员组，可以登录admin控制台');
insert into groups (id,code,group_name,admin_name,phone,status,note) values(3,'nogroup','零散的用户组','零散人员','13000000003','Yes','零散的用户组，归集一些零散的用户、测试用户等');
insert into groups (id,code,group_name,admin_name,phone,status,note) values(4,'facebook','facebook','facebook管理员','13000000004','No','facebook');
insert into groups (id,code,group_name,admin_name,phone,status,note) values(5,'gaoda','gaoda','gaoda管理员','13000000005','No','这里有各色的高达');
insert into groups (id,code,group_name,admin_name,phone,status,note) values(6,'jimuda','吉姆达','吉姆达管理员','13000000006','Yes','木人');
insert into groups (id,code,group_name,admin_name,phone,status,note) values(7,'shouji','手机公司','手机控','13000000007','Yes','一群手机控');
insert into groups (id,code,group_name,admin_name,phone,status,note) values(8,'yaoshi','钥匙公司','开锁人','13000000008','Yes','公安备案开锁人团体');
insert into groups (id,code,group_name,admin_name,phone,status,note) values(9,'huwai','户外爱好者','户外管理员','13000000009','Yes','一起去户外');
insert into groups (id,code,group_name,admin_name,phone,status,note) values(10,'running','奔跑者','奔跑者','13000000010','Yes','跑步爱好者');
insert into groups (id,code,group_name,admin_name,phone,status,note) values(11,'automan','变形金刚','擎天柱','13000000011','Yes','我们是变形金刚');
insert into groups (id,code,group_name,admin_name,phone,status,note) values(12,'badman','大反派','威整天','13000000012','Yes','我们是大反派，专门对付变形金刚');
insert into groups (id,code,group_name,admin_name,phone,status,note) values(13,'google','google','google管理员','13000000013','No','google');

-- 控制台用户
drop table if exists account;

create table account (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	phone varchar(20) NOT NULL,
	name varchar(64) DEFAULT '新用户',
	email varchar(64),
	hash_password varchar(128) NOT NULL,
	salt varchar(16) NOT NULL,
	register_date timestamp DEFAULT CURRENT_TIMESTAMP() COMMENT '注册日期',
	group_id INT(3) null DEFAULT 3 COMMENT '组ID，确认权限',
	status varchar(4) DEFAULT 'No' COMMENT '是否有效',
	UNIQUE index_account_phone (phone),
	UNIQUE index_account_email (email)
);

insert into account (id,phone,email,name,hash_password,salt,group_id,status) values(1,'13000000001','1@gmail.com','超级管理员','48577990717ce63726608c92c6ab257f','4sG3vd',1,'Yes');
insert into account (id,phone,email,name,hash_password,salt,status) values(2,'13000000002','2@gmail.com','东方不败','48577990717ce63726608c92c6ab257f','4sG3vd','Yes');
insert into account (id,phone,email,name,hash_password,salt,status) values(3,'13000000003','3@gmail.com','张三','48577990717ce63726608c92c6ab257f','4sG3vd','Yes');
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

-- 终端用户
drop table if exists endusers;

create table endusers (
	id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	phone varchar(20) NOT NULL,
	name varchar(64) DEFAULT '新用户',
	hash_password varchar(128) NOT NULL,
	salt varchar(16) NOT NULL,
	register_date timestamp DEFAULT CURRENT_TIMESTAMP() COMMENT '注册日期',
	group_id INT(3) null DEFAULT 3 COMMENT '组ID，确认权限',
	status varchar(4) DEFAULT 'No' COMMENT '是否有效',
	UNIQUE index_endusers_phone (phone)
);

insert into endusers (id,phone,name,hash_password,salt,group_id,status) values(1,'13000000001','张三','48577990717ce63726608c92c6ab257f','4sG3vd',1,'Yes');
insert into endusers (id,phone,name,hash_password,salt,group_id,status) values(2,'13000000002','李四','48577990717ce63726608c92c6ab257f','4sG3vd',1,'No');
insert into endusers (id,phone,name,hash_password,salt,group_id,status) values(3,'13000000003','王五','48577990717ce63726608c92c6ab257f','4sG3vd',1,'Yes');
insert into endusers (id,phone,name,hash_password,salt,group_id,status) values(4,'13000000004','张三','48577990717ce63726608c92c6ab257f','4sG3vd',2,'Yes');
insert into endusers (id,phone,name,hash_password,salt,group_id,status) values(5,'13000000005','李四','48577990717ce63726608c92c6ab257f','4sG3vd',2,'No');
insert into endusers (id,phone,name,hash_password,salt,group_id,status) values(6,'13000000006','王五','48577990717ce63726608c92c6ab257f','4sG3vd',2,'Yes');
insert into endusers (id,phone,name,hash_password,salt,group_id,status) values(7,'13000000007','张三','48577990717ce63726608c92c6ab257f','4sG3vd',3,'Yes');
insert into endusers (id,phone,name,hash_password,salt,group_id,status) values(8,'13000000008','李四','48577990717ce63726608c92c6ab257f','4sG3vd',3,'No');
insert into endusers (id,phone,name,hash_password,salt,group_id,status) values(9,'13000000009','王五','48577990717ce63726608c92c6ab257f','4sG3vd',3,'No');

commit;