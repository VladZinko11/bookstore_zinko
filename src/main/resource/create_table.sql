DROP TABLE IF EXISTS book;

CREATE TABLE public.book
(
    id BIGSERIAL UNIQUE PRIMARY KEY,
    author VARCHAR(100),
    title VARCHAR(100),
    isbn VARCHAR(100) UNIQUE,
    publication_date DATE
);