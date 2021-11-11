CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS rooms
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    owner_id BIGINT REFERENCES users(id),
    UNIQUE(owner_id, name)
);

CREATE TABLE IF NOT EXISTS user_room
(
    user_id BIGINT REFERENCES users(id),
    room_id BIGINT REFERENCES rooms(id),
    PRIMARY KEY(user_id, room_id)
);

CREATE TABLE IF NOT EXISTS messages
(
    id BIGSERIAL PRIMARY KEY,
    author_id BIGINT REFERENCES users(id),
    room_id BIGINT REFERENCES rooms(id),
    text TEXT,
    date TIMESTAMP
);