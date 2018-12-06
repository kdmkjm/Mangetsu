
/* Drop Tables */

DROP TABLE chat cascade CONSTRAINTS;



/* Create Tables */

CREATE TABLE chat
(
	chatID INT NOT NULL PRIMARY KEY,
	chatName VARCHAR2(20),
	chatContent VARCHAR2(200),
	chatTime DATE
);


/* Create Sequence */

CREATE SEQUENCE chatID2 INCREMENT BY 1 START WITH 1;