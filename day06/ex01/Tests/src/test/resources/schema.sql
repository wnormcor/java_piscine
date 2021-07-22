DROP SCHEMA IF EXISTS Product CASCADE;

CREATE SCHEMA Product;

CREATE TABLE IF NOT EXISTS Product.Product (
    prod_id serial NOT NULL PRIMARY KEY,
    name varchar NOT NULL UNIQUE,
    price int NOT NULL
);