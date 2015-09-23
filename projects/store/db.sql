DROP DATABASE IF EXISTS store;

CREATE DATABASE store
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;

USE store;



DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
email           VARCHAR(128)                PRIMARY KEY,
name            VARCHAR(128)                NOT NULL                DEFAULT '%username%',
role            VARCHAR(16)                 NOT NULL                DEFAULT 'user',
pass            VARCHAR(32)                 NOT NULL                DEFAULT '',
balance         DOUBLE                       NOT NULL                DEFAULT 0.0
)
ENGINE InnoDB
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;





DROP TABLE IF EXISTS categories;
CREATE TABLE IF NOT EXISTS categories (
id              INT                         AUTO_INCREMENT          PRIMARY KEY,
title           VARCHAR(128)                UNIQUE
)
ENGINE InnoDB
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;


INSERT INTO categories(title) VALUES('default');






DROP TABLE IF EXISTS products;
CREATE TABLE IF NOT EXISTS products (
id              INT                         AUTO_INCREMENT      PRIMARY KEY,
title           VARCHAR(128)                UNIQUE,
price           DOUBLE                       NOT NULL            DEFAULT 0.0
)
ENGINE InnoDB
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;






DROP TABLE IF EXISTS products_categories_rel;
CREATE TABLE IF NOT EXISTS products_categories_rel (
product_id              INT,
category_id             INT,
PRIMARY KEY(product_id, category_id)
)
ENGINE InnoDB
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;



DROP TABLE IF EXISTS cart;
CREATE TABLE IF NOT EXISTS cart (
user_email              VARCHAR(128),
product_id              INT,
buy_data                DATE NOT NULL
)
ENGINE InnoDB
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;




INSERT INTO Users(email, name, pass, role) VALUES('admin@localhost', 'Админ Админов', '1', 'admin');
INSERT INTO Users(email, name, pass, role) VALUES('vasek@mail.ru', 'Василий', '1', 'user');



