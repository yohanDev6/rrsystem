-- Use the database
USE RoomReservationSystem;

-- Insert data into "client"
INSERT INTO client (name, email, password, active) VALUES
('John Doe', 'john.doe@example.com', 'password123', TRUE),
('Jane Smith', 'jane.smith@example.com', 'securepass', TRUE),
('Emily Johnson', 'emily.johnson@example.com', 'mypassword', TRUE),
('Michael Brown', 'michael.brown@example.com', 'adminpassword', TRUE),
('Sophia Davis', 'sophia.davis@example.com', 'guestpass', TRUE);

-- Insert data into "admin"
INSERT INTO admin (client_id) VALUES
(4); -- Michael Brown is the admin

-- Insert data into "room"
INSERT INTO room (name, capacity, location, availability) VALUES
('Conference Room A', 20, '1st Floor, Building A', TRUE),
('Conference Room B', 10, '1st Floor, Building A', TRUE),
('Executive Meeting Room', 15, '2nd Floor, Building B', FALSE),
('Training Room', 30, '3rd Floor, Building C', TRUE),
('Auditorium', 100, 'Ground Floor, Building D', TRUE);

-- Insert data into "equipment"
INSERT INTO equipment (name, room_id) VALUES
('Projector', 1),
('Whiteboard', 1),
('Microphone', 5),
('Sound System', 5),
('Laptop', 3);

-- Insert data into "reservation"
INSERT INTO reservation (reservation_datetime, status, client_id, room_id) VALUES
('2025-01-15 09:00:00', 'RESERVED', 1, 1),
('2025-01-16 14:00:00', 'RESERVED', 2, 2),
('2025-01-17 10:00:00', 'CANCELED', 3, 3),
('2025-01-18 13:30:00', 'POSTPONED', 1, 4),
('2025-01-19 15:00:00', 'HAPPENING', 5, 5);

-- Insert data into "report"
INSERT INTO report (generation_date, data, admin_id) VALUES
('2025-01-20 12:00:00', 'Monthly reservation report', 1),
('2025-01-21 14:00:00', 'Annual equipment usage report', 1);

-- Insert data into "report_reservation"
INSERT INTO report_reservation (report_id, reservation_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(2, 4);