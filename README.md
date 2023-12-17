# Projeto SpaPetTest

## Participantes
Jessica de Oliveira Alves | Jessica Lima | Luciana Souza Ferreira de Oliveira

## Introdução
Este é um projeto de uma API REST desenvolvido em Spring Boot para gerenciar um SPA Pet, incluindo operações CRUD (Create, Read, Update, Delete) para serviços, cliente, pet e pedidos. O projeto também inclui autenticação básica usando o Spring Security e utiliza o banco de dados H2.

## Requisitos
- Java 11 - 17
- Spring Boot
- H2 Database

## Configuração do Ambiente
Certifique-se de ter o Java 11 ou 17 instalado em seu sistema.

## Configuração do Spring Boot
1. Crie um novo projeto Spring Boot usando o Spring Initializer.
2. Certifique-se de selecionar as seguintes dependências:
   - Spring Web
   - Spring Data JPA
   - H2 Database
   - Spring Security
3. Abra o arquivo `application.properties` e configure as propriedades do banco de dados H2:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
```

## Funcionalidades
Este projeto oferece as seguintes funcionalidades:
- Cadastro, leitura, atualização e exclusão (lógica ou física) de clientes, pets, serviços e pedidos.
- Autenticação básica de usuário usando Spring Security.

## Executando o Projeto
1. Clone o repositório do projeto.
2. Abra o projeto em sua IDE.
3. Execute a aplicação Spring Boot.
4. Acesse a API através dos endpoints definidos para realizar operações CRUD e autenticação.

## Endpoints da API

### Autenticação
- POST /login: Autentica um usuário e gera um token de acesso.

### Cliente
- POST /customer/add: Cria um novo cliente.
- GET /customer/list: Retorna todos os clientes.
- GET /customer/{id}: Retorna um cliente específico pelo ID.
- PUT /customer/{id}: Atualiza um cliente existente pelo ID.
- DELETE /customer/{id}: Remove logicamente ou fisicamente um cliente pelo ID.

### Pedido
- POST /order: Cria um novo pedido.
- GET /order/list: Retorna todos os pedidos.
- GET /order/{id}: Retorna um pedido específico pelo ID.
- PUT /order/{id}: Atualiza um pedido existente pelo ID.
- DELETE /order/{id}: Remove logicamente ou fisicamente um pedido pelo ID.

### Pet
- POST /pet/add: Cria um novo pet.
- GET /pet/list: Retorna todos os pets.
- GET /pet/{id}: Retorna um pet específico pelo ID.
- PUT /pet/{id}: Atualiza um pet existente pelo ID.
- DELETE /pet/{id}: Remove logicamente ou fisicamente um pet pelo ID.

### Tipo de Serviço
- POST /types/add: Cria um novo tipo de serviço.
- GET /types/list: Retorna todos os tipos de serviços.
- GET /types/{id}: Retorna um tipo de serviço específico pelo ID.
- PUT /types/{id}: Atualiza um tipo de serviço existente pelo ID.
- DELETE /types/{id}: Remove logicamente ou fisicamente um tipo de serviço pelo ID.

## Testes

### Testes Unitários
Este projeto inclui testes unitários para garantir o funcionamento adequado dos serviços implementados. As classes de teste cobrem uma variedade de cenários e funcionalidades. Os testes estão estruturados da seguinte forma:
- AuthenticationServiceUnitTest: Testes relacionados à autenticação de usuários.
- CustomerServiceUnitTest: Testes para operações relacionadas aos clientes.
- OrderServiceUnitTest: Testes para operações relacionadas aos pedidos.
- PetServiceUnitTest: Testes para operações relacionadas aos pets.
- TypeServiceUnitTest: Testes para operações relacionadas aos tipos de serviço.

### Testes de Integração
Além dos testes unitários, a aplicação também inclui testes de integração para verificar o comportamento dos endpoints da API. Os testes são organizados da seguinte forma:
- CustomerControllerIntegrationTest: Testes para os endpoints relacionados aos clientes.
- OrderControllerIntegrationTest: Testes para os endpoints relacionados aos pedidos.
- PetControllerIntegrationTest: Testes para os endpoints relacionados aos pets.
- TypeControllerIntegrationTest: Testes para os endpoints relacionados aos tipos de serviço.

### Observações
- Não incluímos testes para validações e autenticações específicas devido às diretrizes fornecidas no enunciado do projeto. O que faz com que esses erros sejam retornados:

Rule violated for package com.ada.SpaPetProjeto.controller.configuration: methods covered ratio is 0.25, but expected minimum is 0.40

Rule violated for package com.ada.SpaPetProjeto.controller.exception: methods covered ratio is 0.00, but expected minimum is 0.40

- Além dos testes unitários e de integração incluídos neste repositório, temos um projeto que implementa 2 cenários de teste end-to-end. Este projeto pode ser acessado no [SpaPet-test](https://github.com/Jeeh2/SpaPet-test).


