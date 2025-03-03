DROP DATABASE IF EXISTS ud3_ac6;
CREATE DATABASE IF NOT EXISTS ud3_ac6;
USE ud3_ac6;

drop database actividad3;
create database actividad3;
use actividad3;

CREATE TABLE Robot_de_AL (
    id_robot_almacenamiento INT AUTO_INCREMENT PRIMARY KEY,
    estado BOOLEAN,
    ubicacion_latitud DECIMAL(9,6) NOT NULL,
    ubicacion_longitud DECIMAL(9,6) NOT NULL,
    capacidad_de_carga INT,
    eficiencia VARCHAR(50),
    energetica INT
);

CREATE TABLE Estanteria (
    id_estanteria INT AUTO_INCREMENT PRIMARY KEY,
    ubicacion_latitud DECIMAL(9,6) NOT NULL,
    ubicacion_longitud DECIMAL(9,6) NOT NULL,
    capacidad_de_almacenamiento INT,
    nivel_de_uso INT,
    condicion VARCHAR(50)
);

CREATE TABLE Producto (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    dimensiones INT,
    cantidad_en_inventario INT,
    peso INT
);

CREATE TABLE Pedido (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    fecha_de_pedido DATE NOT NULL,
    estado_de_pedido BOOLEAN,
    cliente VARCHAR(50),
    fecha_de_entrega DATE,
    estimada VARCHAR(50)
);

CREATE TABLE Drone (
    id_drone INT AUTO_INCREMENT PRIMARY KEY,
    estado BOOLEAN,
    ubicacion_latitud DECIMAL(9,6),
    ubicacion_longitud DECIMAL(9,6),
    autonomia INT,
    capacidad_de_carga INT
);

CREATE TABLE Robot_de_empaque (
    id_robot_de_empaque INT AUTO_INCREMENT PRIMARY KEY,
    estado BOOLEAN,
    ubicacion_latitud DECIMAL(9,6),
    ubicacion_longitud DECIMAL(9,6),
    capacidad_de_empaque INT,
    energetica INT
);

CREATE TABLE Modulo_de_energia (
    id_modulo INT AUTO_INCREMENT PRIMARY KEY,
    capacidad INT,
    energetica INT,
    estado BOOLEAN,
    ubicacion_latitud DECIMAL(9,6),
    ubicacion_longitud DECIMAL(9,6)
);

CREATE TABLE Personal_humano (
    id_personal INT AUTO_INCREMENT PRIMARY KEY,
    DNI_NIE VARCHAR(9),
    nombre VARCHAR(40),
    apellido VARCHAR(40),
    rol VARCHAR(50),
    turno BOOLEAN,
    especialidad VARCHAR(50)
);

CREATE TABLE Robot_AL_Estanteria (
    id_robot_al_estanteria INT AUTO_INCREMENT PRIMARY KEY,
    fecha_de_operacion TIME NOT NULL,
    duracion_de_la_operacion TIME NOT NULL,
    id_robot_almacenamiento INT,
    id_estanteria INT,
    FOREIGN KEY (id_robot_almacenamiento) REFERENCES Robot_de_AL(id_robot_almacenamiento),
    FOREIGN KEY (id_estanteria) REFERENCES Estanteria(id_estanteria)
);

CREATE TABLE Estanteria_Producto (
    id_estanteria_producto INT AUTO_INCREMENT PRIMARY KEY,
    cantidad_almacenada INT NOT NULL,
    id_producto INT,
    id_estanteria INT,
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto),
    FOREIGN KEY (id_estanteria) REFERENCES Estanteria(id_estanteria)
);

CREATE TABLE Producto_Pedido (
    id_producto_pedido INT AUTO_INCREMENT PRIMARY KEY,
    cantidad_pedida INT NOT NULL,
    id_producto INT,
    id_pedido INT,
    FOREIGN KEY (id_producto) REFERENCES Producto(id_producto),
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido)
);

CREATE TABLE Pedido_Drone_Entrega (
    id_pedido_drone INT AUTO_INCREMENT PRIMARY KEY,
    fecha_de_entrega DATE NOT NULL,
    duracion_de_entrega TIME NOT NULL,
    id_pedido INT,
    id_drone INT,
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido),
    FOREIGN KEY (id_drone) REFERENCES Drone(id_drone)
);

CREATE TABLE Robot_de_empaque_Pedido (
    id_robot_de_empaque_pedido INT AUTO_INCREMENT PRIMARY KEY,
    fecha_de_empaque DATE NOT NULL,
    tiempo_de_proceso TIME NOT NULL,
    id_robot_de_empaque INT,
    id_pedido INT,
    FOREIGN KEY (id_robot_de_empaque) REFERENCES Robot_de_empaque(id_robot_de_empaque),
    FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido)
);

CREATE TABLE Modulo_de_energia_Robot_AL_EM_DR (
    id_modulo_robot_AL_EM_DR INT AUTO_INCREMENT PRIMARY KEY,
    fecha_recarga DATE NOT NULL,
    duracion_recarga TIME NOT NULL,
    id_modulo INT,
    id_robot_almacenamiento INT,
    id_robot_de_empaque INT,
    id_drone INT,
    FOREIGN KEY (id_modulo) REFERENCES Modulo_de_energia(id_modulo),
    FOREIGN KEY (id_robot_almacenamiento) REFERENCES Robot_de_AL(id_robot_almacenamiento),
    FOREIGN KEY (id_robot_de_empaque) REFERENCES Robot_de_empaque(id_robot_de_empaque),
    FOREIGN KEY (id_drone) REFERENCES Drone(id_drone)
);

CREATE TABLE Personal_humano_Robot_AL_EM_DR (
    id_personal_robot_AL_EM_DR INT AUTO_INCREMENT PRIMARY KEY,
    fecha_mantenimiento DATE NOT NULL,
    descripcion_de_la_tarea VARCHAR(100),
    id_personal INT,
    id_robot_almacenamiento INT,
    id_robot_de_empaque INT,
    id_drone INT,
    FOREIGN KEY (id_personal) REFERENCES Personal_humano(id_personal),
    FOREIGN KEY (id_robot_almacenamiento) REFERENCES Robot_de_AL(id_robot_almacenamiento),
    FOREIGN KEY (id_robot_de_empaque) REFERENCES Robot_de_empaque(id_robot_de_empaque),
    FOREIGN KEY (id_drone) REFERENCES Drone(id_drone)
);


-- Insertar datos en Robot_de_AL
INSERT INTO Robot_de_AL (estado, ubicacion_latitud, ubicacion_longitud, capacidad_de_carga, eficiencia, energetica) VALUES
(false, -23.5505, -46.6333, 85, 'Alta', 90),
(true, 48.8566, 2.3522, 75, 'Media', 82),
(false, 34.6937, 135.5023, 65, 'Baja', 78),
(true, 55.7558, 37.6173, 70, 'Alta', 88),
(false, -33.8688, 151.2093, 95, 'Media', 85)
(true, 28.7041, 77.1025, 90, 'Alta', 95),
(false, -34.6037, -58.3816, 70, 'Media', 85),
(true, 59.3293, 18.0686, 80, 'Baja', 78),
(false, 37.9838, 23.7275, 85, 'Alta', 90),
(true, 6.5244, 3.3792, 95, 'Media', 88),
(false, 50.1109, 8.6821, 75, 'Alta', 92),
(true, -15.7942, -47.8822, 85, 'Media', 80),
(false, 55.9533, -3.1883, 70, 'Baja', 79),
(true, 25.7617, -80.1918, 100, 'Alta', 96),
(false, 35.6895, 51.3890, 65, 'Media', 81)
(true, 55.7558, 37.6173, 95, 'Alta', 97),
(false, -33.9249, 18.4241, 85, 'Media', 87),
(true, 35.6762, 139.6503, 90, 'Baja', 82),
(false, 19.4326, -99.1332, 100, 'Alta', 95),
(true, 47.6062, -122.3321, 92, 'Media', 88),
(false, -25.2744, 133.7751, 80, 'Alta', 91),
(true, 52.5200, 13.4050, 89, 'Media', 86),
(false, 41.9028, 12.4964, 78, 'Baja', 80),
(true, 40.7128, -74.0060, 105, 'Alta', 98),
(false, 35.6895, 139.6917, 70, 'Media', 83);

-- Insertar datos en Estanteria
INSERT INTO Estanteria (ubicacion_latitud, ubicacion_longitud, capacidad_de_almacenamiento, nivel_de_uso, condicion) VALUES
(37.7749, -122.4194, 240, 80, 'Buena'),
(51.1657, 10.4515, 170, 65, 'Regular'),
(19.4326, -99.1332, 200, 75, 'Excelente'),
(35.6895, 139.6917, 220, 85, 'Buena'),
(41.9028, 12.4964, 230, 90, 'Regular')
(41.3851, 2.1734, 260, 85, 'Buena'),
(-36.8485, 174.7633, 180, 75, 'Regular'),
(1.3521, 103.8198, 220, 90, 'Excelente'),
(45.4215, -75.6972, 240, 80, 'Buena'),
(12.9716, 77.5946, 210, 85, 'Regular'),
(-4.4419, 15.2663, 200, 78, 'Buena'),
(60.1695, 24.9354, 230, 92, 'Excelente'),
(18.5204, 73.8567, 195, 70, 'Regular'),
(31.2304, 121.4737, 250, 95, 'Buena'),
(-23.4425, -58.4438, 205, 83, 'Excelente')
(51.1657, 10.4515, 265, 88, 'Buena'),
(60.4720, 8.4689, 185, 76, 'Regular'),
(-14.2350, -51.9253, 225, 93, 'Excelente'),
(37.7749, -122.4194, 245, 82, 'Buena'),
(34.0522, -118.2437, 215, 87, 'Regular'),
(48.8566, 2.3522, 205, 80, 'Buena'),
(59.9343, 30.3351, 235, 94, 'Excelente'),
(39.9042, 116.4074, 200, 72, 'Regular'),
(-33.8688, 151.2093, 255, 97, 'Buena'),
(55.9533, -3.1883, 210, 85, 'Excelente');

-- Insertar datos en Producto
INSERT INTO Producto (nombre, dimensiones, cantidad_en_inventario, peso) VALUES
('Nintendo Switch OLED', 26, 130, 15),
('Sony WH-1000XM5', 18, 170, 10),
('PlayStation 5', 45, 95, 20),
('Google Pixel 7', 21, 200, 8),
('Canon EOS R5', 25, 150, 9),
('Microsoft Surface Pro 9', 29, 150, 12),
('Bose QuietComfort 45', 19, 180, 9),
('Oculus Quest 2', 35, 100, 18),
('Samsung Galaxy Z Fold 4', 22, 190, 7),
('DJI Mavic Air 2', 26, 160, 8),
('Garmin Fenix 7X', 13, 175, 6),
('Asus ROG Zephyrus G14', 39, 140, 15),
('GoPro HERO11 Black', 20, 130, 5),
('Xiaomi Mi Band 7', 14, 210, 3),
('Sony Alpha 7 IV', 28, 120, 11),
('Dell XPS 15', 32, 140, 13),
('Beats Studio 3', 18, 175, 10),
('PS5 DualSense Controller', 25, 120, 7),
('Google Pixel 7 Pro', 20, 200, 8),
('Raspberry Pi 4', 15, 180, 5),
('Sony WH-1000XM5', 17, 190, 9),
('Lenovo ThinkPad X1', 31, 135, 14),
('Samsung Odyssey G9', 50, 110, 20),
('Fitbit Charge 5', 12, 220, 3),
('Canon EOS R6', 30, 125, 12);

-- Insertar datos en Pedido
INSERT INTO Pedido (fecha_de_pedido, estado_de_pedido, cliente, fecha_de_entrega, estimada) VALUES
('2024-05-01', true, 'C-112233', '2024-05-03', '2 días'),
('2024-05-02', false, 'C-445566', '2024-05-04', '2 días'),
('2024-05-03', true, 'C-778899', '2024-05-05', '2 días'),
('2024-05-04', false, 'C-990011', '2024-05-06', '2 días'),
('2024-05-05', true, 'C-223344', '2024-05-07', '2 días'),
('2024-06-10', true, 'C-334455', '2024-06-12', '2 días'),
('2024-06-11', false, 'C-556677', '2024-06-13', '2 días'),
('2024-06-12', true, 'C-778899', '2024-06-14', '2 días'),
('2024-06-13', false, 'C-990011', '2024-06-15', '2 días'),
('2024-06-14', true, 'C-112233', '2024-06-16', '2 días'),
('2024-06-15', false, 'C-223344', '2024-06-17', '2 días'),
('2024-06-16', true, 'C-334455', '2024-06-18', '2 días'),
('2024-06-17', false, 'C-445566', '2024-06-19', '2 días'),
('2024-06-18', true, 'C-556677', '2024-06-20', '2 días'),
('2024-06-19', false, 'C-667788', '2024-06-21', '2 días'),
('2024-07-01', true, 'C-998877', '2024-07-03', '2 días'),
('2024-07-02', false, 'C-776655', '2024-07-04', '2 días'),
('2024-07-03', true, 'C-554433', '2024-07-05', '2 días'),
('2024-07-04', false, 'C-332211', '2024-07-06', '2 días'),
('2024-07-05', true, 'C-112233', '2024-07-07', '2 días'),
('2024-07-06', false, 'C-221144', '2024-07-08', '2 días'),
('2024-07-07', true, 'C-334455', '2024-07-09', '2 días'),
('2024-07-08', false, 'C-556677', '2024-07-10', '2 días'),
('2024-07-09', true, 'C-778899', '2024-07-11', '2 días'),
('2024-07-10', false, 'C-990011', '2024-07-12', '2 días');

-- Insertar datos en Drone
INSERT INTO Drone (estado, ubicacion_latitud, ubicacion_longitud, autonomia, capacidad_de_carga) VALUES
(true, 60.1699, 24.9384, 140, 25),
(false, -26.2041, 28.0473, 100, 15),
(true, 39.9042, 116.4074, 160, 30),
(false, 35.6762, 139.6503, 120, 20),
(true, 52.5200, 13.4050, 110, 22),
(true, 49.2827, -123.1207, 135, 26),
(false, -41.2865, 174.7762, 120, 18),
(true, 22.3193, 114.1694, 155, 30),
(false, 59.9139, 10.7522, 110, 22),
(true, 40.4168, -3.7038, 140, 28),
(false, -37.8136, 144.9631, 100, 16),
(true, 24.7136, 46.6753, 145, 27),
(false, 54.6872, 25.2797, 125, 21),
(true, 33.6844, 73.0479, 130, 23),
(false, 43.6511, -79.3470, 105, 19),
(true, -26.2041, 28.0473, 140, 29),
(false, 13.7563, 100.5018, 115, 20),
(true, 41.3851, 2.1734, 160, 33),
(false, 37.5665, 126.9780, 120, 25),
(true, 45.5088, -73.5878, 145, 31),
(false, -22.9068, -43.1729, 105, 18),
(true, 50.8503, 4.3517, 150, 30),
(false, 40.4168, -3.7038, 130, 23),
(true, 1.3521, 103.8198, 135, 27),
(false, 52.5200, 13.4050, 110, 19);

-- Insertar datos en Robot_de_empaque
INSERT INTO Robot_de_empaque (estado, ubicacion_latitud, ubicacion_longitud, capacidad_de_empaque, energetica) VALUES
(false, 40.7128, -74.0060, 35, 88),
(true, 13.7563, 100.5018, 28, 80),
(false, -22.9068, -43.1729, 40, 92),
(true, 19.0760, 72.8777, 30, 85),
(true, 37.5665, 126.9780, 38, 87),
(false, 52.3676, 4.9041, 32, 86),
(true, 39.2904, -76.6122, 29, 82),
(false, 41.2565, -95.9345, 40, 91),
(true, 30.0444, 31.2357, 31, 89),
(true, 36.1627, -86.7816, 35, 94),
(false, 64.1355, -21.8954, 28, 77),
(true, -29.8587, 31.0218, 38, 92),
(false, 14.5995, 120.9842, 27, 83),
(true, 25.2048, 55.2708, 36, 96),
(false, 37.9838, 23.7275, 30, 81),
(false, 35.6895, 139.6917, 34, 89),
(true, -3.745, -73.2472, 31, 84),
(false, 30.2672, -97.7431, 42, 92),
(true, 25.2769, 55.2962, 33, 90),
(true, -23.5505, -46.6333, 37, 95),
(false, 43.7384, 7.4246, 30, 79),
(true, 55.7558, 37.6173, 40, 97),
(false, 12.9716, 77.5946, 29, 85),
(true, 51.5074, -0.1278, 38, 98),
(false, -37.8136, 144.9631, 32, 83);


-- Insertar datos en Modulo_de_energia
INSERT INTO Modulo_de_energia (capacidad, energetica, estado, ubicacion_latitud, ubicacion_longitud) VALUES
(480, 92, true, -1.2921, 36.8219),
(420, 89, false, 55.7558, 37.6173),
(500, 97, true, -35.2809, 149.1300),
(550, 95, true, 25.276987, 55.296249),
(430, 85, false, 31.2304, 121.4737),
(470, 94, true, -8.4095, 115.1889),
(430, 89, false, 32.0853, 34.7818),
(520, 98, true, 45.7640, 4.8357),
(550, 97, true, 23.6345, -102.5528),
(480, 88, false, -12.0464, -77.0428),
(510, 92, true, 37.5665, 126.9780),
(460, 85, false, 55.7558, 37.6173),
(490, 95, true, 19.0758, 72.8777),
(530, 90, false, -22.9068, -43.1729),
(495, 87, true, 34.0522, -118.2437),
(460, 96, true, 10.8231, 106.6297),
(420, 88, false, 3.1390, 101.6869),
(540, 99, true, -35.2820, 149.1287),
(570, 100, true, 48.2082, 16.3738),
(490, 91, false, -4.4419, 15.2663),
(530, 94, true, 59.3293, 18.0686),
(510, 90, false, 28.6139, 77.2090),
(500, 89, true, -33.4489, -70.6693),
(520, 97, false, 35.6762, 139.6503),
(475, 85, true, 31.7683, 35.2137);


-- 1. Actualizar la eficiencia de los robots de almacenamiento con eficiencia 'Baja'
UPDATE Robot_de_AL
SET eficiencia = 'Media'
WHERE eficiencia = 'Baja';

-- 2. Aumentar el stock de un producto específico
UPDATE Producto
SET cantidad_en_inventario = cantidad_en_inventario + 50
WHERE nombre = 'PlayStation 5';

-- 3. Desactivar drones que han agotado su autonomía
UPDATE Drone
SET estado = FALSE
WHERE autonomia = 0;

-- 4. Actualizar el estado de pedidos entregados
UPDATE Pedido
SET estado_de_pedido = TRUE
WHERE fecha_de_entrega <= CURDATE();

-- 5. Reducir la capacidad de almacenamiento de estanterías en mal estado
UPDATE Estanteria
SET capacidad_de_almacenamiento = capacidad_de_almacenamiento - 20
WHERE condicion = 'Regular';



-- 1. Obtener los productos y su ubicación en las estanterías
SELECT P.nombre, E.id_estanteria, E.ubicacion_latitud, E.ubicacion_longitud
FROM Producto P
JOIN Estanteria_Producto EP ON P.id_producto = EP.id_producto
JOIN Estanteria E ON EP.id_estanteria = E.id_estanteria;

-- 2. Listar los pedidos con los productos que contienen
SELECT Pe.id_pedido, Pe.cliente, P.nombre, PP.cantidad_pedida
FROM Pedido Pe
JOIN Producto_Pedido PP ON Pe.id_pedido = PP.id_pedido
JOIN Producto P ON PP.id_producto = P.id_producto;

-- 3. Mostrar los drones que han realizado entregas de pedidos
SELECT D.id_drone, D.estado, Pe.id_pedido, Pe.fecha_de_pedido
FROM Drone D
JOIN Pedido_Drone_Entrega PDE ON D.id_drone = PDE.id_drone
JOIN Pedido Pe ON PDE.id_pedido = Pe.id_pedido;

-- 4. Obtener el historial de mantenimiento de los robots
SELECT PH.nombre, PH.apellido, RAL.id_robot_almacenamiento, RE.id_robot_de_empaque, DR.id_drone, PHRE.fecha_mantenimiento
FROM Personal_humano PH
JOIN Personal_humano_Robot_AL_EM_DR PHRE ON PH.id_personal = PHRE.id_personal
LEFT JOIN Robot_de_AL RAL ON PHRE.id_robot_almacenamiento = RAL.id_robot_almacenamiento
LEFT JOIN Robot_de_empaque RE ON PHRE.id_robot_de_empaque = RE.id_robot_de_empaque
LEFT JOIN Drone DR ON PHRE.id_drone = DR.id_drone;

-- 5. Obtener la cantidad de productos por estantería
SELECT E.id_estanteria, E.ubicacion_latitud, E.ubicacion_longitud, SUM(EP.cantidad_almacenada) AS total_productos
FROM Estanteria E
JOIN Estanteria_Producto EP ON E.id_estanteria = EP.id_estanteria
GROUP BY E.id_estanteria, E.ubicacion_latitud, E.ubicacion_longitud;