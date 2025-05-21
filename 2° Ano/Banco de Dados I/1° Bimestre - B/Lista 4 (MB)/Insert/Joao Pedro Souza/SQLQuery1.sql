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
CodDepto int, --criando campo codDepto
Ramal int NULL , --criando campo ramal
Salario float,
DataAdmissao datetime, --criando campo dataadmissao
DataCadastro datetime, --criando campo dataCadastro
Sexo char(1)
);

use ExercBD01

insert into pet (NumRegistro, Nome, Especie, Raca, Cor, Nascimento, Sexo)
values           (1, 'Max' , 'Cão' , 'Labrador' , 'Amarelo' , '15/08/2019' , 'M');

insert into pet (NumRegistro, Nome, Especie, Raca, Cor, Nascimento, Sexo)
values           (2, 'Luna', 'Gato', 'Siamês', 'Creme com pontos escuros', '10/03/2021', 'F');

insert into pet (NumRegistro, Nome, Especie, Raca, Cor, Nascimento, Sexo)
values           (3, 'Kaiden', 'Gato', 'Vira-lata', 'Branco', '20/07/2020', 'M');

insert into pet (NumRegistro, Nome, Especie, Raca, Cor, Nascimento, Sexo)
values           (4, 'Bella', 'Cão', 'Poodle', 'Branco', '01/07/2018', 'F');


insert into pet (NumRegistro, Nome, Especie, Raca, Cor, Nascimento, Sexo)
values           (5, 'Thor', 'Cão', 'Rottweiler', 'Preto e marrom', '20/05/2020', 'M');

insert into pet (NumRegistro, Nome, Especie, Raca, Cor, Nascimento, Sexo)
values           (6, 'Maya', 'Gato', 'Persa', 'Cinza', '05/11/2017', 'F');

insert into pet (NumRegistro, Nome, Especie, Raca, Cor, Nascimento, Sexo)
values           (7, 'Simba', 'Cão', 'Golden Retriever', 'Dourado', '12/09/2019', 'M');

insert into pet (NumRegistro, Nome, Especie, Raca, Cor, Nascimento, Sexo)
values           (8, 'Nina', 'Coelho', 'Mini Rex', 'Branco com manchas marrons', '01/02/2022', 'F');

insert into pet(NumRegistro, Nome, Especie, Raca, Cor, Nascimento, Sexo)

VALUES(9, 'Princesa', 'Cão', 'PitBull', 'Marrom', '04/02/2009', 'F');

insert into pet (NumRegistro, Nome, Especie, Raca, Cor, Nascimento, Sexo)
values (10, 'Zizi', 'Gato', 'Maine Coon', 'Preto e cinza', '30/08/2020', 'F');

select * from pet


use ExercBD02

insert into Departamento(Codfunc, NomeDepto)
values (1, 'Departamento de Inovação e Tecnologia');

insert into Departamento(Codfunc, NomeDepto)
values(2,'Departamento de Atendimento ao Cliente');

insert into Departamento(Codfunc, NomeDepto)
values(3, 'Departamento de Marketing e Comunicação');

insert into Departamento(Codfunc, NomeDepto)
values(4, 'Departamento de Recursos Humanos');

insert into Departamento (Codfunc, NomeDepto)
values(5, 'Departamento de Logística e Suprimentos');


select * from Departamento


insert into Funcionario (Codfunc, NomeFunc, CodDepto, Ramal, Salario, DataAdmissao, DataCadastro, Sexo)
values (1, 'João Silva', 1012, 110, 3500, '15-03-2018', '15-03-2018', 'M');

insert into Funcionario (Codfunc, NomeFunc, CodDepto, Ramal, Salario, DataAdmissao, DataCadastro, Sexo)
values (2,'Maria Oliveira', 102, 5678, 2800, '15/03/2019', '20/03/2020', 'F');

insert into Funcionario (Codfunc, NomeFunc, CodDepto, Ramal, Salario, DataAdmissao, DataCadastro, Sexo)
values (3,'Carlos Souza', 103, 9101, 4500, '10/08/2018', '20/03/2020', 'M');

insert into Funcionario (Codfunc, NomeFunc, CodDepto, Ramal, Salario, DataAdmissao, DataCadastro, Sexo)
values (4,'Ana Costa', 104, 1122, 3000, '01/11/2021', '20/03/2020', 'F');

insert into Funcionario (Codfunc, NomeFunc, CodDepto, Ramal, Salario, DataAdmissao, DataCadastro, Sexo)
values (5,'Roberto Lima', 105, 3344, 5000, '05/06/2020', '20/03/2020', 'M' );

insert into Funcionario (Codfunc, NomeFunc, CodDepto, Ramal, Salario, DataAdmissao, DataCadastro, Sexo)
values (6, 'Fernanda Almeida', 106, 5567, 2900, '15/09/2021', '20/03/2020', 'F');

insert into Funcionario (Codfunc, NomeFunc, CodDepto, Ramal, Salario, DataAdmissao, DataCadastro, Sexo)
values (7, 'Paulo Ferreira', 107, 7789, 6200 , '25/04/2017', '20/03/2020', 'M');

insert into Funcionario (Codfunc, NomeFunc, CodDepto, Ramal, Salario, DataAdmissao, DataCadastro, Sexo)
values (8, 'Luana Martins', 108, 9900, 3600, '12/12/2020', '20/03/2020', 'F');

insert into Funcionario (Codfunc, NomeFunc, CodDepto, Ramal, Salario, DataAdmissao, DataCadastro, Sexo)
values (9, 'Marcos Pereira', 109, 3345, 4800, '07/02/2022', '20/03/2020', 'M');

insert into Funcionario (Codfunc, NomeFunc, CodDepto, Ramal, Salario, DataAdmissao, DataCadastro, Sexo)
values (10, 'Pedro Ramos', 101, 1234, 3200, '01/02/2020', '20/03/2020', 'M');

select * from Funcionario



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
 

insert into produtos(codigo_produto, nome_produto, desc_produto, categ_produto, prec_produto, qnt_estoque)
values (1, 'arroz', 'é feito para consumir', 22, 35, 40);

insert into produtos(codigo_produto, nome_produto, desc_produto, categ_produto, prec_produto, qnt_estoque)
values (2, 'feijão', 'é feito para consumir', 15, 55, 10);

insert into produtos(codigo_produto, nome_produto, desc_produto, categ_produto, prec_produto, qnt_estoque)
values (3, 'ovo de galinha', 'é feito para consumir', 20, 15, 50);


select * from produtos


