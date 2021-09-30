create database ConsultorioG2;

use ConsultorioG2;

create table pacientes(
id varchar(10) not null,
email varchar(45) not null,
nombre varchar(20) not null,
apellido varchar(20) not null,
tipo_documento char(2) not null default 'CC',
fecha_nacimiento date not null,
activo tinyint(1) not null default 1 comment '1 cuando está activo, de lo contrario 0',
direccion varchar(80) not null,
ciudad varchar(25) not null,
tel1 varchar(10) not null,
tel2 varchar(10),
sexo char(1) not null default 'I',
constraint pacientes_pk primary key(id),
unique index email_UNIQUE (email asc) visible,
check(tipo_documento='TI' or tipo_documento='RC' or tipo_documento='CC' or tipo_documento='CE'),
check(activo=1 or activo=0),
check(sexo='F' or sexo='M' or sexo='I')
);

insert into pacientes(id,email,nombre,apellido,tipo_documento,fecha_nacimiento,direccion,ciudad,tel1,tel2,sexo) values
(239031460,'rafael_torp@yahoo.com','Mary','Curtis','CC','1996-11-13','325 Sardis Sta','Fort Worth','8176290458','817680910','F'),
(1221434678,'lillian.gre@gmail.com','Miguel','Lafurcade','TI','2004-02-08','4286 Scott Street','Newburgh','8455698561','8454386039','M'),
(9695658137,'hettie_fran@gmail.com','Jennifer','Decker','CC','1976-12-04','3473 Coburn Hollow Road','Peoria','3094240618',null,'F'),
(7949655110,'jules.johns@yahoo.com','Philip','Raulson','CE','1989-11-21','504 Science Center Drive','Pocatello','3203048990','2085894426','M'),
(4692895216,'lacy.kula4@yahoo.com','Raul','Burley','CC','1977-10-03','2050 Red Hawk Road','Alexandria','3207637283',null,'M'),
(8901622348,'samara_cummera@yahoo.com','Yolanda','Mader','RC','2010-11-16','1686 Friendship Lane','San Jose','4086267938','4083962497','F')
;

create table medicos(
id varchar(10) not null,
nombre varchar(15) not null,
apellido varchar(20) not null,
constraint medicos_pk primary key(id));

insert into medicos values
(4519338757,'Joshua','Till'),
(9528416753,'Levi','Jensen'),
(4935224660,'Alvin','Armstrong');

create table examenes(
id int not null auto_increment,
nombre varchar(20) not null,
constraint examenes_pk primary key(id));

insert into examenes(nombre) values
('Electoencefalograma'),
('Radiografia'),
('Electrocardiograma');

create table medicamentos(
id int not null auto_increment,
nombre varchar(20) not null,
laboratorio varchar(15) not null,
constraint medicamentos_pk primary key(id));

insert into medicamentos(nombre,laboratorio) values
('Paracetamol','AmericanGeneric'),
('Ibuprofeno','Genfar'),
('Cetirizina','Coaspharma'),
('Fenilefrina','Laproff'),
('Diclofenaco','La Sante'),
('Amoxicilina','MK');

create table diagnosticos(
id int not null auto_increment,
nombre text,
constraint diagnostricos_pk primary key(id));

insert into diagnosticos(nombre) values
('Encefalitis'),('Taquicardia'),('HepatitisA'),
('Influenza'),('Meningitis'),('Fractura'),
('Queratitis'),('Laringitis'),('Insomnio'),
('Juanetes'),('Tendinitis');

create table citas(
id int not null auto_increment,
fecha date not null,
hora time not null,
paciente_id varchar(10) not null,
medico_id varchar(10) not null,
constraint citas_pk 
	primary key(id),
constraint citas_pacienteid_fk 
	foreign key(paciente_id) references pacientes(id)
    on update cascade
    on delete restrict,
constraint citas_medicoid_fk 
	foreign key(medico_id) references medicos(id)
    on update cascade
    on delete restrict
);


insert into citas(fecha,hora,paciente_id,medico_id) values
('2020-10-30','09:15:00',239031460,9528416753),
('2021-05-22','11:10:00',1221434678,4519338757),
('2020-11-27','13:15:00',9695658137,9528416753),
('2020-02-15','16:15:00',7949655110,9528416753),
('2021-02-05','15:30:00',4692895216,9528416753),
('2020-07-18','11:45:00',8901622348,4519338757),
('2021-05-15','12:45:00',239031460,9528416753),
('2020-04-05','18:35:00',1221434678,4935224660),
('2021-12-02','13:15:00',9695658137,4519338757),
('2021-09-28','11:00:00',7949655110,4935224660),
('2020-10-07','08:25:00',4692895216,4935224660),
('2021-12-22','16:20:00',8901622348,9528416753);

create table diagnosticos_citas(
cita_id int not null,
diagnostico_id int not null,
constraint diagnosticoscitas_pk
	primary key(cita_id,diagnostico_id),
constraint diagnosticoscitas_citaid_fk
	foreign key(cita_id) references citas(id)
    on update cascade
    on delete restrict,
constraint diagnosticoscitas_diagnosticoid_fk
	foreign key(diagnostico_id) references diagnosticos(id)
    on update cascade
    on delete restrict
);

insert into diagnosticos_citas values
(1,1),(1,2),(2,4),(3,6),(5,9),(5,4),
(6,11),(7,3),(7,2),(8,5),(9,7),(10,8),
(10,2),(11,10),(11,11),(12,4);

create table examenes_citas(
cita_id int not null,
examen_id int not null,
constraint examenescitas_pk
	primary key(cita_id,examen_id),
constraint examenescitas_citaid_fk
	foreign key(cita_id) references citas(id)
    on update cascade
    on delete restrict,
constraint examenescitas_examenid_fk
	foreign key(examen_id) references examenes(id)
    on update cascade
    on delete restrict
);

insert into examenes_citas values
(1,1),(1,2),(1,3),(2,2),(2,3),(3,2),
(6,2),(7,1),(7,2),(7,3),(8,2),(10,2),(10,3);

create table recetas(
id int not null auto_increment,
cita_id int not null,
medicamento_id int not null,
dosis varchar(15) not null,
cantidad varchar(15) not null,
intervalo varchar(200) not null,
constraint recetas_pk
	primary key(id),
constraint recetas_citaid_fk
	foreign key(cita_id) references citas(id)
    on update cascade
    on delete restrict,
constraint recetas_medicamentoid_fk
	foreign key(medicamento_id) references medicamentos(id)
    on update cascade
    on delete restrict
);

insert into recetas(cita_id,medicamento_id,dosis,cantidad,intervalo) values
(1,2,'600 mg','60 tabletas','cada 4 horas por 15 días'),
(1,1,'500 mg','20 cápsulas','1 al día por 15 días'),
(1,3,'10 mg/1 ml','Gotas','1 vez al día por 15 días'),
(2,2,'100 mg','30 tabletas','cada 6 horas por 8 días'),
(4,4,'30 mg','40 tabletas','cada 4 horas por 8 días'),
(5,6,'500 mg','30 tabletas','cada 8 horas por 8 días'),
(5,5,'75 mg','7 ampollas','1 ampolla cada 3 días'),
(5,4,'30 mg','20 tabletas','cada 4 horas por 5 días'),
(9,1,'500 mg','20 cápsulas','1 al día por 15 días'),
(9,5,'100 mg','Gel x 3','luego del baño por 90 días'),
(11,2,'600 mg','30 tabletas','cada 6 horas por 8 días'),
(11,3,'10 mg','20 tabletas','1 al día por 8 días'),
(12,2,'100 mg','60 tabletas','cada 4 horas por 15 días'),
(12,1,'500 mg','suspención','2 veces al día por 8 días');