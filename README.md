# 🚌 Transcard - Back End (Resumo)

## 🔹 Objetivo
Criar uma aplicação full-stack para **gerenciar usuários e cartões de transporte**, aplicando boas práticas de engenharia de software, arquitetura em camadas e design responsivo.

---

# Repositório principal [Transcard](https://github.com/kleber-a/transcard.git)

## 🔹 Funcionalidades Principais

### 1. Gestão de Usuários
- **Consultar usuários:**  
  - Admin vê todos os usuários.  
  - Usuário comum vê apenas o próprio cadastro.
- **Incluir usuário:**  
  - Permite criar novos usuários com nome, email e senha.
- **Alterar usuário:**  
  - Usuário pode alterar seu **nome e senha**.  
  - Admin pode alterar **nome, email e senha** de qualquer usuário.
- **Remover usuário:**  
  - Apenas usuários com perfil **ADMIN** podem remover outros usuários.
- **Perfis de acesso:**  
  - **ADMIN** – acesso total às funcionalidades.  
  - **USER** – acesso limitado às próprias informações e cartões.


### 2. Gestão de Cartões
- **Adicionar e remover cartões:**  
  - Apenas **admins** podem adicionar novos cartões a um usuário.  
  - Apenas **admins** podem remover cartões de um usuário.
- **Consultar cartões:**  
  - Admin pode consultar todos os cartões.  
  - Usuário comum vê apenas os seus próprios cartões.
- **Ativar/Inativar cartões:**  
  - Tanto admins quanto usuários podem alterar o status de cartões.  
  - Usuário comum só pode alterar o status dos **seus próprios cartões**.
- **Tipos de cartão:**  
  - **COMUM** – uso padrão.  
  - **ESTUDANTE** – desconto ou benefícios para estudantes.  
  - **TRABALHADOR** – uso específico para trabalhadores.
- **Relacionamento:**  
  - Cada usuário pode possuir **0 ou mais cartões** (1 usuário → N cartões).

 

### 3. Segurança
- Autenticação via **Spring Security**.  
- Controle de acesso baseado em roles (**ADMIN** e **USER**).  

---

## 🔹 Tecnologias Utilizadas

### Backend
- Java 17+ / Spring Boot 3+  
- Spring Data JPA / Hibernate  
- PostgreSQL 
- Swagger
- Migrações com Flyway
- Segurança com Spring Security + JWT  
- MapStruct para mapeamento entre DTOs e entidades  
- Estrutura em camadas: Controller → Service → Repository → Mapper → DTO → Model  
- Build com Maven
- **Tratamento de Erros:** Exceções personalizadas e globais com `@ControllerAdvice`

---

## 🔹 Boas Práticas Aplicadas
- Separação clara de responsabilidades (**camadas bem definidas**)  
- Uso de **DTOs** para transferência de dados  
- Estrutura modular para **escalabilidade e manutenção**  
- Aplicação de **Design Patterns** (Factory, DTO, Mapper)  
- Documentação da API via **Swagger**  
- Login e controle de permissões por perfil  
- Uso de **Native Queries** em SQL  

---


## Como Rodar Localmente

##### PostgreSQL

1. Instale PostgreSQL localmente.
2. Crie o banco de dados:.

Crie o banco de dados:

```bash
CREATE DATABASE transcard_database;
CREATE USER transcard_user WITH PASSWORD 'transcard_password';
GRANT ALL PRIVILEGES ON DATABASE transcard_database TO transcard_user;

```

##### Back-End
1. Configure o application.properties

```bash
# Configurações do Banco de Dados (PostgreSQL)
spring.datasource.url=jdbc:postgresql://localhost:5432/transcard_database
spring.datasource.username=transcard_user
spring.datasource.password=transcard_password
spring.datasource.driver-class-name=org.postgresql.Driver

# Configurações do JPA / Hibernate
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
# Recomendado: Mostrar o SQL no console para debug (opcional)
# spring.jpa.show-sql=true
```

2. Execute 
```bash
mvn spring-boot:run
```
