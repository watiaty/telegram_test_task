--liquibase formatted sql
--changeset Yauheni Haikou:delete field allows_write_to_pm localFilePath:01.000.01/users.sql

ALTER TABLE users DROP COLUMN allows_write_to_pm;