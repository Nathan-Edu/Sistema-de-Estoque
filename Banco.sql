CREATE DATABASE StockMaster;
USE StockMaster;

CREATE TABLE Usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE Materiais (
    id_material INT AUTO_INCREMENT PRIMARY KEY,
    descricao_curta VARCHAR(255) NOT NULL,
    descricao_longa TEXT,
    quantidade INT NOT NULL DEFAULT 0,
    unidade_medida VARCHAR(50) NOT NULL,
    deposito VARCHAR(100) NOT NULL
);

CREATE TABLE Estoque (
    id_estoque INT AUTO_INCREMENT PRIMARY KEY,
    id_material INT NOT NULL,
    quantidade DECIMAL(10, 2) NOT NULL DEFAULT 0,
    unidade_medida VARCHAR(50) NOT NULL,
    data_entrada DATE NOT NULL,
    FOREIGN KEY (id_material) REFERENCES Materiais(id_material) ON DELETE CASCADE
);

CREATE TABLE Lotes (
    id_lote INT AUTO_INCREMENT PRIMARY KEY,
    id_material INT NOT NULL,
    quantidade DECIMAL(10, 2) NOT NULL CHECK (quantidade >= 0),
    tipo_acao ENUM('Entrada', 'Saída') NOT NULL,
    data DATE NOT NULL,
    FOREIGN KEY (id_material) REFERENCES Materiais(id_material) ON DELETE CASCADE
);

CREATE TABLE Requisicoes (
    id_requisicao INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    solicitante VARCHAR(255) NOT NULL,
    id_material INT NOT NULL,
    quantidade DECIMAL(10, 2) NOT NULL CHECK (quantidade > 0),
    data DATE NOT NULL,
    FOREIGN KEY (id_material) REFERENCES Materiais(id_material) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
);

CREATE TABLE Logs (
    id_log INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    acao VARCHAR(255) NOT NULL,
    data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
);


INSERT INTO Usuarios (id_usuario, nome, email, senha) VALUES
(1, "Stockadm", "admin@stockmaster.com", "Admin@123");

SELECT * FROM Usuarios;
SELECT * FROM Materiais;
SELECT * FROM Estoque;
SELECT * FROM Lotes;
SELECT * FROM Requisicoes;

