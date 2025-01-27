-- database schema for Online Prking System application
CREATE DATABASE IF NOT EXISTS online_parking_system_db;
use online_parking_system_db;

CREATE TABLE Users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(15) UNIQUE NOT NULL,
    gender ENUM('Male','Female','Other') NOT NULL,
    dob date NOT NULL,
    role ENUM('User','Admin','Vendor') NOT NULL,
    license_number VARCHAR(20) UNIQUE NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO Users (first_name, last_name, email, password, phone_number, gender, dob, role, license_number) 
VALUES
('John', 'Doe', 'john.doe@example.com', 'password123', '1234567890', 'Male', '1990-05-15', 'User', 'ABC1234567'),
('Jane', 'Smith', 'jane.smith@example.com', 'securepass456', '9876543210', 'Female', '1985-07-20', 'Admin', 'DEF7654321'),
('Alex', 'Taylor', 'alex.taylor@example.com', 'mypassword789', '1122334455', 'Other', '2000-12-05', 'Vendor', 'GHI9876543'),
('Chris', 'Brown', 'chris.brown@example.com', 'pass123456', '2233445566', 'Male', '1995-09-30', 'User', 'JKL6543219'),
('Emily', 'Clark', 'emily.clark@example.com', 'emilypass321', '3344556677', 'Female', '1992-03-10', 'Vendor', 'MNO1239876');
