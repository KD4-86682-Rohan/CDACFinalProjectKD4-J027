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

CREATE TABLE Parking_Locations (
    location_id INT PRIMARY KEY AUTO_INCREMENT,
    vendor_id INT NOT NULL,
    city VARCHAR(100) NOT NULL,
    area VARCHAR(100) NOT NULL,
    latitude DECIMAL(10, 8) NOT NULL,
    longitude DECIMAL(11, 8) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (vendor_id) REFERENCES Users(user_id)
);

INSERT INTO Parking_Locations (vendor_id, city, area, latitude, longitude)
VALUES
(3, 'Pune', 'Kothrud', 18.5074, 73.8077),
(3, 'Mumbai', 'Andheri', 19.1197, 72.8467),
(5, 'Delhi', 'Connaught Place', 28.6345, 77.2206);

CREATE TABLE Parking_Slots (
    slot_id INT PRIMARY KEY AUTO_INCREMENT,
    location_id INT NOT NULL,
    slot_number VARCHAR(10) NOT NULL,
    slot_type ENUM('2-Wheeler', '4-Wheeler') NOT NULL,
    price_per_hour_2w DECIMAL(10, 2),
    price_per_hour_4w DECIMAL(10, 2),
    status ENUM('Available', 'Booked', 'Unavailable') NOT NULL DEFAULT 'Available',
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (location_id) REFERENCES Parking_Locations(location_id)
);

INSERT INTO Parking_Slots (location_id, slot_number, slot_type, price_per_hour_2w, price_per_hour_4w, status)
VALUES
(1, 'A1', '2-Wheeler', 20.00, NULL, 'Available'),
(1, 'A2', '4-Wheeler', NULL, 50.00, 'Available'),
(2, 'B1', '2-Wheeler', 15.00, NULL, 'Booked'),
(2, 'B2', '4-Wheeler', NULL, 45.00, 'Available'),
(3, 'C1', '2-Wheeler', 10.00, NULL, 'Unavailable');

CREATE TABLE Bookings (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    slot_id INT NOT NULL,
    slot_type ENUM('2-Wheeler', '4-Wheeler') NOT NULL,
    booking_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    status ENUM('Active', 'Completed', 'Cancelled') NOT NULL DEFAULT 'Active',
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (slot_id) REFERENCES Parking_Slots(slot_id)
);

INSERT INTO Bookings (user_id, slot_id, slot_type, start_time, end_time, total_price, status)
VALUES
(1, 1, '2-Wheeler', '2025-01-28 09:00:00', '2025-01-28 12:00:00', 60.00, 'Completed'),
(4, 2, '4-Wheeler', '2025-01-28 10:00:00', '2025-01-28 11:30:00', 45.00, 'Active');

CREATE TABLE Payments (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    payment_method ENUM('Credit Card', 'Debit Card', 'UPI', 'Net Banking', 'Cash') NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_status ENUM('Success', 'Pending', 'Failed') NOT NULL DEFAULT 'Pending',
    transaction_id VARCHAR(50) NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (booking_id) REFERENCES Bookings(booking_id)
);

INSERT INTO Payments (booking_id, payment_method, amount, payment_status, transaction_id)
VALUES
(1, 'UPI', 150.00, 'Success', 'TXN123456'),
(2, 'Credit Card', 60.00, 'Pending', 'TXN789101');

CREATE TABLE Slot_Availability (
    availability_id INT PRIMARY KEY AUTO_INCREMENT,
    slot_id INT NOT NULL,
    date DATE NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    is_booked BOOLEAN NOT NULL DEFAULT FALSE,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (slot_id) REFERENCES Parking_Slots(slot_id)
);

INSERT INTO Slot_Availability (slot_id, date, start_time, end_time, is_booked)
VALUES
(1, '2025-01-28', '09:00:00', '12:00:00', TRUE),
(2, '2025-01-28', '10:00:00', '11:30:00', FALSE);

CREATE TABLE Revenue (
    revenue_id INT PRIMARY KEY AUTO_INCREMENT,
    location_id INT NOT NULL,
    total_revenue DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    total_revenue_2w DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    total_revenue_4w DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    vendor_earning DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    admin_earning DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (location_id) REFERENCES Parking_Locations(location_id)
);

INSERT INTO Revenue (location_id, total_revenue, total_revenue_2w, total_revenue_4w, vendor_earning, admin_earning)
VALUES
(1, 150.00, 60.00, 90.00, 120.00, 30.00),
(2, 60.00, 15.00, 45.00, 48.00, 12.00);

CREATE TABLE Notifications (
    notification_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    message TEXT NOT NULL,
    notification_type ENUM('Booking', 'Payment', 'System') NOT NULL,
    is_read BOOLEAN NOT NULL DEFAULT FALSE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

INSERT INTO Notifications (user_id, message, notification_type, is_read)
VALUES
(1, 'Your booking has been confirmed.', 'Booking', TRUE),
(4, 'Payment pending for your booking.', 'Payment', FALSE);

CREATE TABLE Reviews (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    location_id INT NOT NULL,
    rating INT NOT NULL,
    review_text TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (location_id) REFERENCES Parking_Locations(location_id)
);

INSERT INTO Reviews (user_id, location_id, rating, review_text)
VALUES
(1, 1, 5, 'Excellent parking facility!'),
(4, 2, 4, 'Good service, but needs better lighting.');

CREATE TABLE Audit_Log (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    admin_id INT NOT NULL,
    action VARCHAR(255) NOT NULL,
    description TEXT,
    timestamp DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (admin_id) REFERENCES Users(user_id)
);

INSERT INTO Audit_Log (admin_id, action, description)
VALUES
(2, 'Update Revenue', 'Updated revenue for location ID 1.');