
-- //创建用户表
DROP TABLE IF EXISTS user ;
CREATE TABLE user(
id		int	PRIMARY KEY AUTO_INCREMENT,
user_name	 	 nvarchar(20)	UNIQUE NOT NULL,
password	 	 nvarchar(200)	NOT NULL,
name		 nvarchar(20)	NOT NULL,
sex	 	 nvarchar(20)	 DEFAULT '男'  CHECK (sex in ('男','女')),
phone		 nvarchar(20)	NOT NULL,
email		 nvarchar(20)	NOT NULL,
we_chat		 nvarchar(20)	NOT NULL,
auth		 nvarchar(20)	 DEFAULT '普通用户'  CHECK (auth IN('普通用户','管理员','超级管理员')),
role		 nvarchar(20)	DEFAULT '用户',
login_time	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP

);

INSERT user(user_name,password,name,phone,email,we_chat,auth,role) VALUES('用户名1','123456','王先生1','2133434566','1104432863@qq.com','1234567754','超级管理员','管理者');
INSERT user(user_name,password,name,phone,email,we_chat,auth,role) VALUES('用户名2','123456','王先生2','2133434566','1104432863@qq.com','1234567754','管理员','管理者');
INSERT user(user_name,password,name,sex,phone,email,we_chat) VALUES('用户名3','123456','话女士1','女','2133434566','1104432863@qq.com','1234567754');
INSERT user(user_name,password,name,sex,phone,email,we_chat) VALUES('用户名4','123456','任女士2','女','2133434566','1104432863@qq.com','1234567754');


-- --实验室
DROP TABLE IF EXISTS lab_gdt;
DROP TABLE IF EXISTS lab;
CREATE TABLE lab(
id		int	PRIMARY KEY AUTO_INCREMENT,
lab_id		nvarchar(20)	UNIQUE NOT NULL,
lab_type		nvarchar(20)	NOT NULL
);

INSERT lab(lab_id,lab_type) VALUES('111','数控');
INSERT lab(lab_id,lab_type) VALUES('112','化学');
INSERT lab(lab_id,lab_type) VALUES('113','电路');
INSERT lab(lab_id,lab_type) VALUES('114','通信');
INSERT lab(lab_id,lab_type) VALUES('121','计算机软件');
INSERT lab(lab_id,lab_type) VALUES('122','单片机');
INSERT lab(lab_id,lab_type) VALUES('123','计算机组成原理');
INSERT lab(lab_id,lab_type) VALUES('124','物理');



--  实验室时间管理
-- DROP TABLE IF EXISTS lab_gdt;
CREATE TABLE lab_gdt(
lab_id	 	 nvarchar(20)	  ,
lg_timing 		 nvarchar(20)	 ,
lg_date date,
lg_status		nvarchar(20)	DEFAULT '可预约' CHECK (lg_status IN ('可预约','被预约')), 
-- lg_s_time	 	 TIMESTAMP	 NOT NULL DEFAULT CURRENT_TIMESTAMP,
-- lg_e_time		TIMESTAMP	 NOT NULL DEFAULT CURRENT_TIMESTAMP,
name		nvarchar(20)	,
lg_type		nvarchar(20)	 ,
-- CHECK (lg_type IN ('课程','项目','其它')),
obj_name		nvarchar(20)	 DEFAULT '无',
PRIMARY KEY (lab_id,lg_timing,lg_date),
FOREIGN KEY (lab_id)
REFERENCES lab(lab_id)
);
ALTER TABLE lab_gdt MODIFY lg_timing nvarchar(20)  CHECK (lo_timing IN ('1-2','2-4','4-6','6-8','8-10'));

-- INSERT lab_gdt(lab_id,lg_timing,lg_date,lg_status,name,lg_type) VALUES('111','1-2',SYSDATE(),'被预约','话女士1','课程');
-- INSERT lab_gdt(lab_id,lg_timing,lg_date,lg_status) VALUES('111','2-4','2022/4/13','可预约');
-- INSERT lab_gdt(lab_id,lg_timing,lg_date,lg_status) VALUES('111','4-6','2022/4/13','可预约');
-- INSERT lab_gdt(lab_id,lg_timing,lg_date,lg_status) VALUES('111','6-8','2022/4/12','可预约');
-- INSERT lab_gdt(lab_id,lg_timing,lg_date,lg_status) VALUES('111','8-10','2022/4/12','可预约');
-- 
-- INSERT lab_gdt(lab_id,lg_timing,lg_date,lg_status) VALUES('112','1-2','2022/4/12','可预约');
-- INSERT lab_gdt(lab_id,lg_timing,lg_date,lg_status) VALUES('112','2-4','2022/4/12','可预约');
-- INSERT lab_gdt(lab_id,lg_timing,lg_date,lg_status) VALUES('112','4-6','2022/4/12','可预约');
-- INSERT lab_gdt(lab_id,lg_timing,lg_date,lg_status) VALUES('112','6-8','2022/4/12','可预约');
-- INSERT lab_gdt(lab_id,lg_timing,lg_date,lg_status) VALUES('112','8-10','2022/4/12','可预约');




-- 实验项目
DROP TABLE IF EXISTS obj;
CREATE TABLE obj(		
id		int	PRIMARY KEY AUTO_INCREMENT,
obj_name		nvarchar(20)	UNIQUE NOT NULL,
 obj_description		nvarchar(8000)	NOT NULL,
obj_status		nvarchar(20)	 DEFAULT '审批中' CHECK (obj_status in ('审批中','已审批')),
name		nvarchar(20)	,
obj_s_time		date	 NOT NULL,
							
obj_e_time		date	NOT NULL ,
               
obj_reason		nvarchar(8000)	 
);

INSERT obj(obj_name,obj_description,obj_status,name,obj_s_time,obj_e_time,obj_reason) VALUES('量子力学','呼吁~呼吁~','已审批','任女士2',NOW(), date_add(now(), interval 1 day),'可以起飞');
INSERT obj(obj_name,obj_description,obj_status,name,obj_s_time,obj_e_time,obj_reason) VALUES('项目1','项目描述','审批中','任女士2',NOW(), NOW(),'项目原由');

-- 设备仪器
DROP TABLE IF EXISTS 	device		;
CREATE TABLE device(
id		int	PRIMARY KEY AUTO_INCREMENT,
dev_name 	 	nvarchar(20)	UNIQUE NOT NULL,
dev_price		float	NOT NULL,
dev_u_status		nvarchar(20)	DEFAULT '可借用' CHECK(dev_u_status IN ('不可用','可借用','借用中','被借用')),
dev_status		nvarchar(20)	 DEFAULT '良好' CHECK(dev_status IN ('良好','修理中','故障')),
name		nvarchar(20)	,
device_s_time		TIMESTAMP	  ,
device_e_time		TIMESTAMP	 ,
dev_reason		nvarchar(8000)	,
obj_name		nvarchar(20)	 DEFAULT '无'
);

INSERT device(dev_name,dev_price,dev_u_status,dev_status,device_s_time,device_e_time) VALUES('设备1',15.5,'可借用','良好',NOW(), NOW());
INSERT device(dev_name,dev_price,dev_u_status,dev_status,device_s_time,device_e_time) VALUES('设备2',11,'可借用','良好',NOW(), NOW());
INSERT device(dev_name,dev_price,dev_u_status,dev_status,device_s_time,device_e_time) VALUES('设备3',5,'可借用','良好',NOW(), NOW());
INSERT device(dev_name,dev_price,dev_u_status,dev_status,name,device_s_time,device_e_time,dev_reason) VALUES('设备4',22,'被借用','良好','话女士1',NOW(), date_add(now(),interval 1 day),'上课');
INSERT device(dev_name,dev_price,dev_u_status,dev_status,name,device_s_time,device_e_time,dev_reason) VALUES('设备5',15,'借用中','良好','话女士1',NOW(), NOW(),'上课');
INSERT device(dev_name,dev_price,dev_u_status,dev_status,device_s_time,device_e_time) VALUES('设备6',15,'不可用','故障',NOW(), NOW());
INSERT device(dev_name,dev_price,dev_u_status,dev_status,device_s_time,device_e_time) VALUES('设备7',15,'不可用','修理中',NOW(), NOW());


-- 易耗品
DROP TABLE IF EXISTS	comsume;
CREATE TABLE comsume(
id		int	PRIMARY KEY AUTO_INCREMENT,
sme_name		nvarchar(20)	UNIQUE NOT NULL,
sme_count		int	DEFAULT '0' CHECK (sme_count >='0')
);

INSERT comsume(sme_name,sme_count) VALUES('易耗品1',50);
INSERT comsume(sme_name,sme_count) VALUES ('易耗品2',30);

-- 入库记录
DROP TABLE IF EXISTS 	in_record		;
CREATE TABLE in_record(
id		int	PRIMARY KEY AUTO_INCREMENT,
sme_name		nvarchar(20)	NOT NULL,
in_count		int	CHECK (in_count >'0'),
name		nvarchar(20)	NOT NULL,
in_time			TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP
);

INSERT in_record(sme_name,in_count,name) VALUES('易耗品1',2,'话女士1');
INSERT in_record(sme_name,in_count,name) VALUES('易耗品2',3,'话女士1');


-- 出库记录
DROP TABLE IF EXISTS	out_record	;	
CREATE TABLE out_record(
id		bigint	PRIMARY KEY AUTO_INCREMENT,
sme_name		nvarchar(20)	NOT NULL ,
out_count		int	CHECK (out_count >'0'),
name		nvarchar(20)	NOT NULL,
out_status		nvarchar(20)	DEFAULT '出库中' CHECK (out_status IN ('未出库','出库中','已出库')),

out_reason		nvarchar(8000)	,
out_time		TIMESTAMP	NOT NULL DEFAULT CURRENT_TIMESTAMP	,
obj_name		nvarchar(20)	 DEFAULT '无'
);

INSERT out_record(sme_name,out_count,name,out_reason) VALUES('易耗品2',3,'话女士1','肝实验1');

INSERT out_record(sme_name,out_count,name,out_reason) VALUES('易耗品1',5,'任女士2','开发开发');



-- 通知
DROP TABLE IF EXISTS 	advice;
CREATE TABLE advice(
id		int	PRIMARY KEY AUTO_INCREMENT,
ad_type	 	nvarchar(20)	DEFAULT '成功' CHECK (ad_type in ('成功','失败','警告')),
ad_content		nvarchar(8000)	NOT NULL,
user_name		nvarchar(20)	NOT NULL
);


INSERT advice(ad_type,ad_content,user_name) VALUES('成功','您已成功借用设备1','用户名3');
INSERT advice(ad_type,ad_content,user_name) VALUES('成功','您已成功预约实验室','用户名3');
INSERT advice(ad_type,ad_content,user_name) VALUES('失败','设备已被借用','用户名3');



-- 通知日志 待处理
DROP TABLE IF EXISTS advice_log;
CREATE TABLE advice_log(
id bigint primary key auto_increment,
ad_type	 	nvarchar(20)	DEFAULT '成功' CHECK (ad_type in ('成功','失败','警告')),
ad_content		nvarchar(8000)	NOT NULL,
user_name		nvarchar(20)	NOT NULL,
name nvarchar(20) NOT NULL
);

INSERT advice_log(ad_type,ad_content,user_name,name) VALUES('成功','您已成功借用设备1','用户名3','王先生1');
INSERT advice_log(ad_type,ad_content,user_name,name) VALUES('失败','设备已被借用','用户名3','王先生1');



#开启事件计划（调度器）
SET GLOBAL event_scheduler = 1;

#每天删除昨天数据
DROP PROCEDURE IF EXISTS `lab_gdt_refresh`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lab_gdt_refresh`()
BEGIN
	#Routine body goes here...
	DELETE FROM lab_gdt	
	WHERE 
	#lg_status='被预约' and 
	TO_DAYS(CURDATE())-TO_DAYS(lg_date)>=1;

END;



#每周添加下周数据
DROP PROCEDURE IF EXISTS `lab_gdt_refresh2`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lab_gdt_refresh2`(IN START_TIME INT,IN START_WEEK INT)
BEGIN
	#Routine body goes here...
	DECLARE i INT ;
	DECLARE j INT DEFAULT 0;
	DECLARE l INT DEFAULT 0;
	DECLARE c INT DEFAULT 0;
	DECLARE tmp  TEXT DEFAULT '';
	SET l=START_TIME;
	SET c=START_WEEK;
	SELECT COUNT(*) INTO i FROM lab;
	WHILE l<c DO
		WHILE j<i DO
			SELECT lab_id INTO tmp FROM lab LIMIT j,1;
			INSERT INTO lab_gdt(lab_id,lg_timing,lg_date,lg_status) 
			VALUES(tmp,'1-2',ADDDATE(CURDATE(),interval l day),'可预约');
			INSERT INTO lab_gdt(lab_id,lg_timing,lg_date,lg_status) 
			VALUES(tmp,'2-4',ADDDATE(CURDATE(),interval l day),'可预约');
					INSERT INTO lab_gdt(lab_id,lg_timing,lg_date,lg_status) 
			VALUES(tmp,'4-6',ADDDATE(CURDATE(),interval l day),'可预约');
					INSERT INTO lab_gdt(lab_id,lg_timing,lg_date,lg_status) 
			VALUES(tmp,'6-8',ADDDATE(CURDATE(),interval l day),'可预约');
					INSERT INTO lab_gdt(lab_id,lg_timing,lg_date,lg_status) 
			VALUES(tmp,'8-10',ADDDATE(CURDATE(),interval l day),'可预约');
			SET tmp='';
			SET j=j+1;
		END WHILE;
		SET j=0;
		SET l=l+1;
	END WHILE;
END;

#准备当前周数据
DROP PROCEDURE IF EXISTS `lab_gdt_ready`;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lab_gdt_ready`()
BEGIN
	DECLARE t INT;
	#Routine body goes here...
	select (7+1- (SELECT DATE_FORMAT(CURDATE(),'%w'))) INTO t;
	CALL   lab_gdt_refresh2(0,t);

END;
#调用
call lab_gdt_ready();

#每天1
DROP EVENT IF EXISTS `lims`.`lab_gdt_day`;
CREATE EVENT `lims`.`lab_gdt_day`
ON SCHEDULE
EVERY '1' DAY STARTS '2018-07-25 00:00:00'
DO CALL lab_gdt_refresh();



#每周1
DROP EVENT IF EXISTS `lims`.`lab_gdt_week`;
CREATE EVENT `lims`.`lab_gdt_week`
ON SCHEDULE
EVERY '1' WEEK STARTS '2018-07-25 00:00:00'
DO  CALL lab_gdt_refresh2(7,14);



