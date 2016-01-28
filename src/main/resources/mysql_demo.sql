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

insert into account (id,phone,email,name,hash_password,salt,group_id,status) values(1,'13000000001','1@gmail.com','超级管理员','48577990717ce63726608c92c6ab257f','4sG3vd',1,'Y');
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

--  导航菜单
--  使用id/pid关联，考虑到实际使用普遍只有两级菜单，不实现多重菜单
--  导航菜单的配置查询（只考虑两级菜单）
-- select
-- p.id pid, p.name pname, p.href phref, p.param pparam, p.status pstatus,
-- c.id cid, c.name cname, c.href chref, c.param cparam, c.status cstatus
-- from   navtree as p left join navtree as c on p.id=c.pid
-- where  p.pid = 1
-- and    p.status='Y'
-- and    (c.status='Y' or c.status is null)
-- order by p.id asc
-- ;
drop table if exists navtree;

create table navtree (
	id INT(3) NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pid INT(3) NOT NULL,
	name varchar(64) DEFAULT '新菜单',
	href varchar(128),
	param varchar(128),
	status char(1) DEFAULT 'Y'
);

insert into navtree (id,pid,name,href,param,status) values(1,0,'根菜单','#','','Y');
insert into navtree (id,pid,name,href,param,status) values(2,1,'咸菜罐子','/index','','Y');
insert into navtree (id,pid,name,href,param,status) values(3,1,'个人电脑','#','','Y');
insert into navtree (id,pid,name,href,param,status) values(4,3,'Window','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(5,3,'MacBook','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(6,1,'工作站&amp;笔记本','#','','Y');
insert into navtree (id,pid,name,href,param,status) values(7,6,'Dell','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(8,6,'Asus','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(9,6,'Samsung','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(10,6,'Lenovo','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(11,6,'Acer','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(12,1,'移动电话&amp;平板','#','','Y');
insert into navtree (id,pid,name,href,param,status) values(13,12,'Iphone','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(14,12,'Samsung','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(15,12,'Nokia','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(16,12,'Lenovo','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(17,12,'Google','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(18,1,'软件','#','','Y');
insert into navtree (id,pid,name,href,param,status) values(19,18,'游戏','/category','?TOKEN=${(TOKEN)!}','N');
insert into navtree (id,pid,name,href,param,status) values(20,18,'通讯','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(21,1,'配件','#','','N');
insert into navtree (id,pid,name,href,param,status) values(22,21,'键盘&amp;鼠标','/category','?TOKEN=${(TOKEN)!}','Y');
insert into navtree (id,pid,name,href,param,status) values(23,21,'音响','/category','?TOKEN=${(TOKEN)!}','Y');

commit;