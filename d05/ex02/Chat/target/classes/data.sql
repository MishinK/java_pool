INSERT INTO chat.User (login, password) VALUES ('user1','user1');
INSERT INTO chat.User (login, password) VALUES ('user2','user2');
INSERT INTO chat.User (login, password) VALUES ('user3','user3');
INSERT INTO chat.User (login, password) VALUES ('user4','user4');
INSERT INTO chat.User (login, password) VALUES ('user5','user5');

INSERT INTO chat.Chatroom(name, owner) VALUES
('Chat1', (SELECT id FROM chat.User WHERE login = 'user5')),
('Chat2', (SELECT id FROM chat.User WHERE login = 'user4')),
('Chat3', (SELECT id FROM chat.User WHERE login = 'user3')),
('Chat4', (SELECT id FROM chat.User WHERE login = 'user2')),
('Chat5', (SELECT id FROM chat.User WHERE login = 'user1'));

INSERT INTO chat.Message (author, room, text) VALUES
((SELECT id FROM chat.User WHERE login = 'user1'), (SELECT id FROM chat.Chatroom WHERE name = 'Chat1'), 'Message1'),
((SELECT id FROM chat.User WHERE login = 'user2'), (SELECT id FROM chat.Chatroom WHERE name = 'Chat2'), 'Message2'),
((SELECT id FROM chat.User WHERE login = 'user3'), (SELECT id FROM chat.Chatroom WHERE name = 'Chat3'), 'Message3'),
((SELECT id FROM chat.User WHERE login = 'user4'), (SELECT id FROM chat.Chatroom WHERE name = 'Chat4'), 'Message4'),
((SELECT id FROM chat.User WHERE login = 'user5'), (SELECT id FROM chat.Chatroom WHERE name = 'Chat5'), 'Message5');
