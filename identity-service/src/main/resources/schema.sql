CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(40),
    password VARCHAR(40),
    email    VARCHAR(40)
);

INSERT INTO users (id, username, password,email)
VALUES (1,
        'test',
        '$2a$12$34Q.YnTmOq/tlQ/lW4KBYe9XRoxdAjBsxO1c8/hbSDIGz3.twocDu',
        'test@mail.ru'
        );