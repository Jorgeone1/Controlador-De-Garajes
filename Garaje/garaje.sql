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
    numero_usuario integer,
    FOREIGN KEY (Matricula) REFERENCES Coches(Matricula),
    FOREIGN KEY (numero_usuario) REFERENCES usuario(numero_usuario)
    
);
CREATE TABLE TiempoEstancia (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    numero_usuario integer,
    dni varchar(10),
    numero_tarjeta varchar(30),
    importe float,
    pagado float,
    FOREIGN KEY (numero_usuario) REFERENCES usuario(numero_usuario)
);
Insert into coches(Matricula, marca, modelo,anyo) values("9823POO",'Seat','Ibiza',2007);
Insert into coches(Matricula, marca, modelo,anyo) values('9123EST','Toyota','Corola',1999);
Insert into coches(Matricula, marca, modelo,anyo) values('1234UIO','kia','Rio',2010);
Insert into coches(Matricula, marca, modelo,anyo) values('5432MNB','Tesla','Modelo C',2018);
Insert into coches(Matricula, marca, modelo,anyo) values('7654JGH','Mercedes','Mercedes-Benz',2014);
Insert into coches(Matricula, marca, modelo,anyo) values('L123POH','Citroen','Sentollo',2014);
Insert into coches(Matricula, marca, modelo,anyo) values('UI765421','Volskvawen','coche aleman',2014);
Insert into coches(Matricula, marca, modelo,anyo) values('H123AS','Mercedes','Mercedes-Benz',2014);


insert into usuario(nombre,apellidos,fecha_nacimiento,Matricula) values('Alex','Perez Sanchez','28-8-1943',"9823POO");
insert into usuario(nombre,apellidos,fecha_nacimiento,Matricula) values('Arvid','Sharma Garcia','28-8-1954','9123EST');
insert into usuario(nombre,apellidos,fecha_nacimiento,Matricula) values('Jonas','Ginsberg','01-1-1969','1234UIO');
insert into usuario(nombre,apellidos,fecha_nacimiento,Matricula) values('Luis','Alberto','30-5-1969','5432MNB');
insert into usuario(nombre,apellidos,fecha_nacimiento,Matricula) values('Jonas','Ginsberg','10-4-1999','L123POH');
insert into usuario(nombre,apellidos,fecha_nacimiento,Matricula) values('Vilcabaro','Triper','23-4-2000','UI765421');
insert into usuario(nombre,apellidos,fecha_nacimiento,Matricula) values('Jonas','Ginsberg','5-8-1980','5432MNB');

INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (1, 'reservada',true);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (2, 'reservada',true);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (3, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (4, 'libre',false);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (5, 'disabled',true);
INSERT INTO Plazas_Garaje (Numero, TipoDePlaza,onuse) VALUES (6, 'disabled',true);
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
UPDATE Plazas_Garaje SET Matricula = "9823POO",numero_usuario=1 where numero = 7;
UPDATE Plazas_Garaje SET Matricula = "9123EST",numero_usuario=2 where numero = 8;
UPDATE Plazas_Garaje SET Matricula = "1234UIO",numero_usuario=3 where numero = 9;
UPDATE Plazas_Garaje SET Matricula = "5432MNB",numero_usuario=4 where numero = 10;
UPDATE Plazas_Garaje SET Matricula = "7654JGH",numero_usuario=5 where numero = 11;

