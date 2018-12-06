


/* Drop Tables */

DROP TABLE jsp_member CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_jsp_member_Userno;




/* Create Sequences */

CREATE SEQUENCE SEQ_jsp_member_Userno INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE jsp_member
(
	Userno number NOT NULL,
	id varchar2(45) NOT NULL,
	password varchar2(45) NOT NULL,
	name varchar2(45),
	mail varchar2(45),
	phone varchar2(45),
	address varchar2(200),
	reg date DEFAULT sysdate,
	mailcheck number,
	Status number NOT NULL,
	intro varchar2(100),
	intro_blog varchar2(300),
	pic_blog varchar2(30),
	PRIMARY KEY (Userno)
);




