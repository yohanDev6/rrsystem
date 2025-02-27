-- Create database
CREATE DATABASE IF NOT EXISTS RoomReservationSystem;
USE RoomReservationSystem;

-- Create table for "client"
CREATE TABLE client (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    email VARCHAR(128) UNIQUE NOT NULL,
    password VARCHAR(128) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
) engine=InnoDB;

-- Create table for "refresh-tokens"
CREATE TABLE refresh_tokens (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    client_id BIGINT NOT NULL,
    token VARCHAR(512) NOT NULL,
    expiry_date DATETIME NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE
) engine=InnoDB;

-- Create table for "admin"
CREATE TABLE admin (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    client_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (client_id) REFERENCES client(id)
) engine=InnoDB;

-- Create table for "room"
CREATE TABLE room (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    capacity INT NOT NULL,
    location VARCHAR(128),
    availability BOOLEAN NOT NULL DEFAULT TRUE
) engine=InnoDB;

-- Create table for "equipment"
CREATE TABLE equipment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    room_id BIGINT NOT NULL,
    FOREIGN KEY (room_id) REFERENCES room(id)
) engine=InnoDB;

-- Create table for "reservation"
CREATE TABLE reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    reservation_datetime DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'RESERVED',
    client_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (room_id) REFERENCES room(id)
) engine=InnoDB;

-- Create table for "report"
CREATE TABLE report (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    generation_date DATETIME NOT NULL,
    data TEXT NOT NULL,
    admin_id BIGINT NOT NULL,
    FOREIGN KEY (admin_id) REFERENCES admin(id)
) engine=InnoDB;

-- Create join table for "report_reservation" to link reports and reservations
CREATE TABLE report_reservation (
    report_id BIGINT NOT NULL,
    reservation_id BIGINT NOT NULL,
    PRIMARY KEY (report_id, reservation_id),
    FOREIGN KEY (report_id) REFERENCES report(id),
    FOREIGN KEY (reservation_id) REFERENCES reservation(id)
) engine=InnoDB;
