-- Tạo database (nếu chưa có)
CREATE DATABASE IF NOT EXISTS cartshoping;
USE cartshoping;

-- Tạo bảng products
CREATE TABLE products (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    MODEL VARCHAR(255) NOT NULL,
    DESCRIPTION VARCHAR(500),
    QUANTITY INT NOT NULL DEFAULT 0,
    PRICE DOUBLE NOT NULL,
    IMGURL LONGTEXT
);

-- Chèn dữ liệu mẫu
INSERT INTO products (MODEL, DESCRIPTION, QUANTITY, PRICE, IMGURL) VALUES
('iPhone 15', 'Apple iPhone 15 128GB', 50, 899.99, 'iphone15.jpg'),
('Samsung Galaxy S24', 'Samsung Galaxy S24 Ultra 256GB', 30, 1099.99, 'galaxy-s24.jpg'),
('Google Pixel 8', 'Google Pixel 8 Pro 128GB', 25, 799.99, 'pixel8.jpg'),
('Xiaomi 14', 'Xiaomi 14 256GB', 40, 699.99, 'xiaomi14.jpg'),
('MacBook Air M2', 'Apple MacBook Air 13-inch M2', 20, 1199.99, 'macbook-air.jpg');

-- Kiểm tra dữ liệu
SELECT * FROM products WHERE ID =;
