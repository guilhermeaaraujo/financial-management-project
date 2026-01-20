# 💰 Financial Management API

API REST de **gestão financeira** desenvolvida em **Java com Spring Boot**.

Este projeto foi desenvolvido como objeto de estudo para meu aprendizado no framework. Futuramente planejo utilizá-lo em um projeto Fullstack de Gestão financeira com uma aplicação web.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Banco de dados PostgresSQL
- Flyway Migrations
- Maven
- REST API
- Spring Security + JWT
- H2 Database (Para testes)
- JUnit + Mockito para testes unitários.

---

## 🧱 Arquitetura do Projeto

O projeto segue uma **arquitetura em camadas**, com responsabilidades bem definidas:

- **Controller** → Camada de entrada (API REST)
- **Service** → Regras de negócio (Business Rules)
- **Repository** → Persistência de dados
- **Entity (Domain)** → Modelo de domínio
- **Data Tranfer Objects (DTO's)** → Objetos para transferência de dados.

## 📊 Modelo de Domínio (Resumo)

### Entidades principais:
- **User** → Representa o usuário do sistema
- **Account** → Conta financeira do usuário
- **Transaction** → Movimentação financeira
- **Category** → Classificação da transação (entrada ou saída)

### Regras de negócio:
- Toda transação pertence a uma conta
- Toda transação possui uma categoria
- Categorias definem se a transação é **INCOME** ou **EXPENSE**
- Transações de despesa diminuem o saldo
- Transações de receita aumentam o saldo
- O saldo da conta é atualizado automaticamente dentro da transação
- Usuários podem registrar e deletar transações em suas próprias contas
- Usuários só podem criar e deletar contas para si mesmos.
- Usuários só podem acessas suas próprias contas e transações.

## 📊 Database Migrations

Esse projeto utiliza Flyway para versionamento e controle do banco de dados.

## 🔒 Autenticação e Autorização

- Implementada utilizando Spring Security + JWT com controle baseado em roles.
- ROLE_ADMIN: Representa os administradores com todas as permissões de CRUD ao banco de dados.
- ROLE_USER: Representa os usuários padrão da aplicação, tendo certas permissões sobre suas próprias contas e transações.

## 🔗 Endpoints da aplicação

```

// ENDPOINTS auth
- POST: /auth/register -> Registra um novo usuário no banco de dados. Permissão: Todos
- POST: /auth/login -> Loga na conta de um usuário e retorna um token para autorização. Permissão: Todos

// ENDPOINTS users
- GET: /users -> Retorna todos os usuários da aplicação. Permissão: ADMIN
- GET: /users/{id} -> Retorna um usuário por id. Permissão: ADMIN
- GET: /users/me -> Retorna os dados do usuário autenticado. Permissão: USER
- POST: /users -> Registra um novo usuário. Permissão: ADMIN
- DELETE: /users/{id} -> Deleta um usuário por id. Permissão: ADMIN
- PUT: /users/{id} -> Atualiza os dados de um usuário. Permissão: ADMIN

// ENDPOINTS accounts
- GET: /accounts -> Retorna todas as contas do banco de dados. Permissão: ADMIN
- GET: /accounts/{id} -> Retorna uma conta por id. Permissão: ADMIN
- GET: /accounts/myaccounts -> Retornas as contas do usuário autenticado. Permissão: USER
- GET: /accounts/{id}/transactions -> Retorna as transações de uma conta(Apenas para o usuário da conta) - Permissão: USER
- POST: /accounts -> Registra uma nova conta (Usuários podem criar contas para si mesmos). Permissão: USER
- DELETE: /accounts/{id} -> Deleta uma conta por id (Usuários podem deletar suas próprias contas). Permissão: USER
- PUT: /accounts/{id} -> Atualiza os dados de uma conta. Permissão: ADMIN

// ENDPOINTS transactions
- GET: /transactions -> Retorna todas as transações do banco de dados. Permissão: ADMIN
- GET: /transactions/{id} -> Retorna uma transação por id. Permissão: ADMIN
- POST: /transactionss -> Registra uma nova transação (Usuários podem inserir transações em suas próprias contas). Permissão: USER
- DELETE: /transactions/{id} -> Deleta uma transação por id (Usuários podem deletar suas próprias transações). Permissão: USER
- PUT: /transactions/{id} -> Atualiza os dados de uma transação. Permissão: ADMIN

// ENDPOINTS categories
- GET: /categories -> Retorna todas as categorias do banco de dados. Permissão: USER
- GET: /categories/{id} -> Retorna uma categoria por id. Permissão: USER
- POST: /categories -> Registra uma nova categoria. Permissão: ADMIN
- DELETE: /categories/{id} -> Deleta uma categoria por id. Permissão: ADMIN
- PUT: /categories/{id} -> Atualiza os dados de uma categoria. Permissão: ADMIN

```

### Pré-requisitos
- Java 17+
- Maven
- PostgresSQL

## 📌 Possíveis Evoluções

- Paginação e filtros
- FrontEnd

## 👨‍💻 Autor

**Guilherme Araújo**

Projeto desenvolvido com foco em aprendizado e arquitetura backend.

🔗 GitHub: https://github.com/guilhermeaaraujo
