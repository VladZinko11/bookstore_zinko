INSERT INTO public.user(
                 first_name, last_name, email, password, id_enum_role
) VALUES ('Vlad', 'Zinko', 'zinko1996@yandex.ru', '12345678', 1),
         ('Max', 'Holloway', 'holloway@gmail.ru', md5(random()::text)::char(10), 1),
         ('Dustin', 'Poirier', 'poirier@gmail.ru', md5(random()::text)::char(10), 2),
         ('Justin', 'Gaethje', 'gaethje@gmail.ru', md5(random()::text)::char(10), 2),
         ('Charles', 'Oliveira', 'oliveira@gmail.ru', md5(random()::text)::char(10), 2),
         ('Arman', 'Tsarukyan', 'tsarukyan@gmail.ru', md5(random()::text)::char(10), 2),
         ('Mateusz', 'Gamrot', 'gamrot@gmail.ru', md5(random()::text)::char(10), 2),
         ('Beneil', 'Dariush', 'dariush@gmail.ru', md5(random()::text)::char(10), 2),
         ('Michael', 'Chandler', 'chandler@gmail.ru', md5(random()::text)::char(10), 2),
         ('Rafael', 'Fiziev', 'fisiev@gmail.ru', md5(random()::text)::char(10), 2),
         ('Renato', 'Moicano', 'moicano@gmail.ru', md5(random()::text)::char(10), 2),
         ('Dan', 'Hooker', 'hooker@gmail.ru', md5(random()::text)::char(10), 2),
         ('Jalin', 'Turner', 'turner@gmail.ru', md5(random()::text)::char(10), 2),
         ('Benoit', 'Saint Denis', 'benoit@gmail.ru', md5(random()::text)::char(10), 2),
         ('Rafael', 'Dos Anjos', 'rafael@gmail.ru', md5(random()::text)::char(10), 2),
         ('Bobby', 'Green', 'green@gmail.ru', md5(random()::text)::char(10), 2),
         ('Ilia', 'Topuria', 'topuria@gmail.ru', md5(random()::text)::char(10), 2),
         ('Brian', 'Ortega', 'ortega@gmail.ru', md5(random()::text)::char(10), 2),
         ('Yair', 'Rodriguez', 'rodriguez@gmail.ru', md5(random()::text)::char(10), 2),
         ('Arnold', 'Alen', 'alen@gmail.ru', md5(random()::text)::char(10), 2);

INSERT INTO public.enum_role (id, role) VALUES (1, 'ADMIN'),
                                               (2, 'CUSTOMER'),
                                               (3, 'MANAGER');