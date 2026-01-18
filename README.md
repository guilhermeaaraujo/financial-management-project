# 💰 Financial Management API

API REST de **gestão financeira** desenvolvida em **Java com Spring Boot**.

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

## 📊 Database Migrations

Esse projeto utiliza Flyway para versionamento e controle do banco de dados.

### Autenticação e Autorização

- Implementada utilizando Spring Security + JWT com controle baseado em roles.

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
