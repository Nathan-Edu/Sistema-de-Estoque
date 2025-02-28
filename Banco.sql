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
(1, "Stockadm", "admin@stockmaster.com", "Admin@123"),
(2, 'João Silva', 'joao.silva@email.com', 'senha123'),
(3, 'Maria Oliveira', 'maria.oliveira@email.com', 'senha456'),
(4, 'Carlos Santos', 'carlos.santos@email.com', 'senha789');

INSERT INTO Materiais (id_material, descricao_curta, descricao_longa, quantidade, unidade_medida, deposito) VALUES
(1, 'Parafuso', 'Parafuso de aço inoxidável 10mm', 100, 'unidade', 'Depósito A'),
(2, 'Cimento', 'Saco de cimento 50kg', 50, 'saco', 'Depósito B'),
(3, 'Tinta Azul', 'Lata de tinta azul 3,6L', 20, 'lata', 'Depósito C'),
(4, 'Madeira Compensada', 'Chapa de madeira 1,2m x 2,4m', 30, 'chapa', 'Depósito A'),
(5, 'Canos de PVC', 'Cano de PVC 50mm - 6 metros', 75, 'barra', 'Depósito B'),
(6, 'Fios Elétricos', 'Rolo de fio elétrico 2,5mm - 100 metros', 40, 'rolo', 'Depósito C'),
(7, 'Pregos', 'Caixa com 500 pregos 2 polegadas', 200, 'caixa', 'Depósito A'),
(8, 'Cimento Queimado', 'Saco de 25kg para acabamento', 25, 'saco', 'Depósito B'),
(9, 'Argamassa', 'Saco de argamassa ACIII 20kg', 60, 'saco', 'Depósito C'),
(10, 'Vidro Temperado', 'Placa de vidro 8mm 1m x 2m', 15, 'placa', 'Depósito A'),
(11, 'Luminária LED', 'Luminária LED 40W para ambientes internos', 35, 'unidade', 'Depósito B'),
(12, 'Porta de Madeira', 'Porta de madeira 80cm x 210cm', 12, 'unidade', 'Depósito C'),
(13, 'Telha de Zinco', 'Telha ondulada de zinco 2m', 50, 'unidade', 'Depósito A'),
(14, 'Chapa de Aço', 'Chapa de aço galvanizado 1m x 2m', 20, 'chapa', 'Depósito B'),
(15, 'Bloco de Concreto', 'Bloco estrutural de concreto 14x19x39cm', 500, 'unidade', 'Depósito C');


INSERT INTO Estoque (id_material, quantidade, unidade_medida, data_entrada) VALUES
(1, 100, 'unidade', '2025-02-01'),
(2, 50, 'saco', '2025-02-05'),
(3, 20, 'lata', '2025-02-10');

INSERT INTO Lotes (id_material, quantidade, tipo_acao, data) VALUES
(1, 50, 'Entrada', '2025-02-01'),
(2, 20, 'Entrada', '2025-02-05'),
(3, 10, 'Entrada', '2025-02-10'),
(1, 10, 'Saída', '2025-02-15'),
(2, 5, 'Saída', '2025-02-18');

INSERT INTO Requisicoes (id_usuario, solicitante, id_material, quantidade, data) VALUES
(1, 'João Silva', 1, 10, '2025-02-15'),
(2, 'Maria Oliveira', 2, 5, '2025-02-18'),
(3, 'Carlos Santos', 3, 2, '2025-02-20');

INSERT INTO Logs (id_usuario, acao) VALUES
(1, 'Criou um novo material'),
(2, 'Fez uma requisição de material'),
(3, 'Atualizou o estoque');

SELECT * FROM Usuarios;
SELECT * FROM Materiais;
SELECT * FROM Estoque;
SELECT * FROM Lotes;
SELECT * FROM Requisicoes;




