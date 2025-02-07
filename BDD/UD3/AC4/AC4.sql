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
(10001,34000,'d006','2021-06-26','9999-01-01'),
(10002,20000,'d006','2018-11-21','9999-01-01'),
(10003,22000,'d004','2019-08-28','9999-01-01'),
(10004,29500,'d004','2022-12-01','9999-01-01'),
(10005,32000,'d004','2019-09-12','9999-01-01'),
(10006,19500,'d005','2018-06-02','9999-01-01'),
(10007,30000,'d008','2018-02-10','9999-01-01'),
(10008,29500,'d006','2021-09-15','2022-07-31'),
(10009,31250,'d006','2019-02-18','9999-01-01'),
(10010,33000,'d004','2018-08-24','2020-10-11'),
(10010,36000,'d006','2020-10-12','9999-01-01'),
(10011,26000,'d005','2018-01-22','2019-09-10'),
(10012,27000,'d005','2019-06-26','9999-01-01');

-- 1. ¿Cuántos empleados trabajan en el departamento de gestión de calidad?

select count(*) as num_empleados
from dept_emp
where cod_departamento = 'd006' and fecha_hasta = '9999-01-01';

-- 2. ¿Quién es el empleado mejor pagado del departamento de desarrollo? Aporta su
-- nombre, apellido y salario.

select e.nombre, e.apellido, de.salario
from dept_emp de
join empleado e on de.cod_empleado = e.cod_empleado
where de.cod_departamento = 'd005'
order by de.salario desc
limit 1;


-- 3. ¿Cuál es el nombre y apellido del último empleado contratado en el departamento de
-- producción? ¿En qué fecha fue contratado?

select e.nombre, e.apellido, de.fecha_desde
from dept_emp de
join empleado e on de.cod_empleado = e.cod_empleado
where de.cod_departamento = 'd004'
order by de.fecha_desde desc
limit 1;

-- 4. Calcular el salario medio de los trabajadores del departamento de producción.

select avg(de.salario) as salario_medio
from dept_emp de
where de.cod_departamento = 'd004' and de.fecha_hasta = '9999-01-01'; 


-- 5. Mostrar el nombre, apellido y fecha de contratación del empleado que menos gana en
-- el departamento de producción. También muestra su salario.

select e.nombre, e.apellido, e.fecha_contratacion
from dept_emp de
join empleado e on de.cod_empleado = e.cod_empleado
where de.cod_departamento = 'd004'
order by de.salario asc
limit 1;

-- 6. Muestra el nombre, apellidos, sexo y salario del empleado peor pagado de cada
-- departamento.

select e.nombre, e.apellido, e.sexo, de.salario, de.cod_departamento
from dept_emp de
join empleado e on de.cod_empleado = e.cod_empleado
where (de.cod_departamento, de.salario) in (
    select cod_departamento, MIN(salario)
    from dept_emp
    group by cod_departamento
)
order by de.cod_departamento;

-- 7. Calcula el salario medio por sexo.

select e.sexo,avg(de.salario) as salario_medio
from dept_emp de
join empleado e on de.cod_empleado = e.cod_empleado
group by e.sexo;

-- 8. Calcula el coste salarial total de todos los empleados del departamento de desarrollo.

select SUM(de.salario) as coste_total
from dept_emp de
where de.cod_departamento = 'd005' and de.fecha_hasta = '9999-01-01';


-- 9. Calcula el coste salarial total de todos los empleados del departamento de producción
-- que trabajan actualmente en él.

select  SUM(de.salario) as coste_total
from dept_emp de
where de.cod_departamento = 'd004' and de.fecha_hasta = '9999-01-01';


-- 10. Identifica el departamento con el mayor número de empleados actualmente asignados
-- y muestra el nombre del departamento junto con la cantidad de empleados.

select d.nombre_departamento, COUNT(*) AS num_empleados
from dept_emp de
join departamento d on de.cod_departamento = d.cod_departamento
where de.fecha_hasta = '9999-01-01'
group by de.cod_departamento
order by num_empleados desc
limit 1;
