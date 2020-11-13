INSERT INTO role (role_name) VALUES ('admin');
INSERT INTO role (role_name) VALUES ('user');
INSERT INTO role (role_name) VALUES ('teacher');

INSERT INTO user (email, first_name, hourly_rate, last_name, phone, role_id)
    VALUES ('mdabrowski@o2.pl', 'Michał', 105, 'Dąbrowski', '123456789', 3),
           ('aziel@gmail.com', 'Antoni', 0, 'Zieliński', '987654321', 2);

INSERT INTO course (begin_date, city, end_date, mode, name, price, teacher_id)
    VALUES ('2021-01-15', 'Warszawa', '2021-07-15', 'DAILY', 'Java od podstaw', 7500, 1);

INSERT INTO user_courses (users_id, courses_id) VALUES (2, 1);
