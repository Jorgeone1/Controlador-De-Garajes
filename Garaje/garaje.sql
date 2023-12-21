create database Garaje;
use Garaje;
Create table coches(
Matricula varchar(8) primary key not null,
marca char(50),
modelo varchar(50),
anyo integer(5)
);

create table usuario(
numero_usuario integer auto_increment primary key not null,
nombre varchar(50),
apellidos varchar(100),
fecha_nacimiento varchar(20),
Matricula char(8),
FOREIGN KEY (Matricula) REFERENCES coches(Matricula)
);
CREATE TABLE Plazas_Garaje (
    Numero INT PRIMARY KEY,
    TipoDePlaza CHAR(10),
    Matricula char(8),
    Onuse bool,
    FOREIGN KEY (Matricula) REFERENCES Coches(Matricula)
);
Insert into coches(Matricula, marca, modelo,anyo) values("9823POO",'Seat','Ibiza',2007);
Insert into coches(Matricula, marca, modelo,anyo) values('9123EST','Toyota','Corola',1999);
Insert into coches(Matricula, marca, modelo,anyo) values('1234UIO','kia','Rio',2010);
Insert into coches(Matricula, marca, modelo,anyo) values('5432MNB','Tesla','Modelo C',2018);
Insert into coches(Matricula, marca, modelo,anyo) values('7654JGH','Mercedes','Mercedes-Benz',2014);


insert into usuario(nombre,apellidos,fecha_nacimiento,Matricula) values('Alex','Perez Sanchez','28-Ago-1943',"9823POO");
insert into usuario(nombre,apellidos,fecha_nacimiento,Matricula) values('Arvid','Sharma Garcia','28-Ago-1954','9123EST');
insert into usuario(nombre,apellidos,fecha_nacimiento,Matricula) values('Jonas','Ginsberg','01-Ene-1969','1234UIO');
insert into usuario(nombre,apellidos,fecha_nacimiento,Matricula) values('Luis','Alberto','30-May-1969','5432MNB');
insert into usuario(nombre,apellidos,fecha_nacimiento,Matricula) values('Jonas','Ginsberg','10-Abr-1969','7654JGH');

INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (1, 'reservada',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (2, 'reservada',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (3, 'reservada',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (4, 'reservada',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (5, 'disabled',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (6, 'disabled',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (7, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (8, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (9, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (10, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (11, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (12, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (13, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (14, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (15, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (16, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (17, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (18, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (19, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (20, 'libre',false);

UPDATE Plazas_Garaje SET onuse = true,tipodeplaza ="ocupada" WHERE Numero IN (7, 8, 9, 10, 11);
-- Asignar un coche a las plazas
UPDATE Plazas_Garaje SET Matricula = "9823POO" where numero = 7;
UPDATE Plazas_Garaje SET Matricula = "9123EST" where numero = 8;
UPDATE Plazas_Garaje SET Matricula = "1234UIO" where numero = 9;
UPDATE Plazas_Garaje SET Matricula = "5432MNB" where numero = 10;
UPDATE Plazas_Garaje SET Matricula = "7654JGH" where numero = 11;

