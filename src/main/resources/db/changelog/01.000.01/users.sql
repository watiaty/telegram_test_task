--liquibase formatted sql
--changeset Yauheni Haikou:create user table localFilePath:01.000.00/users.sql

ALTER TABLE users DROP COLUMN allows_write_to_pm;