DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA chat;

DROP TABLE IF EXISTS chat.User;
DROP TABLE IF EXISTS chat.Chatroom;
DROP TABLE IF EXISTS chat.Message;
DROP TABLE IF EXISTS chat.Users_chatrooms;

CREATE TABLE IF NOT EXISTS chat.User (
    id 			SERIAL NOT NULL,
    login 		varchar(20) NOT NULL UNIQUE,
    password 	varchar(20) NOT NULL,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS chat.Chatroom (
    id 			SERIAL NOT NULL,
    name 		varchar(20) NOT NULL UNIQUE,
    owner 		SERIAL NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (owner) REFERENCES chat.User (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS chat.Message (
    id 			SERIAL NOT NULL,
    author	 	SERIAL NOT NULL,
    room	  	SERIAL NOT NULL,
    text 		text,
    date 		TIMESTAMP with time zone DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id),
	FOREIGN KEY (author) REFERENCES chat.User(id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    FOREIGN KEY (room) REFERENCES chat.Chatroom(id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS chat.User_chatrooms
(
    user_id INTEGER NOT NULL,
    room_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES chat.User (id),
    FOREIGN KEY (room_id) REFERENCES chat.Chatroom (id)
);
