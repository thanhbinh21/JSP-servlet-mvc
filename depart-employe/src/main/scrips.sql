-- Tạo database
CREATE DATABASE IF NOT EXISTS employeeDB  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE employeeDB ;

-- Bảng phòng ban
CREATE TABLE departments (
    dept_id INT AUTO_INCREMENT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL UNIQUE
);

-- Bảng nhân viên
CREATE TABLE employees (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    salary DECIMAL(12,2) NOT NULL DEFAULT 0,
    dept_id INT,
    CONSTRAINT fk_emp_dept FOREIGN KEY (dept_id) REFERENCES departments(dept_id)
        ON DELETE SET NULL ON UPDATE CASCADE
);

-- Insert dữ liệu phòng ban
INSERT INTO departments (dept_name) VALUES
('Phòng Kế toán'),
('Phòng Nhân sự'),
('Phòng CNTT'),
('Phòng Kinh doanh');

-- Insert dữ liệu nhân viên
INSERT INTO employees (emp_name, salary, dept_id) VALUES
('Nguyễn Văn A', 15000000, 1),
('Trần Thị B', 12000000, 2),
('Lê Văn C', 20000000, 3),
('Phạm Thị D', 18000000, 4),
('Hoàng Văn E', 17000000, 3);
