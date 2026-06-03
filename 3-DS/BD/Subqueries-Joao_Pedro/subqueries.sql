CREATE DATABASE IF NOT EXISTS arenastream_auditoria;
USE arenastream_auditoria;

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS patrocinadores;
DROP TABLE IF EXISTS equipes;
DROP TABLE IF EXISTS pro_players;
SET FOREIGN_KEY_CHECKS = 1;


-- Tabela A: Equipes de E-sports cadastradas na liga
CREATE TABLE equipes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome_equipe VARCHAR(50) NOT NULL,
    divisao VARCHAR(20) NOT NULL
);

-- Tabela B: Pro-players e seus respectivos salários e vínculos
CREATE TABLE pro_players (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_equipe INT,
    nickname VARCHAR(50) NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    posicao VARCHAR(30) NOT NULL,
    FOREIGN KEY (id_equipe) REFERENCES equipes(id)
);

-- Tabela C: Patrocinadores e o investimento injetado diretamente nas equipes
CREATE TABLE patrocinadores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_equipe INT,
    nome_empresa VARCHAR(50) NOT NULL,
    valor_patrocinio DECIMAL(12,2) NOT NULL,
    FOREIGN KEY (id_equipe) REFERENCES equipes(id)
);

-- Carga de Dados Estratégica
INSERT INTO equipes (id, nome_equipe, divisao) VALUES 
(1, 'Loud Esports', 'Elite'),
(2, 'Pain Gaming', 'Elite'),
(3, 'Furia', 'Elite'),
(4, 'Fluxo', 'Acesso'),
(5, 'Los Grandes', 'Acesso'),
(6, 'EquipeSemPlayer', 'Acesso'); -- Caso de teste: Sem jogadores contratados

INSERT INTO pro_players (id, id_equipe, nickname, salario, posicao) VALUES 
(10, 1, 'Less', 18000.00, 'Controlador'),
(20, 1, 'Saadhak', 22000.00, 'Iniciador'),
(30, 2, 'w7m', 15000.00, 'Duelista'),
(40, 2, 'dyan', 14500.00, 'Suporte'),
(50, 3, 'Fallen', 35000.00, 'Capitão'),
(60, 4, 'Nobru', 40000.00, 'Iniciador'),
(70, 5, 'PlayerX', 8000.00, 'Duelista'),
(80, NULL, 'PlayerSolitario', 5000.00, 'Reserva'); -- Caso de teste: Sem equipe

INSERT INTO patrocinadores (id, id_equipe, nome_empresa, valor_patrocinio) VALUES 
(100, 1, 'Banco Itaú', 500000.00),
(201, 1, 'Logitech', 150000.00),
(302, 2, 'BMW Brasil', 600000.00),
(403, 3, 'Betano', 450000.00),
(504, 4, 'Razer', 200000.00); -- Nota: Los Grandes (ID 5) e EquipeSemPlayer (ID 6) não possuem patrocinadores



SELECT nickname, salario
FROM pro_players
WHERE salario > (
    SELECT AVG(salario)
    FROM pro_players
);


SELECT P.nickname, P.salario, P.id_equipe
FROM pro_players P
WHERE P.id_equipe IS NOT NULL
  AND P.salario > (
      SELECT AVG(P2.salario)
      FROM pro_players P2
      WHERE P2.id_equipe = P.id_equipe
  );
  
  
  
SELECT E.nome_equipe
FROM equipes E
WHERE EXISTS (
    SELECT 1
    FROM patrocinadores P
    WHERE P.id_equipe = E.id
);

SELECT E.nome_equipe
FROM equipes E
WHERE NOT EXISTS (
    SELECT 1
    FROM pro_players P
    WHERE P.id_equipe = E.id
);