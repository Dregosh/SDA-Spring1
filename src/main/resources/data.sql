INSERT INTO user (email, first_name, hourly_rate, last_name, password, phone, role)
    VALUES 
		('admin@op.pl', 'Wiktor', 0, 'Nowak', '$2y$12$ECIsvt0epKRYprMTMcSDzuxFj0znBqmPEs6B9mhZZk8qcHDk7qpba', '123456789', 'ROLE_ADMIN'),
		('trener@op.pl', 'Maciej', 105, 'Adamczyk', '$2y$12$GPsN3CVAQod1RaetSyvkTe7xkLzogcvdIUsTt.kIy8SKVf1StnbHa', '987654321', 'ROLE_TEACHER'),
		('student@op.pl', 'Antonina', 0, 'Adamska', '$2y$12$7loij428Be6QZPi7IVt9cOuPjAfVst6e2EdxZDUuR/84GCEV/2jUu', '111222333', 'ROLE_USER'),
		('jkarski@op.pl', 'Jakub', 110, 'Karski', '$2y$12$ky2VCpVskrH5ghF/wzh/YOWkijfX1pxILWbjgb3rCKO8xOlmsvz4S', '567543564', 'ROLE_TEACHER'),
		('kgrzelak@op.pl', 'Karolina', 115, 'Grzelak', '$2y$12$VYiSapmK7lRYCgTmR50e6OH24dz.Ps6dlNlUwlXQaGjvK/eWDywnG', '888777666', 'ROLE_TEACHER'),
		('jmazur@op.pl', 'Jakub', 0, 'Mazur', '$2y$12$xJmQhQsEEABbsxxDi7doQORmnrEfYms0UrPUAIpyiGytginYwZTLi', '123678098', 'ROLE_USER'),
		('pkowalski@op.pl', 'Patryk', 0, 'Kowalski', '$2y$12$3h5JRFgAGUex5dgOHCDMF.buxZdZPJsPTXHTVLRpqI.u31h9SWVwy', '321654987', 'ROLE_USER');

INSERT INTO course (begin_date, city, end_date, mode, name, price, teacher_id)
    VALUES 
		('2021-01-15', 'Warszawa', '2021-07-15', 'DAILY', 'Java od podstaw', 7900, 2),
		('2021-01-25', 'Gdańsk', '2021-06-15', 'EXTRAMURAL', 'Angular Developer', 5900, 4),
		('2021-02-05', 'Wrocław', '2021-08-15', 'DAILY', 'Full-Stack Developer', 11900, 5);

INSERT INTO user_courses (users_id, courses_id) 
	VALUES 
		(3, 1),
		(6, 1),
		(6, 2),
		(7, 2),
		(7, 3);
		