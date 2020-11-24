INSERT INTO user (email, first_name, hourly_rate, last_name, password, phone, role)
    VALUES 
		('admin@op.pl', 'Wiktor', 0, 'Nowak', 'admin', '123456789', 'ROLE_ADMIN'),
		('trener@op.pl', 'Maciej', 105, 'Adamczyk', 'trener', '987654321', 'ROLE_TEACHER'),
		('jkarski@op.pl', 'Jakub', 110, 'Karski', 'jk', '567543564', 'ROLE_TEACHER'),
		('kgrzelak@op.pl', 'Karolina', 115, 'Grzelak', 'kg', '888777666', 'ROLE_TEACHER'),
		('student@op.pl', 'Antonina', 0, 'Adamska', 'student', '111222333', 'ROLE_USER'),
		('jmazur@op.pl', 'Jakub', 0, 'Mazur', 'jm', '123678098', 'ROLE_USER'),
		('pkowalski@op.pl', 'Patryk', 0, 'Kowalski', 'pk', '321654987', 'ROLE_USER'),
		('kpawlak@op.pl', 'Krzysztof', 0, 'Pawlak', 'kp', '435765938', 'ROLE_USER'),
		('ochmielewski@op.pl', 'Oliwier', 0, 'Chmielewski', 'oc', '678456234', 'ROLE_USER'),
		('aszewczyk@op.pl', 'Amelia', 0, 'Szewczyk', 'as', '765435234', 'ROLE_USER');

INSERT INTO course (begin_date, city, end_date, mode, name, price, teacher_id)
    VALUES 
		('2021-01-15', 'Warszawa', '2021-07-15', 'DAILY', 'Java od podstaw', 7900, 2),
		('2021-01-25', 'Gdańsk', '2021-06-15', 'EXTRAMURAL', 'Angular Developer', 5900, 3),
		('2021-02-05', 'Wrocław', '2021-08-15', 'DAILY', 'Full-Stack Developer', 11900, 4);

INSERT INTO user_courses (users_id, courses_id) 
	VALUES 
		(5, 1),
		(6, 1),
		(7, 2),
		(8, 1),
		(8, 2),
		(9, 2),
		(9, 3),
		(10, 3);
		