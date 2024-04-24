DROP TABLE IF EXISTS user;

CREATE TYPE E_ROLE AS ENUM (
    'ADMIN',
    'CUSTOMER'
    );

CREATE TABLE public.user
(
    id BIGSERIAL UNIQUE PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(50) UNIQUE,
    password VARCHAR(20),
    role E_ROLE
);