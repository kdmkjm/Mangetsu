
/* Drop Tables */

DROP TABLE Post_com CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE SEQ_Post_com_Idpost_com;




/* Create Sequences */

CREATE SEQUENCE SEQ_Post_com_Idpost_com INCREMENT BY 1 START WITH 1;



/* Create Tables */

CREATE TABLE Post_com
(
	Idpost_com number NOT NULL,
	writecom number,
	post_com number,
	Com_story varchar2(300),
	Com_date date,
	PRIMARY KEY (Idpost_com)
);





