/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ss
 * Created: 8 Nov, 2017
 */

-- CREATE TABLE `Persons` (
--   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
--   `name` varchar(20) NOT NULL DEFAULT '',
--   `country` varchar(20) DEFAULT NULL,
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


/*All User's are stored in APP_USER table*/
-- create table APP_USER (
--    id BIGINT NOT NULL AUTO_INCREMENT,
--    sso_id VARCHAR(30) NOT NULL,
--    password VARCHAR(100) NOT NULL,
--    first_name VARCHAR(30) NOT NULL,
--    last_name  VARCHAR(30) NOT NULL,
--    email VARCHAR(30) NOT NULL,
--    state VARCHAR(30) NOT NULL,  
--    PRIMARY KEY (id),
--    UNIQUE (sso_id)
-- );
--   
-- /* USER_PROFILE table contains all possible roles */
-- create table USER_PROFILE(
--    id BIGINT NOT NULL AUTO_INCREMENT,
--    type VARCHAR(30) NOT NULL,
--    PRIMARY KEY (id),
--    UNIQUE (type)
-- );
--   
-- /* JOIN TABLE for MANY-TO-MANY relationship*/ 
-- CREATE TABLE APP_USER_USER_PROFILE (
--     user_id BIGINT NOT NULL,
--     user_profile_id BIGINT NOT NULL,
--     PRIMARY KEY (user_id, user_profile_id),
--     CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
--     CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
-- );
--  
-- /* Populate USER_PROFILE Table */
-- INSERT INTO USER_PROFILE(type)
-- VALUES ('USER');
--  
-- INSERT INTO USER_PROFILE(type)
-- VALUES ('ADMIN');
--  
-- INSERT INTO USER_PROFILE(type)
-- VALUES ('DBA');
--  
-- /* Populate APP_USER Table */
-- INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
-- VALUES ('bill','abc123', 'Bill','Watcher','bill@xyz.com', 'Active');
--  
-- INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
-- VALUES ('danny','abc124', 'Danny','Theys','danny@xyz.com', 'Active');
--  
-- INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
-- VALUES ('sam','abc125', 'Sam','Smith','samy@xyz.com', 'Active');
--  
-- INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
-- VALUES ('nicole','abc126', 'Nicole','warner','nicloe@xyz.com', 'Active');
--  
-- INSERT INTO APP_USER(sso_id, password, first_name, last_name, email, state)
-- VALUES ('kenny','abc127', 'Kenny','Roger','kenny@xyz.com', 'Active');
--  
-- /* Populate JOIN Table */
-- INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
--   SELECT user.id, profile.id FROM app_user user, user_profile profile  
--   where user.sso_id='bill' and profile.type='USER';
--  
-- INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
--   SELECT user.id, profile.id FROM app_user user, user_profile profile
--   where user.sso_id='danny' and profile.type='USER';
--  
-- INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
--   SELECT user.id, profile.id FROM app_user user, user_profile profile
--   where user.sso_id='sam' and profile.type='ADMIN';
--  
-- INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
--   SELECT user.id, profile.id FROM app_user user, user_profile profile
--   where user.sso_id='nicole' and profile.type='DBA';
--  
-- INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
--   SELECT user.id, profile.id FROM app_user user, user_profile profile  
--   where user.sso_id='kenny' and profile.type='ADMIN';
--  
-- INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
--   SELECT user.id, profile.id FROM app_user user, user_profile profile  
--   where user.sso_id='kenny' and profile.type='DBA';

-- CREATE TABLE `Language` (
--   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
--   `name` varchar(50) NOT NULL DEFAULT '',
--   PRIMARY KEY (`id`)
-- ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- CREATE TABLE `State` (
--   `state_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
--   `state_name` varchar(50) NOT NULL,
--   PRIMARY KEY (`state_id`) USING BTREE,
--   UNIQUE KEY `UNI_State_Name` (`State_Name`)
--   ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- CREATE TABLE  `District` (
--   `district_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
--   `district_name` varchar(50) NOT NULL,
--    `state_id` int(10) unsigned NOT NULL,
--   PRIMARY KEY (`district_id`) USING BTREE,
--   KEY `FK_STATE_TRANSACTION_STATE_ID` (`state_id`),
--   CONSTRAINT `FK_STATE_TRANSACTION_STATE_ID` FOREIGN KEY (`state_id`)
--   REFERENCES `State` (`state_id`) ON DELETE CASCADE ON UPDATE CASCADE
-- ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ALTER TABLE `State` CHANGE COLUMN `State_Name` `state_name` VARCHAR(50) NOT NULL;
-- select * from District;

-- create table ssc(
-- ssc_id int(20) unsigned NOT NULL AUTO_INCREMENT,
-- ssc_name varchar(50) NOT NULL,
-- ssc_code varchar(20) NOT NULL,
-- PRIMARY KEY (ssc_id) USING BTREE,UNIQUE KEY UNI_ssc_name (ssc_name)
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- create table assessor(
-- assessor_id int(20) unsigned NOT NULL AUTO_INCREMENT,
-- first_name varchar(50) NOT NULL,
-- last_name varchar(50) NOT NULL,
-- job_role varchar(50) NOT NULL,
-- qualification varchar(50) NOT NULL,
-- email_id varchar(50) NOT NULL,
-- contact_no varchar(50) NOT NULL,
-- aadhar_no varchar(50) NOT NULL,
-- total_exp varchar(50) NOT NULL,
-- state_id int(20) NOT NULL,
-- district_id int(20) NOT NULL,
-- pincode varchar(50) NOT NULL,
-- login_id varchar(50) NOT NULL,
-- password varchar(50) NOT NULL,
-- photo_name varchar(50) NULL,
-- aadhar_image varchar(50) NULL,
-- resume_name varchar(50) NULL,
-- PRIMARY KEY (assessor_id) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ALTER TABLE assessor
-- ADD ssc_id int(20) NOT NULL;
-- ALTER TABLE assessor MODIFY resume_name varchar(50) NULL DEFAULT NULL; 


-- create table qualificationpack(
-- qpID int(20) unsigned NOT NULL AUTO_INCREMENT,
-- qpack_id varchar(50) NOT NULL,
-- qpackname varchar(50) NOT NULL,
-- qpacklevel varchar(50) NULL,
-- totaltheorymarks int(20) NULL,
-- totalpracticalmarks int(20) NULL,
-- totalmarks int(20) NULL,
-- theorycutoffmarks int(20) NULL,
-- practicalcutoffmarks int(20) NULL,
-- overallcutoffmarks int(20) NULL,
-- weightedavgmarks int(20) NULL,
-- PRIMARY KEY (qpID) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ALTER TABLE qualificationpack
-- ADD ssc_id int(20) NOT NULL;


-- create table nos(
-- nosID int(20) unsigned NOT NULL AUTO_INCREMENT,
-- nos_id varchar(50) NOT NULL,
-- nos_name varchar(50) NOT NULL,
-- theorycutoffmarks int(20) NULL,
-- practicalcutoffmarks int(20) NULL,
-- overallcutoffmarks int(20) NULL,
-- weightedavgmarks int(20) NULL,
-- qpack_id int(20) NOT NULL,
-- PRIMARY KEY (nosID) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- create table pc(
-- pcID int(20) unsigned NOT NULL AUTO_INCREMENT,
-- pc_id varchar(50) NOT NULL,
-- pc_name varchar(50) NOT NULL,
-- theorycutoffmarks int(20) NULL,
-- practicalcutoffmarks int(20) NULL,
-- overallcutoffmarks int(20) NULL,
-- nosid int(20) NOT NULL,
-- qpack_id int(20) NOT NULL,
-- PRIMARY KEY (pcID) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- create table batches(
-- ID int(20) unsigned NOT NULL AUTO_INCREMENT,
-- batch_id int(20) NOT NULL,
-- batch_size int(20) NOT NULL,
-- center_id varchar(50) NOT NULL,
-- project_id int(20) NOT NULL,
-- assessment_start_date datetime,
-- assessment_end_date datetime,
-- state_id int(20) NOT NULL,
-- district_id int(20) NOT NULL,
-- center_pincode varchar(50) NOT NULL,
-- center_contactno varchar(50) NOT NULL,
-- center_emailid varchar(50) NOT NULL,
-- center_address varchar(100) NOT NULL,
-- tp_name varchar(50) NOT NULL,
-- login_restrict int(5) NOT NULL,
-- capture_image CHAR(2),
-- qpack_id int(20) NOT NULL,
-- assessor_id int(20) NOT NULL,
-- question_paper_id int(20) NOT NULL,
-- PRIMARY KEY (ID) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
--ALTER TABLE batches ADD capturetime int(5) NOT NULL;

-- create table project(
-- ID int(20) unsigned NOT NULL AUTO_INCREMENT,
-- projectname varchar(20) NOT NULL,
-- PRIMARY KEY (ID) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- create table users(
-- ID int(20) unsigned NOT NULL AUTO_INCREMENT,
-- batchid int(20) NOT NULL,
-- enrollmentno int(20) NOT NULL,
-- trainingagency varchar(50) NOT NULL,
-- traineename varchar(50) NOT NULL,
-- gender varchar(5) NOT NULL,
-- category varchar(20) NOT NULL,
-- dateofbirth varchar(50) NOT NULL,
-- fathername varchar(50) NULL,
-- mothername varchar(50) NULL,
-- traineeadress varchar(50) NOT NULL,
-- district varchar(20) NOT NULL,
-- states varchar(20) NOT NULL,
-- traineephone varchar(50) NOT NULL,
-- eduqualification varchar(50) NOT NULL,
-- techqualification varchar(50) NOT NULL,
-- istraineconductocupstd varchar(20) NOT NULL,
-- jobrole varchar(20) NOT NULL,
-- istagencynsdcpartner varchar(20) NOT NULL,
-- skillingupskilling varchar(20) NOT NULL,
-- coursestartdate varchar(50) NULL,
-- dateofcompletion varchar(50) NULL,
-- trainersname varchar(50) NULL,
-- tproviderspocname varchar(20)NULL,
-- contactno varchar(50) NULL,
-- emailid varchar(20) NULL,
-- totalfeefortraining varchar(50) NULL,
-- dateofassessment varchar(50) NULL,
-- assessmentagency varchar(50) NULL,
-- totalfeeforassessment varchar(50) NULL,
-- assessmentfeefromwhom varchar(50) NULL,
-- totalfeeforcertification varchar(50) NULL,
-- certificationchargefromwhom varchar(50) NULL,
-- priorworkexpyear varchar(50) NULL,
-- employed varchar(20) NULL,
-- nameofemployer varchar(50) NULL,
-- keycontactfrmemployer varchar(50) NULL,
-- phonenoofkeycontact varchar(50) NULL,
-- perageincreasewagepostcert varchar(50) NULL,
-- benifitgainfrmcert varchar(50) NULL,
-- PRIMARY KEY (ID) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ALTER TABLE users
--  ADD username varchar(50) NOT NULL,ADD webcam varchar(20) NOT NULL;

--  create table questions(
--  question_id int(20) unsigned NOT NULL AUTO_INCREMENT,
--  question_title varchar(100) NOT NULL,
--  option1 varchar(100) NOT NULL,
--  option2 varchar(100) NOT NULL,
--  option3 varchar(100) NOT NULL,
--  option4 varchar(100) NOT NULL,
--  option5 varchar(100) NULL,
--  marks int(20) NOT NULL,
--  correct_option varchar(10) NOT NULL,
--  cans1 varchar(10) NULL,
--  cans2 varchar(10)  NULL,
--  cans3 varchar(10)  NULL,
--  cans4 varchar(10)  NULL,
--  solution varchar(50) NOT NULL,
--  noofoption int(10) NOT NULL,
--  question_type varchar(50) NOT NULL,
--  question_level varchar(50) NOT NULL,
-- questionimgurl varchar(100),
--  imageurl1 varchar(100),
--  imageurl2 varchar(100),
--  imageurl3 varchar(100),
--  imageurl4 varchar(100),
--  qpackid int(10) NOT NULL,
--  nosid int(10) NOT NULL,
--  pcid int(10) NOT NULL,
--  PRIMARY KEY (question_id) USING BTREE
--  )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ALTER TABLE questions
-- ADD cans5 varchar(10) NULL;

--  ALTER TABLE questions MODIFY question_title varchar(500) NOT NULL;
--  ALTER TABLE questions MODIFY solution varchar(500) NOT NULL;

-- ALTER TABLE questions
-- ADD imageurl5 varchar(100) NULL;
--  ALTER TABLE questions ADD isapproved varchar(100) NULL; 
--     ALTER TABLE questions ADD isphaseout varchar(50) NULL; 

-- create table multilangquestion(
-- id int(20) unsigned NOT NULL AUTO_INCREMENT,
-- language_id int(20) NOT NULL,
-- question_id int(20) NOT NULL,
-- question_title varchar(500) NOT NULL,
-- option1 varchar(100) NOT NULL,
-- option2 varchar(100) NOT NULL,
-- option3 varchar(100) NOT NULL,
-- option4 varchar(100) NOT NULL,
-- option5 varchar(100) NOT NULL,
-- PRIMARY KEY (id) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

--  create table theorymmq(
--  question_id int(20) unsigned NOT NULL AUTO_INCREMENT,
--  question_title varchar(300) NOT NULL,
--  option1 varchar(100) NOT NULL,
--  option2 varchar(100) NOT NULL,
--  option3 varchar(100) NOT NULL,
--  option4 varchar(100) NOT NULL,
--  option5 varchar(100) NULL,
--  marks int(20) NOT NULL,
--  correct_option varchar(10) NOT NULL,
--  solution varchar(50) NOT NULL,
--  noofoption int(10) NOT NULL,
--  question_type varchar(50) NOT NULL,
--  question_level varchar(50) NOT NULL,
-- questionimgurl varchar(100),
--  imageurl1 varchar(100),
--  imageurl2 varchar(100),
--  imageurl3 varchar(100),
--  imageurl4 varchar(100),
--  qpackid int(10) NOT NULL,
--  isactive varchar(50) NULL,
--  pcidwithmarks varchar(200) NOT NULL,
--  PRIMARY KEY (question_id) USING BTREE
--  )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- create table multilangquestiontheorymmq(
-- id int(20) unsigned NOT NULL AUTO_INCREMENT,
-- language_id int(20) NOT NULL,
-- question_id int(20) NOT NULL,
-- question_title varchar(500) NOT NULL,
-- option1 varchar(100) NOT NULL,
-- option2 varchar(100) NOT NULL,
-- option3 varchar(100) NOT NULL,
-- option4 varchar(100) NOT NULL,
-- PRIMARY KEY (id) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- create table theorypcwithmarks(
-- id int(20) unsigned NOT NULL AUTO_INCREMENT,
-- pcid int(50) NULL,
-- marks int(50) NULL,
-- question_id int(50) NULL,
-- PRIMARY KEY (id) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- create table practicalmmq(
-- id int(20) unsigned NOT NULL AUTO_INCREMENT,
-- qpackid int(10) NOT NULL,
-- senario varchar(100) NOT NULL,
-- marks varchar(100) NOT NULL,
-- PRIMARY KEY (id) USING BTREE
--)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- create table senarioquestions(
-- srno int(20) unsigned NOT NULL AUTO_INCREMENT,
-- question varchar(100) NOT NULL,
-- pcsselectedmarks varchar(100) NOT NULL,
-- totalmarks int(50) NOT NULL,
-- senario_id int(50) NOT NULL,
-- PRIMARY KEY (srno) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- create table pcwithmarks(
-- id int(20) unsigned NOT NULL AUTO_INCREMENT,
-- pcid int(50) NULL,
-- marks int(50) NULL,
-- question_id int(50) NULL,
-- PRIMARY KEY (id) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;



-- create table pcwisequestion(
-- id int(20) unsigned NOT NULL AUTO_INCREMENT,
-- qpackid int(20) NOT NULL,
-- pcid int(20) NOT NULL,
-- totaladdedmarks int(20) NULL,
-- marks1 int(20) NULL,
-- marks2 int(20) NULL,
-- marks3 int(20) NULL,
-- marks4 int(20) NULL,
-- marks5 int(20) NULL,
-- marks6 int(20) NULL,
-- marks7 int(20) NULL,
-- marks8 int(20) NULL,
-- marks9 int(20) NULL,
-- marks10 int(20) NULL,
-- marks11 int(20) NULL,
-- marks12 int(20) NULL,
-- marks13 int(20) NULL,
-- marks14 int(20) NULL,
-- marks15 int(20) NULL,
-- marks16 int(20) NULL,
-- marks17 int(20) NULL,
-- marks18 int(20) NULL,
-- marks19 int(20) NULL,
-- marks20 int(20) NULL,
-- marks21 int(20) NULL,
-- marks22 int(20) NULL,
-- marks23 int(20) NULL,
-- marks24 int(20) NULL,
-- marks25 int(20) NULL,
-- PRIMARY KEY (id) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


-- create table questionpaper(
-- questionpaperid int(20) unsigned NOT NULL AUTO_INCREMENT,
-- questionpapername varchar(200) NOT NULL,
-- totaltime int(20) NOT NULL,
-- israndom varchar(50) NULL,
-- isoptionrandom varchar(50) NULL,
-- isactive varchar(50) NULL,
-- qpackid int(50) NOT NULL,
-- createddatetime datetime,
-- questionids varchar(200) NOT NULL,
-- PRIMARY KEY (questionpaperid) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ALTER TABLE questionpaper ADD theorymmqids varchar(200) NULL;
-- ALTER TABLE questionpaper ADD practicalmmqids varchar(200) NULL;
-- ALTER TABLE questionpaper ADD totalmarks varchar(200) NULL;



-- create table questionusage(
-- id int(20) unsigned NOT NULL AUTO_INCREMENT,
-- questionid int(50) NULL,
-- questionappear varchar(100) NULL,
-- questionanswered varchar(100) NULL,
-- PRIMARY KEY (id) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ALTER TABLE questionusage ADD isphaseout varchar(100) NULL;




-- create table assesmentresult(
-- assesmentid int(20) unsigned NOT NULL AUTO_INCREMENT,
-- batchid int(20) NOT NULL,
-- dateofassesment date NULL,
-- centerimage varchar(200) NULL,
-- assessorimage varchar(200) NULL,
-- startdate datetime NULL,
-- enddate datetime NULL,
-- latonstart varchar(100) NULL,
-- latonend varchar(100) NULL,
-- longonstart varchar(100) NULL,
-- longonend varchar(100) NULL,
-- PRIMARY KEY (assesmentid) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- 
-- drop table userresultdetail;
-- create table userresultdetail(
-- userresultdetailid int(20) unsigned NOT NULL AUTO_INCREMENT,
-- userid int(20) NOT NULL,
-- examstarttime varchar(100)  NULL,
-- examendtime varchar(100)  NULL,
-- totaltime varchar(100)  NULL,
-- timetaken varchar(100)  NULL,
-- ipaddress varchar(100)  NULL,
-- browserversion varchar(300)  NULL,
-- examstatus varchar(100) NULL,
-- logincount int(50) NULL,
-- latitude varchar(100) NULL,
-- questionids varchar(200) NULL,
-- longitude varchar(100) NULL,
-- assesmentid int(20) NOT NULL,
-- batchid int(20) NOT NULL,
-- PRIMARY KEY (userresultdetailid) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- drop table theorywiseresult;
-- 
-- create table theorywiseresult(
-- theoryid int(20) unsigned NOT NULL AUTO_INCREMENT,
-- questionno int(20) NOT NULL,
-- questionid int(20) NOT NULL,
-- correctanswer varchar(100) NOT NULL,
-- timetaken varchar(50) NOT NULL,
-- reviewlater varchar(50) NOT NULL,
-- userresultdetailid int(20) NOT NULL,
-- userid int(20) NOT NULL,
-- assesmentid int(20) NOT NULL,
-- recorddate varchar(100) NULL,
-- PRIMARY KEY (theoryid) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- 
-- create table practicalwiseresult(
-- practicalid int(20) unsigned NOT NULL AUTO_INCREMENT,
-- senarioid int(20) NOT NULL,
-- questionid int(20) NOT NULL,
-- answerstatus varchar(100) NOT NULL,
-- userresultdetailid int(20) NOT NULL,
-- userid int(20) NOT NULL,
-- assesmentid int(20) NOT NULL,
-- PRIMARY KEY (practicalid) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- 
-- drop table assesmentlog;
-- create table assesmentlog(
-- logid int(20) unsigned NOT NULL AUTO_INCREMENT,
-- logactiondate varchar(200)  NULL,
-- actiontaken varchar(200)  NULL,
-- userresultdetailid int(20)  NULL,
-- userid int(20)  NULL,
-- assesmentid int(20)  NULL,
-- PRIMARY KEY (logid) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- drop table questionwiselog;
-- 
-- create table questionwiselog(
-- questionwiselogid int(20) unsigned NOT NULL AUTO_INCREMENT,
-- questionno int(20),
-- startdate varchar(100),
-- enddate varchar(100),
-- timetaken varchar(100),
-- userresultdetailid int(20),
-- userid int(20),
-- assesmentid int(20),
-- PRIMARY KEY (questionwiselogid) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
-- 
-- 
-- create table imagelog(
-- imagelogid int(20) unsigned NOT NULL AUTO_INCREMENT,
-- photourl varchar(200) NOT NULL,
-- timecapture datetime NOT NULL,
-- userresultdetailid int(20) NOT NULL,
-- userid int(20) NOT NULL,
-- assesmentid int(20) NOT NULL,
-- PRIMARY KEY (imagelogid) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


 select * from userresultdetail;
 select * from theorywiseresult;
 select * from questionwiselog;
 select * from assesmentlog;
 select * from questions;
