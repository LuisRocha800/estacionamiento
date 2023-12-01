jdbc:derby://localhost:1527/estacionamientoBDD;create=FALSE

CREATE TABLE personal (
id_tag VARCHAR(30) PRIMARY KEY,
nombre VARCHAR(30),
apellido VARCHAR(30)
);

CREATE TABLE pensionados(
id_tag VARCHAR(30) PRIMARY KEY,
nombre VARCHAR(30),
apellido VARCHAR(30),
vigencia_tarjeta DATE
);

CREATE TABLE tarjetas (
id_tag VARCHAR (30) PRIMARY KEY,
estatus VARCHAR(20)
);

CREATE TABLE nfc_movements_test (
id_movement INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
id_tag_personal VARCHAR(30),
id_tag_pensionado VARCHAR(30),
id_tag_invitado VARCHAR(30),
date_mov DATE,
time_mov TIME,
status VARCHAR(30),
tipe_movement VARCHAR(30),
FOREIGN KEY (id_tag_personal) REFERENCES personal(id_tag),
FOREIGN KEY (id_tag_pensionado) REFERENCES pensionados(id_tag),
FOREIGN KEY (id_tag_invitado) REFERENCES tarjetas(id_tag)
);

CREATE TABLE pagos (
id_payment INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
id_card VARCHAR(200),
date_payment DATE,
time_payment TIME,
time_service INTEGER,
amount DECIMAL(10,2),
status VARCHAR(30)
);

INSERT INTO PERSONAL VALUES('None','None','None');
INSERT INTO PENSIONADOS(id_tag, nombre, apellido)  VALUES('None','None','None');
INSERT INTO TARJETAS  VALUES('None','None');

CREATE TABLE adminusers (
usuario VARCHAR(100) PRIMARY KEY,
password VARCHAR(100)
);