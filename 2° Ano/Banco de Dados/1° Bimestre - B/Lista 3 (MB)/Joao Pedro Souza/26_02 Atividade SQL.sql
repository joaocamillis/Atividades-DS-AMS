create database ExercBD01 --criando banco


use ExercBD01

CREATE TABLE pet
(
NumRegistro int, --criando campo registro
Nome varchar(80), --criando campo nome
Especie varchar(25), --criando campo especie
Raca varchar(30), --criando campo raca 
Cor varchar(40), --criando campo cor
Nascimento datetime, --criando campo Nascimento
Sexo varchar, --criando campo sexo
);




create database ExercBD02 --criando banco

use ExercBD02

CREATE TABLE Departamento(
Codfunc int, --criando	campo Codfunc
NomeDepto varchar(50) --criando campo NomeDepto
);






CREATE TABLE Funcionario --Criando tabela funcionario
(
Codfunc int, --criando	campo Codfunc
NomeFunc varchar(50), --criando campo Nomefunc
CodDepto varchar(50), --criando campo codDepto
Ramal int NULL , --criando campo ramal
Salario float,
DataAdmissao datetime, --criando campo dataadmissao
DataCadastro datetime, --criando campo dataCadastro
Sexo char(1)
);



create database  ExercBD03 --banco de dados que vai conter as quatro tabelas do exercicio 3

use  ExercBD03

CREATE TABLE produtos -- vai ter as informações dos produtos de uma papelaria 
(
codigo_produto int primary key NOT NULL, --criando campo obrigatorio codigo_produto 
nome_produto varchar(100) NOT NULL, --criando campo obrigatorio nome_produto
desc_produto text, --criando campo desc_produto
categ_produto int NOT NULL, --criando campo obrigatorio categ_produto
prec_produto float NOT NULL check (prec_produto > 0), --criando campo obrigatorio prec_produto
qnt_estoque int NOT NULL check (qnt_estoque >=0), --criando campo obrigatorio qnt_estoque
);



create table Cadastrofuncionarios --guardará informações dos funcionarios de uma escola
(
IDFuncionario int primary key identity, --Identificador único do funcionário
NomeFuncionario varchar (100) NOT NULL, --Nome completo do funcionário
CPF varchar(11) UNIQUE NOT NULL, --Cadastro de Pessoa Física (único)
Cargo varchar(50) NOT NULL, --Cargo ocupado pelo funcionário
Departamento_ID int NOT NULL, --identificador que referencia o departamento
Salario float NOT NULL, --Valor do salário do funcionário
Data_admissao date NOT NULL, --Data de contratação do funcionário
Telefone varchar(15) NOT NULL, -- Número de telefone para contato
Email Varchar (100) NOT NULL --Endereço de e-mail do funcionário
);


create table Fornecedores--Armazenará as informações dos Fornecedores de uma empresa de tecido
(
ID_Fornecedor int primary key identity,--Identificador único do fornecedor
Nome_Empresa varchar(100) NOT NULL,--Nome da empresa fornecedora
CNPJ varchar(14) unique NOT NULL, --Cadastro Nacional da Pessoa Jurídica
Endereco varchar(255), --Endereço da empresa fornecedora
Telefone varchar(15) NOT NULL, --Número de contato da empresa
Email varchar(100), --E-mail de contato
Categoria varchar(50) NOT NULL, --Tipo de material fornecido
); 






create table Departamentos --Cadastro de departamentos de uma empresa
(
ID_Departamento int primary key identity, --Identificador único do departamento
Nome_Departamento varchar(100) NOT NULL, --Nome do departamento
Responsavel_ID int NOT NULL, --Identificador único do Resposavel pelo Departamento
Descricao varchar(100), --Breve descrição sobre o departamento
);


create table mercado --tabela que tera as informações do mercado
(
nome_mercado varchar(20) NOT NULL, --nome do mercado
CNPJ varchar(14) unique NOT NULL, --CNPJ do mercado
endereco varchar(255), -- Endereço do mercado
Telefone varchar (15), --Telefone do mercado
produtos varchar(500) --produtos do mercado
);