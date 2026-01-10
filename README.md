# 💰 Financial Management API

API REST de **gestão financeira** desenvolvida em **Java com Spring Boot**.

---

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- Banco de dados PostgresSQL
- Maven
- REST API

---

## 🧱 Arquitetura do Projeto

O projeto segue uma **arquitetura em camadas**, com responsabilidades bem definidas:

- **Controller** → Camada de entrada (API REST)
- **Service** → Regras de negócio (Business Rules)
- **Repository** → Persistência de dados
- **Entity (Domain)** → Modelo de domínio

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

### Pré-requisitos
- Java 17+
- Maven
- PostgresSQL

### Passos
```bash
# Clonar o repositório
git clone https://github.com/guilhermeaaraujo/financial-management-project.git

# Entrar no diretório
cd financial-management-project

# Executar a aplicação
mvn spring-boot:run
```
## 📌 Possíveis Evoluções

- Autenticação e autorização (Spring Security + JWT)
- Transferência entre contas
- Paginação e filtros

## 👨‍💻 Autor

**Guilherme Araújo**
Projeto desenvolvido com foco em aprendizado e arquitetura backend.

🔗 GitHub: https://github.com/guilhermeaaraujo
