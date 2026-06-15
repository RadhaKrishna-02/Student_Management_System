CREATE DATABASE studentdb;

USE studentdb;

CREATE TABLE students(
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    grade CHAR(1)
);