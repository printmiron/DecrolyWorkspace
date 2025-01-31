DROP DATABASE IF EXISTS ud3_ac4;
CREATE DATABASE IF NOT EXISTS ud3_ac4;
USE ud3_ac4;

DROP TABLE IF EXISTS dept_emp,
                     empleado, 
                     departamento;

CREATE TABLE empleado (
    cod_empleado      INT             NOT NULL,
    fecha_nacimiento  DATE            NOT NULL,
    nombre  VARCHAR(14)     NOT NULL,
    apellido   VARCHAR(16)     NOT NULL,
    sexo      ENUM ('M','F')  NOT NULL,    
    fecha_contratacion  DATE            NOT NULL,
    PRIMARY KEY (cod_empleado)
);

CREATE TABLE departamento (
    cod_departamento     CHAR(4)         NOT NULL,
    nombre_departamento   VARCHAR(40)     NOT NULL,
    PRIMARY KEY (cod_departamento),
    UNIQUE  KEY (nombre_departamento)
);

CREATE TABLE dept_emp (
    cod_empleado      INT             NOT NULL,
    salario 	DOUBLE 			NOT NULL,
    cod_departamento     CHAR(4)         NOT NULL,
    fecha_desde   DATE            NOT NULL,
    fecha_hasta     DATE            NOT NULL,
    FOREIGN KEY (cod_empleado)  REFERENCES empleado   (cod_empleado)  ON DELETE CASCADE,
    FOREIGN KEY (cod_departamento) REFERENCES departamento (cod_departamento) ON DELETE CASCADE,
    PRIMARY KEY (cod_empleado,cod_departamento)
);

INSERT INTO `empleado` VALUES 
(10001,'1953-09-02','Georgi','Facello','M','2021-06-26'),
(10002,'1964-06-02','Bezalel','Simmel','F','2018-11-21'),
(10003,'1979-12-03','Parto','Bamford','M','2019-08-28'),
(10004,'1982-05-01','Chirstian','Koblick','M','2022-12-01'),
(10005,'1983-01-21','Kyoichi','Maliniak','M','2019-09-12'),
(10006,'1990-04-20','Anneke','Preusig','F','2018-06-02'),
(10007,'1965-05-23','Tzvetan','Zielinski','F','2018-02-10'),
(10008,'1990-02-19','Saniya','Kalloufi','M','2021-09-15'),
(10009,'1991-04-19','Sumant','Peac','F','2019-02-18'),
(10010,'1978-06-01','Duangkaew','Piveteau','F','2018-08-24'),
(10011,'1972-11-07','Mary','Sluis','F','2018-01-22'),
(10012,'1988-09-02','Lucas','Perez','M','2019-06-26');

INSERT INTO `departamento` VALUES 
('d001','Marketing'),
('d002','Finance'),
('d003','Human Resources'),
('d004','Production'),
('d005','Development'),
('d006','Quality Management'),
('d007','Sales'),
('d008','Research'),
('d009','Customer Service'),
('d010','IT');

INSERT INTO `dept_emp` VALUES 
(10001,'d006','2021-06-26','9999-01-01'),
(10002,'d006','2018-11-21','9999-01-01'),
(10003,'d004','2019-08-28','9999-01-01'),
(10004,'d004','2022-12-01','9999-01-01'),
(10005,'d004','2019-09-12','9999-01-01'),
(10006,'d005','2018-06-02','9999-01-01'),
(10007,'d008','2018-02-10','9999-01-01'),
(10008,'d006','2021-09-15','2022-07-31'),
(10009,'d006','2019-02-18','9999-01-01'),
(10010,'d004','2018-08-24','2020-10-11'),
(10010,'d006','2020-10-12','9999-01-01'),
(10011,'d005','2018-01-22','2019-09-10'),
(10012,'d005','2019-06-26','9999-01-01');

update empleado

-- 1. ¿Cuántos empleados trabajan en el departamento de gestión de calidad?
select departamento

-- 2. ¿Quién es el empleado mejor pagado del departamento de desarrollo? Aporta su
-- nombre, apellido y salario.

-- 3. ¿Cuál es el nombre y apellido del último empleado contratado en el departamento de
-- producción? ¿En qué fecha fue contratado?

-- 4. Calcular el salario medio de los trabajadores del departamento de producción.

-- 5. Mostrar el nombre, apellido y fecha de contratación del empleado que menos gana en
-- el departamento de producción. También muestra su salario.

-- 6. Muestra el nombre, apellidos, sexo y salario del empleado peor pagado de cada
-- departamento.

-- 7. Calcula el salario medio por sexo.

-- 8. Calcula el coste salarial total de todos los empleados del departamento de desarrollo.

-- 9. Calcula el coste salarial total de todos los empleados del departamento de producción
-- que trabajan actualmente en él.

-- 10. Identifica el departamento con el mayor número de empleados actualmente asignados
-- y muestra el nombre del departamento junto con la cantidad de empleados.

