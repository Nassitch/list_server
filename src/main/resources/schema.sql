DROP DATABASE IF EXISTS list_db;

CREATE DATABASE IF NOT EXISTS list_db;

USE list_db;

CREATE TABLE IF NOT EXISTS login
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pseudo VARCHAR(180) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('ROLE_ADMIN', 'ROLE_USER') NOT NULL DEFAULT 'ROLE_USER',
    UNIQUE(pseudo),
    UNIQUE(email)
);

CREATE TABLE IF NOT EXISTS admin
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    os VARCHAR(80) NOT NULL,
    browser VARCHAR(80) NOT NULL,
    login_id INT NOT NULL,
    FOREIGN KEY(login_id) REFERENCES login(id)
);

CREATE TABLE IF NOT EXISTS user
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    picture TEXT NOT NULL,
    address TEXT NOT NULL,
    city VARCHAR(180) NOT NULL,
    zip_code VARCHAR(5) NOT NULL,
    status ENUM('ACTIVATED', 'BANNED') DEFAULT 'ACTIVATED',
    login_id INT,
    FOREIGN KEY(login_id) REFERENCES login(id)
);

CREATE TABLE IF NOT EXISTS market
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    size VARCHAR(12) NOT NULL,
    place VARCHAR(80) NOT NULL,
    picture VARCHAR(255) NOT NULL,
    UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS category
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(180) NOT NULL,
    created_at DATETIME NOT NULL,
    picture VARCHAR(255) NOT NULL,
    UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS item
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(180) NOT NULL,
    category_id INT,
    FOREIGN KEY(category_id) REFERENCES category(id),
    UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS shop
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at DATETIME NOT NULL,
    is_completed BOOL NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS invoice
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at VARCHAR(80) NOT NULL,
    total INT NOT NULL,
    market_id INT NOT NULL,
    shop_id INT NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY(market_id) REFERENCES market(id),
    FOREIGN KEY(shop_id) REFERENCES shop(id),
    FOREIGN KEY(user_id) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS log_detail
(
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    login_id INT NOT NULL,
    last_log DATETIME,
    failed_login_attempts DATETIME,
    FOREIGN KEY(login_id) REFERENCES login(id)
);

CREATE TABLE IF NOT EXISTS shop_items
(
    shop_id INT NOT NULL,
    items_id INT NOT NULL,
    FOREIGN KEY(shop_id) REFERENCES shop(id),
    FOREIGN KEY(items_id) REFERENCES item(id)
);