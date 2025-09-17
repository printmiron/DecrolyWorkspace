DROP DATABASE IF EXISTS schema_bbdd;
CREATE DATABASE IF NOT EXISTS schema_bbdd;
USE schema_bbdd;

-- Tabla Consola
CREATE TABLE Consola (
    Nombre 					VARCHAR(50) NOT NULL PRIMARY KEY,
    Empresa_desarrolladora 	VARCHAR(100) NOT NULL
);

-- Tabla Usuario
CREATE TABLE Usuario (
    ID 						INT AUTO_INCREMENT PRIMARY KEY,
    Nombre 					VARCHAR(50) NOT NULL,
    Apellidos 				VARCHAR(100) NOT NULL,
    Usuario 				VARCHAR(30) NOT NULL UNIQUE,
    Contasena				VARCHAR(30) NOT NULL,
    Correo 					VARCHAR(100) NOT NULL UNIQUE,
    Fecha_Nacimiento 		DATE NOT NULL
);

-- Tabla Videojuegos
CREATE TABLE Videojuegos (
    ID 						INT AUTO_INCREMENT PRIMARY KEY,
    Consola_Nombre 			VARCHAR(100) NOT NULL,
    Nombre 					VARCHAR(100) NOT NULL,
    Genero 					ENUM('ACCION', 'AVENTURA', 'CATASTROFE', 'CIENCIA_FICCION', 'COMEDIA', 'DOCUMENTALES', 'DRAMA', 'FANTASIA'),
    Desarrollador 			VARCHAR(100) NOT NULL,
    Precio 					DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (Consola_Nombre) REFERENCES Consola(Nombre)
);

-- 🛠️ Corrección: Tabla valoracion_empresa ahora enlaza con videojuegos
CREATE TABLE valoracion_empresa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_empresa VARCHAR(100),
    puntuacion INT,
    Videojuego_ID INT NOT NULL,
    FOREIGN KEY (Videojuego_ID) REFERENCES Videojuegos(ID)
);

-- Tabla Valoracion_usuario
CREATE TABLE Valoracion_Usuario (
    ID 						INT AUTO_INCREMENT PRIMARY KEY,
    Videojuego_ID 			INT NOT NULL,
    Usuario_ID 				INT NOT NULL,
    Puntuacion 				TINYINT NOT NULL CHECK (Puntuacion BETWEEN 1 AND 100),
    Comentario 				TEXT,
    Fecha_valoracion 		TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (Videojuego_ID) REFERENCES Videojuegos(ID),
    FOREIGN KEY (Usuario_ID) REFERENCES Usuario(ID),
    UNIQUE KEY (Videojuego_ID, Usuario_ID)
);



INSERT INTO Consola (Nombre, Empresa_desarrolladora) VALUES 
('PlayStation 5', 'Sony Interactive Entertainment'),
('Xbox Series X', 'Microsoft'),
('Nintendo Switch', 'Nintendo'),
('PC', 'Varios'),
('PlayStation 4', 'Sony Interactive Entertainment');

INSERT INTO Usuario (Nombre, Apellidos, Usuario, Contasena, Correo, Fecha_Nacimiento) VALUES 
('Juan', 'Pérez García', 'juanpg', 'password123', 'juan.perez@email.com', '1990-05-15'),
('María', 'López Martínez', 'marialm', 'securepass', 'maria.lopez@email.com', '1985-08-22'),
('Carlos', 'González Ruiz', 'carlosgr', 'mypass123', 'carlos.gonzalez@email.com', '1995-03-10'),
('Ana', 'Martín Sánchez', 'anams', 'anapass', 'ana.martin@email.com', '1992-11-30'),
('David', 'Fernández Castro', 'davidfc', 'davidpass', 'david.fernandez@email.com', '1988-07-18');

INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES 
('PlayStation 5', 'God of War Ragnarök', 'ACCION', 'Santa Monica Studio', 69.99),
('Xbox Series X', 'Halo Infinite', 'CIENCIA_FICCION', '343 Industries', 59.99),
('PlayStation 5', 'The Legend of Zelda: Breath of the Wild', 'AVENTURA', 'Nintendo EPD', 59.99),
('Nintendo Switch','Cyberpunk 2077', 'CIENCIA_FICCION', 'CD Projekt Red', 49.99),
('PC', 'The Last of Us Part II', 'DRAMA', 'Naughty Dog', 39.99);

INSERT INTO Valoracion_Usuario (Videojuego_ID, Usuario_ID, Puntuacion, Comentario) VALUES 
(1, 1, 95, 'Increíble experiencia de juego, gráficos asombrosos'),
(1, 2, 90, 'Muy buena historia pero algo corto'),
(2, 3, 85, 'Multiplayer excelente, campaña regular'),
(3, 4, 100, 'La mejor aventura que he jugado en años'),
(4, 5, 75, 'Buen RPG pero con muchos bugs al lanzamiento');



-- CREATE VIEW Valoracion_Global AS
-- SELECT 
--     v.ID AS Videojuego_ID,
--     v.Nombre AS Nombre_Videojuego,
--     COALESCE(AVG(u.Puntuacion), 0) AS Puntuacion_Media_Usuarios,
--     COALESCE(AVG(e.Puntuacion), 0) AS Puntuacion_Media_Empresas,
--     (COALESCE(AVG(u.Puntuacion), 0) * 0.5 + COALESCE(AVG(e.Puntuacion), 0) * 0.5) AS Puntuacion_Global
-- FROM 
--     Videojuegos v
-- LEFT JOIN 
--     Valoracion_Usuario u ON v.ID = u.Videojuego_ID
-- LEFT JOIN 
--     Valoracion_Empresa e ON v.ID = e.Videojuego_ID
-- GROUP BY 
--     v.ID, v.Nombre;

-- SELECT Videojuegos.ID, Videojuegos.Consola_Nombre, Videojuegos.Nombre, Videojuegos.Genero, Videojuegos.Desarrollador, Videojuegos.Precio,
-- COALESCE(AVG(Valoracion_Usuario.Puntuacion), 0.0) AS PuntuacionGlobal  
-- FROM Videojuegos 
-- LEFT JOIN Valoracion_Usuario  ON Videojuegos.ID = Valoracion_Usuario.Videojuego_ID 
-- WHERE Videojuegos.Consola_Nombre = 'PlayStation 5'
-- GROUP BY Videojuegos.ID, Videojuegos.Consola_Nombre, Videojuegos.Nombre, Videojuegos.Genero, Videojuegos.Desarrollador, Videojuegos.Precio;

INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Official area.', 'DOCUMENTALES', 'Schmitt, Clark and Bentley', 19.43);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Region.', 'FANTASIA', 'Mcguire-Monroe', 41.73);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Give office sure.', 'ACCION', 'White-Harris', 66.32);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Recently million.', 'CATASTROFE', 'Lyons PLC', 29.75);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Along job walk.', 'ACCION', 'Peterson, Knapp and Webb', 31.87);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Vote.', 'CIENCIA_FICCION', 'Drake, Mann and Simmons', 18.15);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Talk increase career.', 'CIENCIA_FICCION', 'Lane PLC', 56.29);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Maintain throughout.', 'FANTASIA', 'Waller-Jacobs', 56.62);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Expect east something.', 'DRAMA', 'Morgan-Cantu', 20.92);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Draw stop.', 'AVENTURA', 'Cox and Sons', 51.34);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Even price.', 'COMEDIA', 'Brown and Sons', 32.87);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'None control.', 'COMEDIA', 'Valdez, Williams and Larson', 49.09);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Series under.', 'FANTASIA', 'Roberson, Mccormick and Mitchell', 49.7);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Popular suggest.', 'FANTASIA', 'Saunders, Lopez and King', 23.82);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'State bad.', 'FANTASIA', 'Robinson PLC', 22.84);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'People price.', 'CATASTROFE', 'Williams Ltd', 48.81);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Fire water feeling.', 'DOCUMENTALES', 'Dominguez, Wilson and Chung', 63.06);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Become event use.', 'DOCUMENTALES', 'Mclaughlin-Howard', 11.64);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Feeling.', 'DOCUMENTALES', 'Munoz Inc', 59.76);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Lay.', 'AVENTURA', 'Porter-Raymond', 66.47);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'War able nearly.', 'ACCION', 'Myers-Miller', 75.48);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Go training.', 'COMEDIA', 'Scott LLC', 73.7);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Main oil.', 'ACCION', 'Lawrence-Myers', 77.08);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Account beautiful manage.', 'CIENCIA_FICCION', 'Morse, Galvan and Hill', 36.56);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Fear.', 'FANTASIA', 'Shaw Group', 27.26);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Past buy.', 'ACCION', 'Black-Mitchell', 28.01);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Yeah recognize.', 'CIENCIA_FICCION', 'Powell and Sons', 67.93);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Husband opportunity.', 'FANTASIA', 'Briggs-Moore', 33.18);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Sometimes understand.', 'DRAMA', 'Sandoval Group', 32.41);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'And movement air.', 'DRAMA', 'Palmer PLC', 70.6);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Price.', 'DOCUMENTALES', 'Bennett-Hobbs', 21.44);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Pick town at.', 'AVENTURA', 'Hill-Tanner', 78.22);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Around music east couple.', 'ACCION', 'Melendez-Murray', 42.35);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'May interesting.', 'ACCION', 'Castillo, Ramirez and Sandoval', 38.07);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Adult kid out.', 'DOCUMENTALES', 'Greer Ltd', 71.96);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Good physical seven.', 'AVENTURA', 'Olsen, Wilcox and Moore', 48.59);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Order performance.', 'DOCUMENTALES', 'Bryant Inc', 57.03);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Image method.', 'DRAMA', 'Smith-Walker', 36.06);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Response fly.', 'DOCUMENTALES', 'Nelson-Green', 71.02);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Direction six.', 'AVENTURA', 'Johnson, Guzman and Wilson', 54.4);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Poor himself probably.', 'CIENCIA_FICCION', 'Montgomery, Santos and Kline', 66.64);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Kid sound manager.', 'AVENTURA', 'Rogers Ltd', 37.97);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Remember.', 'ACCION', 'Brown, Sosa and Vasquez', 76.98);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Kitchen and.', 'ACCION', 'Cantu Inc', 17.3);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Rest she agreement.', 'AVENTURA', 'Sloan PLC', 22.62);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Including again writer.', 'ACCION', 'Clark, Lozano and Rodriguez', 39.85);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Area resource book.', 'ACCION', 'Jackson, Maldonado and Jones', 27.89);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Tonight live cover.', 'DOCUMENTALES', 'Baxter, Davis and Clark', 66.26);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Difficult ability prove.', 'FANTASIA', 'Clark-Delacruz', 64.7);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Statement.', 'COMEDIA', 'Williams, Robertson and Cruz', 54.01);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Democrat visit public.', 'DOCUMENTALES', 'Mccoy and Sons', 73.33);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Issue four too.', 'CIENCIA_FICCION', 'Mayo-White', 66.53);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Change approach best.', 'FANTASIA', 'White-Peck', 25.6);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Happen same discover.', 'FANTASIA', 'Edwards-Doyle', 68.04);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Program like trade ability.', 'DRAMA', 'Hall Group', 35.05);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Point threat music.', 'ACCION', 'Moran, Williams and Huber', 36.31);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Report whom no.', 'AVENTURA', 'Beck, Ford and Ortiz', 54.9);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Option policy.', 'CIENCIA_FICCION', 'Solomon-Ramirez', 42.28);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Blood market fight.', 'COMEDIA', 'Edwards-Phillips', 40.88);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Include week no.', 'CIENCIA_FICCION', 'Martinez-Ritter', 20.61);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'American.', 'AVENTURA', 'Johnson-Gilbert', 26.04);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Lead form girl.', 'FANTASIA', 'Chen, Davenport and Wiley', 78.65);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Western glass southern.', 'AVENTURA', 'Martin-Williams', 76.04);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'But through serve pressure.', 'CATASTROFE', 'Fritz-Harrison', 55.19);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Let charge.', 'FANTASIA', 'Hill Inc', 76.64);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Purpose factor girl.', 'FANTASIA', 'Roth-Murphy', 21.87);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Investment relationship scientist.', 'CIENCIA_FICCION', 'Weaver and Sons', 16.1);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Study middle.', 'COMEDIA', 'Dalton and Sons', 20.37);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Identify rich.', 'DRAMA', 'Brewer Ltd', 20.89);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'They.', 'CIENCIA_FICCION', 'Flynn PLC', 35.23);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Near.', 'CATASTROFE', 'Duran and Sons', 49.32);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Have week.', 'CATASTROFE', 'Smith PLC', 34.18);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Out crime parent.', 'FANTASIA', 'Hall Group', 63.89);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Open season enter.', 'ACCION', 'Manning-Smith', 39.31);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Marriage often decide.', 'FANTASIA', 'Delgado-Riggs', 15.05);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Turn low.', 'COMEDIA', 'Thompson Inc', 74.13);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'When remain.', 'AVENTURA', 'Hampton-Lopez', 43.89);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Some draw.', 'CIENCIA_FICCION', 'Collins and Sons', 44.83);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Coach similar.', 'COMEDIA', 'Bell, Burke and Robles', 43.25);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Defense.', 'CATASTROFE', 'Miller PLC', 51.23);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Before capital.', 'DRAMA', 'Rodriguez Group', 37.76);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Hit their.', 'DOCUMENTALES', 'Matthews LLC', 37.79);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Smile successful.', 'ACCION', 'Jenkins, Cortez and Ramsey', 43.3);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Red can.', 'DRAMA', 'Johnson-Mckee', 21.69);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Clear year enjoy.', 'CATASTROFE', 'Quinn, Morales and Gonzales', 12.29);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'After analysis back.', 'COMEDIA', 'Skinner Group', 21.77);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Serious chair radio budget.', 'CATASTROFE', 'Chan, Cox and Lamb', 33.01);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Child husband begin operation.', 'FANTASIA', 'Rodriguez-Nichols', 14.67);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Republican own election.', 'ACCION', 'Kennedy-Hodge', 37.43);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Day information dinner structure.', 'DRAMA', 'Brown-Mccall', 51.8);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Cultural page.', 'DOCUMENTALES', 'Young, Heath and Chandler', 76.69);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Act back song.', 'CATASTROFE', 'Ayala Ltd', 11.1);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Official language.', 'CATASTROFE', 'Estrada-Kramer', 27.38);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Pressure study sort.', 'DRAMA', 'Martin Group', 63.35);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Training history.', 'AVENTURA', 'Brown Inc', 59.75);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Way program situation me.', 'DOCUMENTALES', 'Hart, Diaz and Jones', 39.56);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'These.', 'COMEDIA', 'Hopkins, Keller and Frazier', 40.93);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Child including.', 'CATASTROFE', 'Lindsey, Lynch and Mcbride', 41.42);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Cause.', 'FANTASIA', 'Patrick-King', 18.86);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Wall artist.', 'FANTASIA', 'Collins-Smith', 71.53);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Friend interesting operation.', 'CATASTROFE', 'Hancock-Hurley', 26.68);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Quite growth suddenly.', 'AVENTURA', 'Barrett, White and Harvey', 64.47);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Base care.', 'CIENCIA_FICCION', 'Ross-Howard', 20.78);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Item stand contain.', 'DRAMA', 'Johnson-Figueroa', 50.77);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Recently relate nature.', 'COMEDIA', 'Burke, Hart and Cherry', 65.63);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Reflect agreement rest.', 'CIENCIA_FICCION', 'Perez PLC', 69.14);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Too local build.', 'AVENTURA', 'Williams PLC', 15.86);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Year.', 'DRAMA', 'Dean PLC', 32.24);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Short father.', 'FANTASIA', 'Dawson, Fields and Hunter', 25.56);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Mission.', 'ACCION', 'Martin-George', 17.09);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Soon opportunity.', 'DOCUMENTALES', 'Watson, Burch and Torres', 62.82);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Treat.', 'DRAMA', 'Dyer, Mack and Hoover', 68.53);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Plan finish.', 'DOCUMENTALES', 'Lopez-Oliver', 44.98);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Relate trade respond.', 'CATASTROFE', 'Rhodes-Hughes', 48.12);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Unit left.', 'FANTASIA', 'Ford Inc', 29.1);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Per focus.', 'AVENTURA', 'Hubbard, Elliott and Edwards', 23.22);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Address bar.', 'CIENCIA_FICCION', 'Price, Mendez and Vaughan', 35.37);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Special.', 'CATASTROFE', 'George PLC', 32.81);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Rate miss.', 'AVENTURA', 'Ryan-Sanchez', 14.4);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Stuff.', 'COMEDIA', 'Valencia, Quinn and Savage', 56.68);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Kind door.', 'AVENTURA', 'Boyd, Wood and Davis', 56.75);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Move.', 'AVENTURA', 'Foster-Curry', 55.94);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Yes including bill.', 'DOCUMENTALES', 'Harris Ltd', 24.96);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Easy go dinner.', 'DOCUMENTALES', 'Jackson, Burns and Torres', 32.0);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Nature require.', 'COMEDIA', 'Jefferson LLC', 32.64);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Room boy democratic.', 'CATASTROFE', 'Lewis-Davis', 39.77);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Past police argue.', 'DOCUMENTALES', 'Griffin-Garcia', 49.05);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Finish tend.', 'COMEDIA', 'Wong PLC', 72.56);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Large stay.', 'COMEDIA', 'Moore and Sons', 25.21);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'While necessary participant few.', 'ACCION', 'White, Wright and Scott', 23.95);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Various course.', 'COMEDIA', 'Escobar-Armstrong', 38.68);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Building authority prepare.', 'AVENTURA', 'Powell-Myers', 69.47);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Fall appear successful.', 'CATASTROFE', 'Thomas, Mcdaniel and Cruz', 15.25);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'According among.', 'COMEDIA', 'Knight-Pena', 75.84);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Action radio ok.', 'DRAMA', 'Dudley Inc', 75.46);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Director not.', 'DOCUMENTALES', 'Li Group', 19.52);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Other onto.', 'FANTASIA', 'Herring Inc', 76.13);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Kid change morning.', 'DRAMA', 'Miller, Gibson and Guzman', 12.43);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Outside everybody provide.', 'DRAMA', 'Wyatt, Garcia and Lopez', 60.89);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Tax pass.', 'FANTASIA', 'Robinson and Sons', 69.6);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Positive into.', 'DOCUMENTALES', 'Wallace, Johnson and Jones', 15.78);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Beat study.', 'ACCION', 'Frank, Bishop and Barton', 24.28);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Beautiful purpose.', 'ACCION', 'Jones-Montoya', 40.57);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Compare image building.', 'CIENCIA_FICCION', 'Jenkins LLC', 41.35);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Place simply trip.', 'ACCION', 'Gomez-Craig', 32.54);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Body.', 'FANTASIA', 'Knox-Duncan', 70.03);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Only cup.', 'DRAMA', 'Scott, Middleton and Winters', 74.02);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'No tough recent.', 'CATASTROFE', 'Robinson-Rodriguez', 71.24);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Likely fear.', 'COMEDIA', 'Price-Cisneros', 67.88);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Tell five.', 'CIENCIA_FICCION', 'Fields, Schmitt and Bautista', 37.13);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Technology task hand.', 'CIENCIA_FICCION', 'Walker LLC', 51.28);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'So dog image.', 'DRAMA', 'Mcclure, Young and Perez', 53.08);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Speak board.', 'ACCION', 'Bishop-Brown', 74.12);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'At Mrs perhaps.', 'AVENTURA', 'Clayton, Gonzales and Hester', 70.34);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Key artist.', 'CATASTROFE', 'Martinez, Berry and Navarro', 58.9);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Pressure class.', 'COMEDIA', 'Adams, Simpson and Mccormick', 55.41);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Growth them.', 'DRAMA', 'Meadows, Watson and Casey', 75.0);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Partner current.', 'CIENCIA_FICCION', 'Hudson-Hudson', 50.84);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Stuff live amount.', 'AVENTURA', 'Harris and Sons', 63.1);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Daughter officer.', 'ACCION', 'Cole, Williams and Blair', 20.24);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Movie beautiful.', 'DRAMA', 'Harris Group', 17.05);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Change treat.', 'CIENCIA_FICCION', 'Moreno PLC', 58.09);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Son whose important.', 'COMEDIA', 'Moore PLC', 77.45);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Will suffer.', 'ACCION', 'Duke-Vargas', 68.54);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Business guy really.', 'CATASTROFE', 'Smith, Jones and Williams', 51.97);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Worry cup word.', 'CIENCIA_FICCION', 'Cohen-Miller', 38.04);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Former view must.', 'COMEDIA', 'Decker, Wise and Cooper', 29.92);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Realize.', 'AVENTURA', 'Simon-West', 70.51);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Want discover.', 'COMEDIA', 'Roberson-Ferguson', 55.78);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'College any skin.', 'AVENTURA', 'Thompson-Rivera', 48.2);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Say road.', 'AVENTURA', 'Williams LLC', 42.96);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Before phone include.', 'DRAMA', 'Hill Inc', 68.02);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Board thank enjoy.', 'FANTASIA', 'Cannon, Leon and Phillips', 41.32);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Worker prepare popular.', 'CIENCIA_FICCION', 'Campbell Ltd', 61.8);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Four trip road.', 'ACCION', 'Johnson, Clarke and Nelson', 32.5);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Feeling father way.', 'AVENTURA', 'Adams Inc', 70.47);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Realize realize board.', 'DOCUMENTALES', 'Hill-Harvey', 28.87);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Outside.', 'COMEDIA', 'Edwards-Lee', 44.84);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Sense clear at.', 'CATASTROFE', 'Boone, Smith and Finley', 57.57);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Local hope.', 'AVENTURA', 'Henderson, Ross and Howard', 65.51);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Call without respond.', 'AVENTURA', 'Tran Inc', 79.03);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Blood business.', 'COMEDIA', 'Hall-Miller', 21.23);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Property parent natural.', 'AVENTURA', 'Bennett, Stone and Spence', 72.91);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Help however.', 'CIENCIA_FICCION', 'Carter Inc', 54.59);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Develop on.', 'CIENCIA_FICCION', 'Gay-Ward', 46.54);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Their news southern.', 'FANTASIA', 'Horton, Rollins and Nichols', 17.65);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Chance right half.', 'CIENCIA_FICCION', 'Richardson, Brown and Baker', 41.75);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Seem save.', 'CATASTROFE', 'Chang-Ryan', 67.85);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Five option heavy.', 'AVENTURA', 'Avila, Mason and Mills', 62.96);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Or end.', 'CATASTROFE', 'Stephens Inc', 77.97);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Single.', 'AVENTURA', 'Bond-Johnson', 74.86);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'While talk tend.', 'CIENCIA_FICCION', 'Martin, Davis and Wilson', 71.12);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 5', 'Court accept.', 'CATASTROFE', 'Allen-Haynes', 39.77);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Account fill.', 'AVENTURA', 'Eaton LLC', 54.15);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Energy make.', 'AVENTURA', 'Baker, Thomas and Price', 53.03);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Nintendo Switch', 'Positive campaign sea.', 'ACCION', 'Parker, Morrow and Sullivan', 11.14);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Whose term.', 'CATASTROFE', 'Butler-Riggs', 26.67);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('Xbox Series X', 'Way model project.', 'COMEDIA', 'Barker, Aguilar and Reed', 66.25);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PC', 'Case.', 'CIENCIA_FICCION', 'Ware-Robinson', 13.01);
INSERT INTO Videojuegos (Consola_Nombre, Nombre, Genero, Desarrollador, Precio) VALUES ('PlayStation 4', 'Growth suddenly special.', 'COMEDIA', 'Nelson, Walker and Delacruz', 56.61);



