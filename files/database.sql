CREATE DATABASE biblioteca;

USE biblioteca;

CREATE TABLE Llibres (
    ID_Llibre INT AUTO_INCREMENT PRIMARY KEY,
    Títol VARCHAR(255),
    Autor VARCHAR(255),
    ISBN VARCHAR(20),
    Editorial VARCHAR(255),
    Any_Publicació INT,
    Categoria VARCHAR(100),
    Estat VARCHAR(20)
);

CREATE TABLE Usuaris (
    ID_Usuari INT AUTO_INCREMENT PRIMARY KEY,
    Nom VARCHAR(255),
    Cognoms VARCHAR(255),
    Email VARCHAR(255),
    Telèfon VARCHAR(20),
    Rol VARCHAR(50),
    Data_Registre DATE
);

CREATE TABLE Préstecs (
    ID_Préstec INT AUTO_INCREMENT PRIMARY KEY,
    ID_Llibre INT,
    ID_Usuari INT,
    Data_Préstec DATE,
    Data_Retorn_Prevista DATE,
    Data_Retorn_Real DATE,
    Estat VARCHAR(20),
    FOREIGN KEY (ID_Llibre) REFERENCES Llibres(ID_Llibre),
    FOREIGN KEY (ID_Usuari) REFERENCES Usuaris(ID_Usuari)
);
