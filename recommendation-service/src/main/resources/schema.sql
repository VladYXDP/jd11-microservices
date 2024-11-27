CREATE TABLE IF NOT EXISTS recommendations
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    full_name      VARCHAR(40),
    company        VARCHAR(40),
    position       VARCHAR(40),
    phone_code_id  BIGINT NOT NULL,
    phone_number   VARCHAR(25),
    email          VARCHAR(50),
    linked_in      VARCHAR,
    telegram       VARCHAR(50),
    viber          VARCHAR(50),
    whats_app      VARCHAR(50),
    recommendation VARCHAR
);

INSERT INTO recommendations (id, full_name, company, position, phone_code_id, phone_number, email, linked_in, telegram, viber, whats_app, recommendation)
VALUES (1,
        'Vlad Lysenko',
        'Bank',
        'Developer',
        1,
        '11111111',
        'v@mail.ru',
        'linkedin/vlad',
        '@vl',
        'vi',
        'whats',
        'Good!');

INSERT INTO recommendations (id, full_name, company, position, phone_code_id, phone_number, email, linked_in, telegram, viber, whats_app, recommendation)
VALUES (2,
        'Ekaterina L',
        'Yandex',
        'Developer',
        1,
        '22222222',
        'e@mail.ru',
        'linkedin/kate',
        '@el',
        'vie',
        'whatse',
        'Very Good!');

INSERT INTO recommendations (id, full_name, company, position, phone_code_id, phone_number, email, linked_in, telegram, viber, whats_app, recommendation)
VALUES (3,
    'Nikita H',
    'X5',
    'HR',
    4,
    '3333333',
    'hr@mail.ru',
    'linkedin/hr',
    '@hr',
    'hr',
    'whatshr',
    'hr Good!');

