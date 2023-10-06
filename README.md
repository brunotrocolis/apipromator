# APT Promator

## Indice

- [Descrição](#descrição)
- [Banco de dados](#banco-de-dados)
    - [Servidor](#servidor)
    - [Tabelas](#tabelas)
- [Métodos](#serviços)

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

<p>Tabel de cadastro de produtos.</p>

| ## | Coluna             | Tipo                                | Descrição               |
|----|--------------------|-------------------------------------|-------------------------|
| 01 | id                 | binary(16)                          | UUID do Produto         |
| 02 | creation_date_time | datetime(6)                         | Data e hora do cadastro |
| 03 | description        | varchar(500)                        | Descrição               |
| 04 | image              | vbinary(255)                        | Imagem                  |
| 05 | name               | varchar(100)                        | Nome                    |
| 06 | price              | decimal(38,2)                       | Preço                   |
| 07 | status             | enum('ACTIVE','DELETED','INACTIVE') | Situação                |		 

## Métodos

### Produtos

#### Cadastrar produto

<p>Este método é responsável pelo cadastro de novos produtos na basa de dados.</p>

```POST: {servidor}/api/v1/produto```

##### Request: RegisterProductRequest

| Campo       | Tipo       | Obrigatório | Descrição            |
|-------------|------------|-------------|----------------------|
| name        | String     | Sim         | Nome do produto      |
| description | String     | Sim         | Descrição do produto |
| price       | BigDecimal | Sim         | Preço do Produto     |
| imagem      | Imagem     | Não         | Imagem do produto    |

- Exemplo:

```json
{
  "name": "Bugiganga",
  "description": "Produto Teste",
  "price": 1.99
}
```

##### Response: RegisterProductResponse

| Campo              | Tipo          | Obrigatório | Descrição            |
|--------------------|---------------|-------------|----------------------|
| id                 | String        | Sim         | Nome do produto      |
| name               | String        | Sim         | Nome do produto      |
| description        | String        | Sim         | Descrição do produto |
| price              | BigDecimal    | Sim         | Preço do Produto     |
| imagem             | Imagem        | Não         | Imagem do produto    |
| creation_date_time | LocalDateTime | Sim         | Nome do produto      |

- Exemplo:

```json
{
    "id": "99103fd8-c3a4-43aa-ad2d-8bf7f9f36773",
    "name": "Bugiganga",
    "description": "Outro produto Teste",
    "price": 1.99,
    "status": "INACTIVE",
    "image": null,
    "creationDateTime": "2023-10-06 15:37:48"
}
```