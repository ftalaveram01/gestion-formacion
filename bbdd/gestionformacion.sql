DROP DATABASE IF EXISTS gestionformacion;
CREATE DATABASE gestionformacion CHARACTER SET utf8mb4;
USE gestionformacion;

CREATE TABLE usuario (
	id INT AUTO_INCREMENT PRIMARY KEY,
	email VARCHAR(100) UNIQUE NOT NULL,
	password VARCHAR(20) NOT NULL
);

CREATE TABLE curso (

	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	nombre VARCHAR(100) NOT NULL,
	descripcion VARCHAR(500) ,
	fecha_inicio DATETIME NOT NULL,
	fecha_fin DATETIME NOT NULL

);

CREATE TABLE usuario_curso (

	id_curso BIGINT NOT NULL,
	id_usuario INT NOT NULL,
    PRIMARY KEY (id_curso, id_usuario),
    FOREIGN KEY (id_curso) REFERENCES curso(id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
    
);