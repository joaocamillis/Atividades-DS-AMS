-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 15-Abr-2025 às 05:12
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
-- 
--
-- Banco de dados: `bd_escola`
--
CREATE DATABASE IF NOT EXISTS `bd_escola` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `bd_escola`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `alunos`
--

CREATE TABLE `alunos` (
  `matricula` varchar(5) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `endereco` varchar(50) NOT NULL,
  `cidade` varchar(30) NOT NULL,
  `codcurso` char(2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `alunos`
--

INSERT INTO `alunos` (`matricula`, `nome`, `endereco`, `cidade`, `codcurso`) VALUES
('2001', 'João Pereira', 'Rua das Acácias, 150', 'São Paulo', '04'),
('2002', 'Ana Costa', 'Av. Central, 200', 'Rio de Janeiro', '05'),
('2003', 'Felipe Silva', 'Rua do Sol, 45', 'Belo Horizonte', '06'),
('2004', ' Larissa Souza', 'Av. Paulista, 67', 'Salvador', '07'),
('2005', ' Carlos Rocha', 'Rua dos Ipês, 333', ' Fortaleza', '04'),
('2006', 'Júlia Almeida', 'Rua das Flores, 89', 'Curitiba', '05'),
('2007', 'Gabriel Costa', 'Av. dos Bandeirantes, 112', 'Porto Alegre', '06'),
('2008', 'Beatriz Santos', 'Rua do Parque, 501', 'Recife', '07'),
('2009', 'Lucas Lima', 'Av. Sete de Setembro, 890', 'Campinas', '04'),
('2010', 'Mariana Ferreira', 'Rua da Liberdade, 732', 'Vitória', '05'),
('2011', 'André Souza', 'Av. Brasil, 650', 'Maceió', '06'),
('2012', 'Isabela Martins', 'Rua das Palmeiras, 210', 'Florianópolis', '07'),
('2013', 'Daniel Pereira', 'Rua do Rio, 190', 'Natal', '04'),
('2014', 'Ana Clara Souza', 'Rua das Flores, 123', 'Belo Horizonte', '04'),
('2015', 'Bruno Henrique Lima	', 'Av. Brasil, 456	', 'São Paulo	', '05'),
('2016', 'Camila Oliveira', 'Rua da Paz, 789', 'Rio de Janeiro', '06'),
('2017', 'Diego Martins Rocha', 'Rua A, 10', 'Curitiba', '07'),
('2018', 'Elisa Mendes Cardoso	', 'Rua B, 35', 'Recife', '04'),
('2019', 'Felipe Augusto Torres', 'Travessa das Laranjeiras, 12', 'Porto Alegre', '06'),
('2020', 'Gabriela Nunes Silva', 'Rua das Acácias, 88', 'Salvador', '05');

-- --------------------------------------------------------

--
-- Estrutura da tabela `cursos`
--

CREATE TABLE `cursos` (
  `CodCurso` char(2) NOT NULL,
  `Nome` varchar(50) NOT NULL,
  `Coddisc1` char(2) NOT NULL,
  `Coddisc2` char(2) NOT NULL,
  `Coddisc3` char(2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `cursos`
--

INSERT INTO `cursos` (`CodCurso`, `Nome`, `Coddisc1`, `Coddisc2`, `Coddisc3`) VALUES
('04', 'Engenharia de Software', '44', '45', '46'),
('05', 'Medicina', '54', '55', '56'),
('06', 'Arquitetura', '64', '65', '66'),
('07', 'Psicologia', '74', '75', '76');

-- --------------------------------------------------------

--
-- Estrutura da tabela `disciplinas`
--

CREATE TABLE `disciplinas` (
  `CodDisciplina` char(2) NOT NULL,
  `NomeDisciplina` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `disciplinas`
--

INSERT INTO `disciplinas` (`CodDisciplina`, `NomeDisciplina`) VALUES
('01', 'Matemática'),
('02', 'Português'),
('03', 'Ciências'),
('04', 'História'),
('05', 'Geografia'),
('06', 'Educação Física'),
('07', 'Artes'),
('08', 'Inglês'),
('09', 'Filosofia'),
('10', 'Química');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `alunos`
--
ALTER TABLE `alunos`
  ADD PRIMARY KEY (`matricula`);

--
-- Índices para tabela `cursos`
--
ALTER TABLE `cursos`
  ADD PRIMARY KEY (`CodCurso`);

--
-- Índices para tabela `disciplinas`
--
ALTER TABLE `disciplinas`
  ADD PRIMARY KEY (`CodDisciplina`);
--
-- Banco de dados: `extra`
--
CREATE DATABASE IF NOT EXISTS `extra` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `extra`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `mercado`
--

CREATE TABLE `mercado` (
  `nome_mercado` varchar(20) NOT NULL,
  `CNPJ` varchar(14) NOT NULL,
  `endereco` varchar(50) NOT NULL,
  `Telefone` varchar(20) NOT NULL,
  `produtos` varchar(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Extraindo dados da tabela `mercado`
--

INSERT INTO `mercado` (`nome_mercado`, `CNPJ`, `endereco`, `Telefone`, `produtos`) VALUES
('SuperMais Mercado', '12.345.678/000', 'Rua do Comercio, 100 – SP', '(11) 98888-0001', 'Hortifruti, Laticini'),
('Mercadão Econômico', '23.456.789/000', 'Av. Central, 200 – RJ	', '(21) 97777-0002', 'Açougue, Bebidas'),
('Bom Preço Supermerca', '34.567.890/000', 'Rua das Palmeiras, 345 – MG', '(31) 96666-0003', 'Padaria, Mercearia'),
('Rede União Atacadist', '45.678.901/000', 'Rodovia BR-101, Km 12 – PE', '(81) 95555-0004', 'Limpeza, Higiene'),
('CompraCerta Express', '56.789.012/000', 'Av. das Américas, 987 – BA', '(71) 94444-0005', 'Frios, Congelados'),
('MegaMix Super', '67.890.123/000', 'Rua da Independência, 58 – PR', '(41) 93333-0006', 'Bebidas, Doces'),
('Econômico da Família', '78.901.234/000', 'Travessa dos Girassóis, 22 – CE', '(85) 92222-0007', 'Grãos, Enlatados'),
('TopCompras Mercado', '89.012.345/000', 'Praça Central, 5 – RS', '(51) 91111-0008', 'Higiene, Limpeza, Pa');

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `mercado`
--
ALTER TABLE `mercado`
  ADD UNIQUE KEY `CNPJ` (`CNPJ`);
--