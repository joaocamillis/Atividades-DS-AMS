create database distribuidora
use distribuidora
CREATE TABLE vendedores (id INT PRIMARY KEY, nome VARCHAR(50), salario_fixo DECIMAL(10,2));
CREATE TABLE clientes (id INT PRIMARY KEY, nome VARCHAR(50), cidade VARCHAR(30), uf CHAR(2));
CREATE TABLE produtos (id INT PRIMARY KEY, descricao VARCHAR(50), unidade VARCHAR(5), valor_unitario DECIMAL(10,2));
CREATE TABLE pedidos (id INT PRIMARY KEY, id_cliente INT, id_vendedor INT, data_pedido DATE, FOREIGN KEY (id_cliente) REFERENCES clientes(id), FOREIGN KEY (id_vendedor) REFERENCES vendedores(id));
CREATE TABLE itens_pedidos (id_pedido INT, id_produto INT, quantidade INT, PRIMARY KEY (id_pedido, id_produto), FOREIGN KEY (id_pedido) REFERENCES pedidos(id), FOREIGN KEY (id_produto) REFERENCES produtos(id));

-- Inserção de Dados Estratégicos
INSERT INTO vendedores (id, nome, salario_fixo) VALUES 
(1, 'Carlos', 2500), (2, 'Felipe', 4000), (3, 'Maurício', 3000), (4, 'Ana', 3500),
(5, 'Ricardo', 3200), (6, 'Beatriz', 4100), (7, 'Marcos', 2800), (8, 'Juliana', 3500),
(9, 'Roberto', 2200), (10, 'Fernanda', 5000), (11, 'Paula', 3100), (12, 'Lucas', 2750);

INSERT INTO clientes (id, nome, cidade, uf) VALUES 
(10, 'Rodolfo', 'Rio', 'RJ'), (20, 'Beth', 'São Paulo', 'SP'), 
(30, 'Lívio', 'São Paulo', 'SP'), (40, 'Susana', 'Rio', 'RJ'),
(50, 'Anderson', 'Curitiba', 'PR'), (60, 'Tatiana', 'Fortaleza', 'CE'),
(70, 'Gustavo', 'Belo Horizonte', 'MG'), (80, 'Carla', 'São Paulo', 'SP'),
(90, 'Henrique', 'Porto Alegre', 'RS'), (100, 'Marta', 'Salvador', 'BA'),
(110, 'Otávio', 'Manaus', 'AM'), (120, 'Renata', 'Rio', 'RJ'),
(130, 'Hugo', 'Vitória', 'ES'), (140, 'Patrícia', 'Campinas', 'SP'),
(150, 'Sérgio', 'Curitiba', 'PR');

INSERT INTO produtos (id, descricao, unidade, valor_unitario) VALUES 
(101, 'Queijo', 'Kg', 10.00), (102, 'Chocolate', 'BAR', 5.00), 
(103, 'Vinho', 'L', 20.00), (104, 'Linha', 'M', 2.00),
(105, 'Azeite', 'L', 25.50), (106, 'Arroz', 'Kg', 5.40), 
(107, 'Feijão', 'Kg', 7.20), (108, 'Macarrão', 'PCT', 3.15),
(109, 'Molho', 'SACH', 2.80), (110, 'Leite', 'L', 4.90),
(111, 'Sabão', 'Kg', 12.00), (112, 'Detergente', 'UN', 1.99);

INSERT INTO pedidos (id, id_cliente, id_vendedor, data_pedido) VALUES 
(501, 10, 1, '2026-05-01'), (502, 20, 1, '2026-05-02'), 
(503, 30, 2, '2026-05-03'), (504, 40, 4, '2026-05-04'),
(505, 50, 5, '2026-05-05'), (506, 60, 6, '2026-05-06'),
(507, 70, 7, '2026-05-07'), (508, 80, 8, '2026-05-08'),
(509, 10, 5, '2026-05-09'), (510, 20, 10, '2026-05-10'),
(511, 100, 11, '2026-05-11'), (512, 120, 1, '2026-05-12'),
(513, 140, 5, '2026-05-13'), (514, 50, 6, '2026-05-14');

INSERT INTO itens_pedidos (id_pedido, id_produto, quantidade) VALUES 
(501, 101, 2), (501, 102, 5), (502, 103, 1), (503, 101, 10), (504, 102, 20),
(505, 105, 2), (505, 106, 5), (506, 110, 10), (507, 108, 3), (508, 109, 15),
(509, 101, 1), (509, 105, 2), (510, 103, 5), (511, 106, 20), (512, 107, 10),
(513, 102, 15), (514, 108, 10), (501, 103, 1), (502, 101, 2), (505, 107, 4),
(510, 102, 2), (511, 105, 1), (512, 110, 2), (513, 101, 1), (514, 105, 1);



SELECT 
    SUM(i.quantidade * pr.valor_unitario) AS valor_total_vendido
FROM itens_pedidos i
INNER JOIN produtos pr ON i.id_produto = pr.id;


SELECT 
    AVG(valor_unitario) AS media_preco_unidade_L
FROM produtos
WHERE unidade = 'L';


SELECT 
    uf,
    COUNT(*) AS total_clientes
FROM clientes
GROUP BY uf;


SELECT 
    v.nome AS vendedor,
    SUM(i.quantidade * pr.valor_unitario) AS faturamento_total
FROM vendedores v
INNER JOIN pedidos p ON v.id = p.id_vendedor
INNER JOIN itens_pedidos i ON p.id = i.id_pedido
INNER JOIN produtos pr ON i.id_produto = pr.id
GROUP BY v.id, v.nome
ORDER BY faturamento_total DESC;


SELECT 
    v.nome AS vendedor,
    SUM(i.quantidade * pr.valor_unitario) AS faturamento_total
FROM vendedores v
INNER JOIN pedidos p ON v.id = p.id_vendedor
INNER JOIN itens_pedidos i ON p.id = i.id_pedido
INNER JOIN produtos pr ON i.id_produto = pr.id
GROUP BY v.id, v.nome
HAVING faturamento_total > 100.00
ORDER BY faturamento_total DESC;


