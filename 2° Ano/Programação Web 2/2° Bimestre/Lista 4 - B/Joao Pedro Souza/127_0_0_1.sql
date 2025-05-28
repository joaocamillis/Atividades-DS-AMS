-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 09-Abr-2025 às 04:18
-- Versão do servidor: 10.4.32-MariaDB
-- versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bd_empresa`
--
CREATE DATABASE IF NOT EXISTS `bd_empresa` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bd_empresa`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `fornecedores`
--

CREATE TABLE `fornecedores` (
  `Cod_Fornecedor` int(11) NOT NULL,
  `Razao_Social` varchar(70) NOT NULL,
  `Nome_Fantasia` varchar(70) NOT NULL,
  `CNPJ` varchar(20) NOT NULL,
  `Endereco` varchar(50) NOT NULL,
  `Num` varchar(7) NOT NULL,
  `Bairro` varchar(25) NOT NULL,
  `Cidade` varchar(25) NOT NULL,
  `Fone` varchar(18) NOT NULL,
  `Nome_Contato` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Site` varchar(50) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `fornecedores`
--

INSERT INTO `fornecedores` (`Cod_Fornecedor`, `Razao_Social`, `Nome_Fantasia`, `CNPJ`, `Endereco`, `Num`, `Bairro`, `Cidade`, `Fone`, `Nome_Contato`, `Email`, `Site`) VALUES
(1001, 'Metalúrgica São Jorge LTDA', ' São Jorge Ferragens', '12.345.678/0001-90', ' Av. das Indústrias', '1234', 'Distrito Industrial', 'São Paulo', '(11) 3333-4455', 'João Silva', ' joao@sjferragens.com.br', 'www.sjferragens.com.br'),
(1002, 'Parafusos Brasil EIRELI', 'PB Parafusos', '98.765.432/0001-10', 'Rua dos Engenheiros', '456', ' Vila Técnica', 'Belo Horizonte', '(31) 9988-7766', 'Ana Costa', ' ana@pbparafusos.com.br', ' www.pbparafusos.com.br'),
(1003, 'Ferramentas União LTDA', 'União Ferramentas', '23.456.789/0001-55', 'Av. Central', '890', 'Centro', 'Curitiba', '(41) 3456-7890', 'Marcos Lima', 'marcos@uniaoferramentas.com', 'www.uniaoferramentas.com'),
(1004, 'Comercial ToolBox ME', 'ToolBox', '11.222.333/0001-44', 'Rua das Ferragens', '100', 'Operário', 'Porto Alegre', '(51) 3344-5566', 'Beatriz Mendes', 'bea@toolbox.com.br', 'www.toolbox.com.br'),
(1005, 'Drill Max Equipamentos LTDA', 'Drill Max', '77.888.999/0001-22', 'Av. dos Trabalhadores', '222', ' Industrial Norte', 'Campinas', '(19) 4002-8922', 'Pedro Azevedo', 'pedro@drillmax.com.br', ' www.drillmax.com.br'),
(1006, ' Lixas & Cia Comércio LTDA', 'Lixas&Cia', ' 66.555.444/0001-33', 'Rua Abrasiva', '321', 'Zona Leste', 'Salvador', '(71) 3232-1234', 'Camila Rocha', 'camila@lixasecia.com.br', 'www.lixasecia.com.br'),
(1007, 'EletroFix Indústria e Comércio', 'EletroFix', '33.444.555/0001-66', 'Rua da Eletricidade', '87', 'Nova Luz', 'Recife', '(81) 9876-5432', 'Rafael Torres', 'rafael@eletrofix.com.br', 'www.eletrofix.com.br'),
(1008, 'Laser Tools do Brasil LTDA', 'Laser Tools', '10.101.010/0001-00', 'Av. Óptica', '55', 'Horizonte Azul', 'Brasília', '(61) 3344-5566', 'Fernanda Prado', 'fernanda@lasertools.com.br', ' www.lasertools.com.br'),
(1009, 'Super Trenas Comércio de Ferragens', ' Super Trenas', '22.333.444/0001-11', 'Estrada do Medidor', '999', 'Medição', 'Goiânia', '(62) 9988-7766', 'Rodrigo Campos', ' rodrigo@supertrenas.com.br', 'www.supertrenas.com.br'),
(1010, 'Fast Cola Soluções Adesivas', 'Fast Cola', '55.666.777/0001-88', ' Av. Adesiva', '12', 'Colagem Rápida', 'Florianópolis', '(48) 3030-4040', 'Larissa Moraes', 'larissa@fastcola.com.br', ' www.fastcola.com.br');

-- --------------------------------------------------------

--
-- Estrutura da tabela `produtos`
--

CREATE TABLE `produtos` (
  `Cod_Produto` int(11) NOT NULL,
  `Descricao` varchar(50) NOT NULL,
  `Unidade` varchar(2) NOT NULL,
  `Qtde_Estoque` double NOT NULL,
  `Caracteristica` varchar(50) NOT NULL,
  `Cod_Fornecedor` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `produtos`
--

INSERT INTO `produtos` (`Cod_Produto`, `Descricao`, `Unidade`, `Qtde_Estoque`, `Caracteristica`, `Cod_Fornecedor`) VALUES
(1, 'Parafuso 10mm', 'UN', 120, 'Aço zincado', 1001),
(2, 'Porca 10mm', 'UN', 85, 'Rosca fina', 1002),
(3, 'Arruela 10mm', 'UN', 95, 'Diâmetro externo 20mm', 1001),
(4, 'Martelo de Borracha', 'UN', 20, 'Cabo de madeira', 1003),
(5, 'Serra Circular', 'UN', 10, ' Disco 7', 1004),
(6, 'Broca 8mm', 'UN', 200, 'Aço rápido (HSS)', 1005),
(7, 'Lixa 120', 'UN', 150, 'Grão médio', 1006),
(8, 'Chave de Fenda', 'UN', 60, 'Cabo emborrachado', 1003),
(9, 'Alicate Universal', 'UN', 35, 'Isolado até 1000V', 1003),
(10, 'Fita Isolante', 'UN', 300, 'Antichama', 1007),
(11, 'Prego 15mm', 'CX', 40, 'Cabeça chata', 1001),
(12, 'Bucha Plástica 8mm', 'UN', 180, 'Nylon reforçado', 1002),
(13, ' Trena 5m', 'UN', 25, 'Trava automática', 1004),
(14, 'Nível a Laser', 'UN', 5, 'Alcance de 10m', 1008),
(15, 'Cola Instantânea', 'UN', 75, 'Secagem em 3 segundos', 1006);

-- --------------------------------------------------------

--
-- Estrutura da tabela `veiculos_frota`
--

CREATE TABLE `veiculos_frota` (
  `Placa` varchar(8) NOT NULL,
  `Modelo` varchar(50) NOT NULL,
  `Ano` year(4) NOT NULL,
  `Cor` varchar(20) NOT NULL,
  `Status` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `veiculos_frota`
--

INSERT INTO `veiculos_frota` (`Placa`, `Modelo`, `Ano`, `Cor`, `Status`) VALUES
('ABC-1234', 'Fiat Strada', '2022', 'Branco', 'Ativo'),
('XYZ-9876', 'Volkswagen Saveiro', '2021', 'Prata', 'Em manutenção'),
('JKL-4567', 'Renault Kangoo', '2020', 'Cinza', 'Inativo'),
('MNO-3322', 'Chevrolet S10', '2019', 'Preto', 'Ativo'),
('PQR-7788', 'Ford Transit', '2023', 'Branco', ' Em manutenção'),
('STU-5566', 'Iveco Daily', '2020', 'Azul', 'Ativo');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `fornecedores`
--
ALTER TABLE `fornecedores`
  ADD PRIMARY KEY (`Cod_Fornecedor`);

--
-- Índices para tabela `produtos`
--
ALTER TABLE `produtos`
  ADD PRIMARY KEY (`Cod_Produto`);

--
-- Índices para tabela `veiculos_frota`
--
ALTER TABLE `veiculos_frota`
  ADD PRIMARY KEY (`Placa`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `fornecedores`
--
ALTER TABLE `fornecedores`
  MODIFY `Cod_Fornecedor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1011;

--
-- AUTO_INCREMENT de tabela `produtos`
--
ALTER TABLE `produtos`
  MODIFY `Cod_Produto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- Banco de dados: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------