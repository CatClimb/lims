
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
auth		 nvarchar(20)	 DEFAULT '普通用户'  CHECK (auth IN('普通用户','管理员')),
role		 nvarchar(20)	DEFAULT '学生'


);

INSERT user(user_name,password,name,phone,email,we_chat,auth,role) VALUES('飞鹿','86ca69808fa336d9947dfd3a233c71be','王先生','2133434566','1104432863@qq.com','1234567754','管理员','管理者');
INSERT user(user_name,password,name,phone,email,we_chat,auth,role) VALUES('小爱同学','86ca69808fa336d9947dfd3a233c71be','魏先生','2133434566','1104432863@qq.com','1234567754','管理员','老师');
INSERT user(user_name,password,name,sex,phone,email,we_chat) VALUES('杀马特','86ca69808fa336d9947dfd3a233c71be','话女士','女','2133434566','1104432863@qq.com','1234567754');
INSERT user(user_name,password,name,sex,phone,email,we_chat) VALUES('你说的都对','86ca69808fa336d9947dfd3a233c71be','任女士','女','2133434566','1104432863@qq.com','1234567754');


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
DROP TABLE IF EXISTS lab_gdt;
CREATE TABLE lab_gdt(
id int	UNIQUE AUTO_INCREMENT,
lab_id	 	 nvarchar(20)	  ,
lg_timing 		 nvarchar(20)	 ,
lg_date date,
lg_status		nvarchar(20)	DEFAULT '可预约' CHECK (lg_status IN ('可预约','被预约')), 
-- lg_s_time	 	 TIMESTAMP	 NOT NULL DEFAULT CURRENT_TIMESTAMP,
-- lg_e_time		TIMESTAMP	 NOT NULL DEFAULT CURRENT_TIMESTAMP,
user_name		nvarchar(20)	,
 
-- CHECK (lg_type IN ('课程','项目','其它')),

PRIMARY KEY (lab_id,lg_timing,lg_date),
FOREIGN KEY (lab_id)
REFERENCES lab(lab_id)
);
ALTER TABLE lab_gdt MODIFY lg_timing nvarchar(20)  CHECK (lo_timing IN ('0-2','2-4','4-6','6-8','8-10'));

-- INSERT lab_gdt(lab_id,lg_timing,lg_date,lg_status,name) VALUES('111','1-2',SYSDATE(),'被预约','话女士1');
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
obj_status		nvarchar(20)	 DEFAULT '审批中' CHECK (obj_status in ('审批中','已审批','不予通过')),
user_name		nvarchar(20)	,
obj_s_time		date	 NOT NULL,
							
obj_e_time		date	NOT NULL ,
record_time datetime,               
obj_reason		nvarchar(8000)	 
);

INSERT obj(obj_name,obj_description,obj_status,user_name,obj_s_time,obj_e_time,obj_reason) VALUES('物业管理系统研究','根据业务需求设计物业网站','审批中','话女士' ,NOW(),date_add(now(), interval 1 day),'课设');
INSERT obj(obj_name,obj_description,obj_status,user_name,obj_s_time,obj_e_time,obj_reason,record_time) VALUES('环境监测','环境检测是对所选评价区域进行详细的场地监测和分析，随着环保意识的日益增强，环境监测市场的不断扩大，传统的环境监测站已不能完全满足社会对环境监测的需求，国家逐渐开放了在环境监测领域的渠道，对于专门从事环境监测、具有CMA环境检测资质的社会化环境检测机构来说，作为第三方检测的有生力量，已成为社会委托检测的首选。','已审批','王先生',NOW(),date_add(now(), interval 1 day),'本学期任务','2022-04-21T01:32:29');

-- 设备仪器
DROP TABLE IF EXISTS dev_lend;
DROP TABLE IF EXISTS 	device		;
CREATE TABLE device(
id		int	PRIMARY KEY AUTO_INCREMENT,
dev_name 	 	nvarchar(20)	UNIQUE NOT NULL,
dev_price		float	NOT NULL,
dev_status		nvarchar(20)	 DEFAULT '良好' CHECK(dev_status IN ('良好','修理中','故障'))


);
DROP TABLE IF EXISTS dev_lend;
CREATE TABLE dev_lend(
id INT PRIMARY KEY,

dev_u_status		nvarchar(20)	DEFAULT '可借用' CHECK(dev_u_status IN ('不可用','可借用','申请中','被借用')),
user_name		nvarchar(20)	,
borrow_time		datetime	  ,
return_time		datetime	 ,
dev_reason		nvarchar(8000)	,
is_return boolean default true,
FOREIGN KEY (id)
REFERENCES device(id)
);

INSERT device(dev_name,dev_price,dev_status) VALUES('设备1',15.5,'良好');
INSERT device(dev_name,dev_price,dev_status) VALUES('设备2',15.5,'良好');
INSERT device(dev_name,dev_price,dev_status) VALUES('设备3',15.5,'良好');
INSERT device(dev_name,dev_price,dev_status) VALUES('设备4',15.5,'故障');
INSERT device(dev_name,dev_price,dev_status) VALUES('设备5',15.5,'修理中');
INSERT device(dev_name,dev_price,dev_status) VALUES('设备6',15.5,'良好');


INSERT dev_lend VALUES(1,'可借用',null,null,null,null,default);
INSERT dev_lend VALUES(2,'可借用',null,null,null,null,default);
INSERT dev_lend VALUES(3,'被借用','话女士',NOW(), date_add(now(), interval 1 day),null,default);
INSERT dev_lend VALUES(4,'不可用',null,null,null,null,default);
INSERT dev_lend VALUES(5,'不可用',null,null,null,null,default);
INSERT dev_lend VALUES(6,'申请中','话女士',NOW(), date_add(now(), interval 1 day),null,default);



-- 易耗品
DROP TABLE IF EXISTS	out_record	;
DROP TABLE IF EXISTS 	in_record		;
DROP TABLE IF EXISTS	consume;
CREATE TABLE consume(
id		int	PRIMARY KEY AUTO_INCREMENT,
sme_name		nvarchar(20)	UNIQUE NOT NULL,
sme_count		int	DEFAULT '0' CHECK (sme_count >='0')
);

INSERT consume(sme_name,sme_count) VALUES('易耗品1',50);
INSERT consume(sme_name,sme_count) VALUES ('易耗品2',30);

-- 入库记录
DROP TABLE IF EXISTS 	in_record		;
CREATE TABLE in_record(
id		int	PRIMARY KEY AUTO_INCREMENT,
sme_id		int	,
in_count		int	CHECK (in_count >'0'),
user_name		nvarchar(20)	NOT NULL,
in_time			datetime	NOT NULL DEFAULT NOW(),
FOREIGN KEY (sme_id)
REFERENCES consume(id)
);

INSERT in_record(sme_id,in_count,user_name) VALUES(1,3,'话女士1');
INSERT in_record(sme_id,in_count,user_name) VALUES(2,3,'话女士1');


-- 出库记录
DROP TABLE IF EXISTS	out_record	;	
CREATE TABLE out_record(
id		bigint	PRIMARY KEY AUTO_INCREMENT,
sme_id		int	,
out_count		int	CHECK (out_count >'0'),
user_name		nvarchar(20)	NOT NULL,
out_status		nvarchar(20)	DEFAULT '出库中' CHECK (out_status IN ('未出库','出库中','已出库')),

out_reason		nvarchar(8000)	,
out_time		datetime	,
FOREIGN KEY (sme_id)
REFERENCES consume(id)

);

INSERT out_record(sme_id,out_count,user_name,out_reason) VALUES(1,3,'话女士1','肝实验1');

INSERT out_record(sme_id,out_count,user_name,out_reason) VALUES(2,5,'任女士2','开发开发');



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
user_name		nvarchar(20)	NOT NULL

);

INSERT advice_log(ad_type,ad_content,user_name) VALUES('成功','您已成功借用设备1','用户名3');
INSERT advice_log(ad_type,ad_content,user_name) VALUES('失败','设备已被借用','用户名3');



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

#call lab_gdt_refresh();

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
			VALUES(tmp,'0-2',ADDDATE(CURDATE(),interval l day),'可预约');
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
-- DROP EVENT IF EXISTS `lims`.`lab_gdt_day`;
-- CREATE EVENT `lims`.`lab_gdt_day`
-- ON SCHEDULE
-- EVERY '1' DAY STARTS '2018-07-25 00:00:00'
-- DO CALL lab_gdt_refresh();
-- 

DROP EVENT IF EXISTS `lims`.`lab_gdt_day`;	
CREATE EVENT `lims`.`lab_gdt_day`	-- 创建名字为user_event的事件
ON SCHEDULE 
EVERY 1 DAY STARTS DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 MINUTE), INTERVAL 5 MINUTE) -- 每隔一天执行一次，开始执行时间为明天凌晨1点整
ON COMPLETION NOT PRESERVE
ENABLE
DO call lab_gdt_refresh();




#每周1
-- DROP EVENT IF EXISTS `lims`.`lab_gdt_week`;
-- CREATE EVENT `lims`.`lab_gdt_week`
-- ON SCHEDULE
-- EVERY '1' WEEK STARTS '2018-07-25 00:00:00'
-- DO  CALL lab_gdt_refresh2(7,14);

DROP EVENT IF EXISTS `lims`.`lab_gdt_week`;	
CREATE EVENT `lims`.`lab_gdt_week`	-- 创建名字为user_event的事件
ON SCHEDULE 
EVERY 1 DAY STARTS DATE_ADD(DATE_ADD(CURDATE(), INTERVAL 1 MINUTE), INTERVAL 5 MINUTE ) -- 每隔一天执行一次，开始执行时间为明天凌晨1点整
ON COMPLETION NOT PRESERVE
ENABLE
DO CALL lab_gdt_refresh2(7,14);

