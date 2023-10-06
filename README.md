# APT Promator

## Indice

- [Descrição](#descrição)
- [Banco de dados](#banco-de-dados)
    - [Servidor](#servidor)
    - [Tabelas](#tabelas)

## Descrição

<p> Este é um projeto tem o objetivo de implementar uma API de Vitrinhe virtual, com o objetivo do estudo Spring Boot 3 e teste de tecnicas de desenvolvimento.

## Especificações

| Software     | Versão |
|--------------|--------|
| Apache Maven | 3.9.4  |
| Java         | 17     |

## Banco de dados

### Servidor

| Software | Versão |
|----------|--------|
| MySql    | 8.1.0  |

### Tabelas

#### Produtos

| ## | Coluna             | Tipo                                | Descrição               |
|----|--------------------|-------------------------------------|-------------------------|
| 01 | id                 | binary(16)                          | UUID do Produto         |
| 02 | creation_date_time | datetime(6)                         | Data e hora do cadastro |
| 03 | description        | varchar(500)                        | Descrição               |
| 04 | image              | vbinary(255)                        | Imagem                  |
| 05 | name               | varchar(100)                        | Nome                    |
| 06 | price              | decimal(38,2)                       | Preço                   |
| 07 | status             | enum('ACTIVE','DELETED','INACTIVE') | Situação                |		 

