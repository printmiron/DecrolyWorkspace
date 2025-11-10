USE football_db;
CREATE TABLE equipo (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(255) NOT NULL
);
CREATE TABLE futbolista (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(255) NOT NULL,
posicion VARCHAR(50),
equipo_id BIGINT,
FOREIGN KEY (equipo_id) REFERENCES equipo(id)
);
INSERT INTO equipo (nombre) VALUES ('Equipo A'), ('Equipo B'), ('Equipo C'), ('Equipo D'),
('Equipo E');
INSERT INTO futbolista (nombre, posicion, equipo_id) VALUES
('Futbolista 1', 'Delantero', 1),
('Futbolista 2', 'Portero', 1),
('Futbolista 3', 'Defensa', 2),
('Futbolista 4', 'Mediocampista', 2),
('Futbolista 5', 'Delantero', 3),
('Futbolista 6', 'Defensa', 3),
('Futbolista 7', 'Mediocampista', 4),
('Futbolista 8', 'Portero', 4),
('Futbolista 9', 'Delantero', 5),
('Futbolista 10', 'Defensa', 5);

