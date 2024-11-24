CREATE TABLE IF NOT EXISTS phone_codes
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    code INT NOT NULL UNIQUE
);

INSERT INTO phone_codes (id, code) VALUES (1, 200);

INSERT INTO phone_codes (id, code) VALUES (2, 210);