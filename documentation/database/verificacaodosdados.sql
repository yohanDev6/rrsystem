-- Listar todos os dados de uma tabela
SELECT * FROM admin;
SELECT * FROM client;
SELECT * FROM room;
SELECT * FROM equipment;
SELECT * FROM reservation;
SELECT * FROM report;
SELECT * FROM report_reservation;

-- Verificar chaves estrangeiras
SHOW CREATE TABLE reservation;
SHOW CREATE TABLE report;
SHOW CREATE TABLE report_reservation;

-- Verificar se os relacionamentos estão corretos
-- 1. Verifica os admins e os seus relatórios
SELECT a.id AS admin_id, a.admin_id, r.id AS report_id, r.generation_date, r.data
FROM admin a
LEFT JOIN report r ON a.id = r.admin_id;

-- 2. Verifica as reservar e os seus clientes
SELECT c.id AS client_id, c.name AS client_name, r.id AS reservation_id, r.reservation_date, r.status
FROM client c
LEFT JOIN reservation r ON c.id = r.client_id;

-- 3. Verifica as reservas e as salas associadas
SELECT r.id AS reservation_id, r.reservation_date, r.status, rm.id AS room_id, rm.name AS room_name
FROM reservation r
LEFT JOIN room rm ON r.room_id = rm.id;

-- 4. Verifica equipamentos e salas associadas
SELECT e.id AS equipment_id, e.name AS equipment_name, rm.id AS room_id, rm.name AS room_name
FROM equipment e
LEFT JOIN room rm ON e.room_id = rm.id;

-- 5. Verifica relatórios e reservas associadas
SELECT rr.report_id, rep.data AS report_data, rr.reservation_id, res.reservation_date
FROM report_reservation rr
LEFT JOIN report rep ON rr.report_id = rep.id
LEFT JOIN reservation res ON rr.reservation_id = res.id;

-- Contar os registros das tabelas
SELECT 'admin' AS table_name, COUNT(*) AS total_records FROM admin
UNION
SELECT 'client', COUNT(*) FROM client
UNION
SELECT 'room', COUNT(*) FROM room
UNION
SELECT 'equipment', COUNT(*) FROM equipment
UNION
SELECT 'reservation', COUNT(*) FROM reservation
UNION
SELECT 'report', COUNT(*) FROM report
UNION
SELECT 'report_reservation', COUNT(*) FROM report_reservation;

-- Verificar a consistência das chaves estrangeiras
-- 1. Reservas com clientes inexistentes
SELECT r.*
FROM reservation r
LEFT JOIN client c ON r.client_id = c.id
WHERE c.id IS NULL;

-- 2. Reservas com salas inexistentes
SELECT r.*
FROM reservation r
LEFT JOIN room rm ON r.room_id = rm.id
WHERE rm.id IS NULL;

-- 3. Relatórios com administradores inexistentes
SELECT rep.*
FROM report rep
LEFT JOIN admin a ON rep.admin_id = a.id
WHERE a.id IS NULL;

-- Verificar o esquema do banco
SELECT table_name, column_name, data_type, column_type, is_nullable, column_key
FROM information_schema.columns
WHERE table_schema = 'RoomReservationSystem';