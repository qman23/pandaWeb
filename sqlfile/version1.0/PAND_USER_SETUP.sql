-- This CLP file was created using DB2LOOK Version "9.7"
-- Timestamp: 3/24/2015 3:20:34 PM
-- Database Name: PANDA
-- Database Manager Version: DB2/NT64 Version 9.7.0
-- Database Codepage: 1208
-- Database Collating Sequence is: IDENTITY
CONNECT TO PANDA;
CREATE REGULAR TABLESPACE PANDASPACE PAGESIZE 4 K MANAGED BY AUTOMATIC STORAGE EXTENTSIZE 16
OVERHEAD 10.5 PREFETCHSIZE 16 TRANSFERRATE 0.14 BUFFERPOOL IBMDEFAULTBP DROPPED TABLE RECOVERY ON;

create schema PANDA
------------------------------------------------
-- DDL Statements for table "PANDA"."USER"
------------------------------------------------

CREATE TABLE "PANDA"."USER"  (
		  "USER_ID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (  
		    START WITH +0  
		    INCREMENT BY +1  
		    MINVALUE +0  
		    MAXVALUE +2147483647  
		    NO CYCLE  
		    NO CACHE  
		    NO ORDER ) , 
		  "USER_EMAIL" VARCHAR(50) NOT NULL , 
		  "USER_PASSWORD" VARCHAR(50) NOT NULL )   
		 IN "PANDASPACE" ; 


-- DDL Statements for primary key on Table "PANDA"."USER"

ALTER TABLE "PANDA"."USER" 
	ADD CONSTRAINT "CC1426823254872" PRIMARY KEY
		("USER_ID");
		
------------------------------------------------
-- DDL Statements for table "PANDA"."ROLE"
------------------------------------------------
CREATE TABLE "PANDA"."ROLE"  (
		  "ROLE_ID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (  
		    START WITH +0  
		    INCREMENT BY +1  
		    MINVALUE +0  
		    MAXVALUE +2147483647  
		    NO CYCLE  
		    NO CACHE  
		    NO ORDER ) , 
		  "ROLE_NAME" VARCHAR(50) NOT NULL )   
		 IN "PANDASPACE" ; 


-- DDL Statements for primary key on Table "PANDA"."ROLE"

ALTER TABLE "PANDA"."ROLE" 
	ADD CONSTRAINT "CC1426823359289" PRIMARY KEY
		("ROLE_ID");

------------------------------------------------
-- DDL Statements for table "PANDA"."USER_ROLE"
------------------------------------------------

CREATE TABLE
    "PANDA"."USER_ROLE"
    (
        "EMAIL" VARCHAR(50) NOT NULL ,
        "ROLE_ID" INTEGER NOT NULL
    )
    IN "PANDASPACE" ;
    
------------------------------------------------
-- DDL Statements for table "PANDA"."PERMISSION"
------------------------------------------------
 

CREATE TABLE "PANDA"."PERMISSION"  (
		  "PERMISSION_ID" INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (  
		    START WITH +0  
		    INCREMENT BY +1  
		    MINVALUE +0  
		    MAXVALUE +2147483647  
		    NO CYCLE  
		    NO CACHE  
		    NO ORDER ) , 
		  "PERMISSION_NAME" VARCHAR(50) NOT NULL )   
		 IN "PANDASPACE" ; 


-- DDL Statements for primary key on Table "PANDA"."PERMISSION"

ALTER TABLE "PANDA"."PERMISSION" 
	ADD CONSTRAINT "CC1426823457028" PRIMARY KEY
		("PERMISSION_ID");
------------------------------------------------
-- DDL Statements for table "PANDA"."ROLE_PERMISSION"
------------------------------------------------
 

CREATE TABLE "PANDA"."ROLE_PERMISSION"  (
		  "ROLE_ID" INTEGER NOT NULL , 
		  "PERMISSION_ID" INTEGER NOT NULL )   
		 IN "PANDASPACE" ; 
		 
insert into PANDA.ROLE(ROLE_NAME) values('admin');
insert into PANDA.ROLE(ROLE_NAME) values('developer');
insert into PANDA.ROLE(ROLE_NAME) values('user');
insert into PANDA.USER(USER_EMAIL,USER_PASSWORD) values('root','root');
insert into PANDA.USER_ROLE(EMAIL,ROLE_ID) values('root','0');

COMMIT WORK;
CONNECT RESET;
TERMINATE;

-- Generate statistics for all creators 
-- The db2look utility will consider only the specified tables 
-- Creating DDL for table(s)