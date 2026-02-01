# 🚌 Transcard Back End - Desafio Técnico (Resumo)

## 🔹 Objetivo
Criar uma aplicação full-stack para **gerenciar usuários e cartões de transporte**, aplicando boas práticas de engenharia de software, arquitetura em camadas e design responsivo.

---

# Repositório principal [Transcard](https://github.com/kleber-a/transcard.git)

## 🔹 Funcionalidades Principais

### 1. Gestão de Usuários
- Consultar usuários (admin vê todos, usuário comum vê apenas o próprio).  
- Incluir usuário.  
- Alterar usuário (nome, email e senha).  
- Remover usuário (restrito a admin).  
- Perfis de acesso: **ADMIN** e **USER**.  

### 2. Gestão de Cartões
- Adicionar e remover cartões de usuários.  
- Consultar cartões (todos ou próprios).  
- Ativar/Inativar cartões.  
- Tipos de cartão: **COMUM, ESTUDANTE, TRABALHADOR**.  
- Relacionamento: 1 usuário → N cartões.  

### 3. Segurança
- Autenticação via **Spring Security**.  
- Controle de acesso baseado em roles (**ADMIN** e **USER**).  

---

## 🔹 Tecnologias Utilizadas

### Backend
- Java 8+ / Spring Boot  
- Spring Data JPA / Hibernate  
- PostgreSQL (ou outro banco SQL)
- Swagger
- Migrações com Flyway (opcional)  
- Estrutura em camadas: Controller → Service → Repository → Mapper → DTO → Model  
- Build com Maven  

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


### Rodando Localmente

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
