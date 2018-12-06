/* Drop Tables */

DROP TABLE User2 CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_User2_Userno;




/* Create Sequences */

CREATE SEQUENCE SEQ_User2_Userno INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE User2
(
	Userno number NOT NULL UNIQUE,
	idUser varchar2(45) NOT NULL,
	pwUser varchar2(45) NOT NULL,
	name varchar2(45) NOT NULL,
	Phonenum varchar2(45),
	Email varchar2(45),
	registerdate date,
	Status number NOT NULL,
	intro varchar2(100),
	intro_blog varchar2(300),
	Cnt_blog number,
	pic_blog varchar2(45),
	PRIMARY KEY (Userno)
);




