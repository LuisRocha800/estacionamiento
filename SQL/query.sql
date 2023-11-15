jdbc:derby://localhost:1527/estacionamientoBDD;create=false

CREATE TABLE nfc_movements_test (
id_movement INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
id_tag VARCHAR(30),
date_mov DATE,
time_mov TIME,
status VARCHAR(30),
tipe_movement VARCHAR(30)
);

CREATE TABLE personal (
id_tag VARCHAR(30) PRIMARY KEY,
nombre VARCHAR(30),
apellido VARCHAR(30)
);

CREATE TABLE pensionados(
id_tag VARCHAR(30) PRIMARY KEY,
nombre VARCHAR(30),
apellido VARCHAR(30),
vigencia_tarjeta DATE,
cajon VARCHAR(30)
);

CREATE TABLE tarjetas (
id_tag VARCHAR (50) PRIMARY KEY,
estatus VARCHAR(20)
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