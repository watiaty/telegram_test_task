--liquibase formatted sql
--changeset Yauheni Haikou:create user table localFilePath:01.000.00/create_user_table.sql

CREATE TABLE users
(
    id                 BIGINT PRIMARY KEY,
    first_name         VARCHAR(50)  NOT NULL,
    last_name          VARCHAR(50)  NOT NULL,
    username           VARCHAR(50)  NOT NULL,
    language_code      VARCHAR(50)  NOT NULL,
    allows_write_to_pm VARCHAR(50)  NOT NULL,
    photo_url          VARCHAR(255) NOT NULL
);
