DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
email           VARCHAR(128)                PRIMARY KEY,
name            VARCHAR(128)                NOT NULL,
role            INT                         NOT NULL                DEFAULT 100,
pass            VARCHAR(32)                 NOT NULL
);



