DROP TABLE IF EXISTS emails;

CREATE TABLE IF NOT EXISTS emails (
    userid 			SERIAL,
    email 		varchar(20) UNIQUE,
	PRIMARY KEY (userid)
);
