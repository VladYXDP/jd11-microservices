CREATE TABLE IF NOT EXISTS countries
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    phone_code         INT                NOT NULL,
    country_name VARCHAR(50) UNIQUE NOT NULL
);

INSERT INTO countries (id, phone_code, country_name)
VALUES (1, 200, 'Россия');

INSERT INTO countries (id, phone_code, country_name)
VALUES (2, 210, 'Испания');