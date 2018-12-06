
/* Drop Tables */

DROP TABLE post CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_post_idpost;




/* Create Sequences */

CREATE SEQUENCE SEQ_post_idpost INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE post
(
	idpost number NOT NULL,
	title varchar2(45),
	story clob,
	postdate date,
	manipulation_read number,
	writepost number,
	PRIMARY KEY (idpost)
);




