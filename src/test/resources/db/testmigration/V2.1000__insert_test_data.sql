INSERT INTO users (username, password) VALUES
('user1', HASH('SHA256', 'password1')),
('user2', HASH('SHA256', 'password2')),
('user3', HASH('SHA256', 'password3'));

INSERT INTO chat_rooms (room_name) VALUES
('Room 1'),
('Room 2'),
('Room 3');

INSERT INTO user_chat_rooms (user_id, room_id) VALUES
(1, 1),
(1, 2),
(2, 2),
(3, 3);

INSERT INTO messages (sender_id, room_id, message_text, timestamp) VALUES
(1, 1, 'Hello from user1 in Room 1', CURRENT_TIMESTAMP),
(2, 2, 'Hello from user2 in Room 2', CURRENT_TIMESTAMP),
(1, 2, 'Another message from user1 in Room 2', CURRENT_TIMESTAMP),
(3, 3, 'Greetings from user3 in Room 3', CURRENT_TIMESTAMP);