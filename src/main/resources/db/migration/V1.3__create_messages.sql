CREATE TABLE MESSAGES (
    message_id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT,
    room_id INT,
    message_text VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    FOREIGN KEY (sender_id) REFERENCES users(user_id),
    FOREIGN KEY (room_id) REFERENCES chat_rooms(room_id)
);