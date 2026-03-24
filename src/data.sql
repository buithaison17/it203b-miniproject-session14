DROP DATABASE IF EXISTS product_management;
CREATE DATABASE product_management;
USE product_management;

CREATE TABLE users
(
    user_id   INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(255) NOT NULL,
    address   VARCHAR(255) NOT NULL
);

CREATE TABLE products
(
    product_id   INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(255) NOT NULL,
    price        DECIMAL(10,2) NOT NULL CHECK(price > 0),
    stock        INT NOT NULL CHECK(stock > 0)
);

CREATE TABLE orders
(
    order_id     INT PRIMARY KEY AUTO_INCREMENT,
    user_id      INT NOT NULL,
    order_date   DATETIME NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL CHECK(total_amount > 0),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE order_items
(
    order_item_id INT PRIMARY KEY AUTO_INCREMENT,
    order_id      INT NOT NULL,
    product_id    INT NOT NULL,
    quantity      INT NOT NULL CHECK(quantity > 0),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (product_id) REFERENCES products(product_id)
);

INSERT INTO users(full_name, address)
VALUES ('John Doe', '123 Main St'),
       ('Jane Smith', '456 Elm St'),
       ('Bob Johnson', '789 Oak Ave'),
       ('Alice Brown', '910 Maple St'),
       ('Chris Davis', '321 Pine St'),
       ('Emma Wilson', '654 Oak St'),
       ('Mike Thompson', '879 Pine Ave'),
       ('Sarah Wilson', '109 Maple St'),
       ('David Davis', '210 Oak St'),
       ('Lily Brown', '987 Maple St');

INSERT INTO products(product_name, stock, price)
VALUES ('iPhone 14', 10, 10000000),
       ('Samsung S23', 15, 9000000),
       ('LG G9', 20, 8000000),
       ('Xiaomi Mi11', 25, 7000000),
       ('Huawei P40', 30, 6000000),
       ('OnePlus 9 Pro', 35, 5000000),
       ('Oppo F11', 40, 4000000),
       ('Vivo V21', 45, 3000000),
       ('Realme 6', 50, 2000000),
       ('Google Pixel 7', 55, 1000000);

INSERT INTO orders(user_id, order_date, total_amount)
VALUES (1, NOW(), 25000000),
       (2, NOW(), 18000000),
       (3, NOW(), 32000000);


INSERT INTO order_items(order_id, product_id, quantity)
VALUES (1, 1, 2),  -- Order 1: 2 iPhone 14
       (1, 2, 1),  -- Order 1: 1 Samsung S23
       (2, 2, 2),  -- Order 2: 2 Samsung S23
       (3, 1, 1),  -- Order 3: 1 iPhone 14
       (3, 3, 1),  -- Order 3: 1 LG G9
       (3, 4, 1);  -- Order 3: 1 Xiaomi Mi11
