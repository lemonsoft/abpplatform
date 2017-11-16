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
-- PRIMARY KEY (pcID) USING BTREE
-- )ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


 select * from pc;