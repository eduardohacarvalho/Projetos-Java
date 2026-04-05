# 📋 Usuarios API

API REST desenvolvida com Spring Boot para gerenciamento de usuários e tarefas, com autenticação via JWT.

## 🚀 Tecnologias

- Java 25
- Spring Boot 4.0.5
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (JSON Web Token)
- Lombok
- Maven

## 📁 Estrutura do Projeto
src/main/java/com/eduardohacarvalho/usuarios_api
├── controller       # Endpoints REST
├── service          # Regras de negócio
├── repository       # Comunicação com o banco
├── model            # Entidades JPA
├── security         # Configuração JWT e Spring Security
├── dto              # Objetos de transferência de dados
└── exception        # Tratamento global de erros

## ⚙️ Pré-requisitos

- Java 21+
- PostgreSQL
- Maven

## 🔧 Configuração

1. Clone o repositório:
```bash
git clone https://github.com/SEU_USUARIO/usuarios-api.git
```

2. Crie o banco de dados no PostgreSQL:
```sql
CREATE DATABASE projeto1;
```

3. Configure o `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/projeto1
spring.datasource.username=postgres
spring.datasource.password=SUA_SENHA
jwt.secret=SUA_CHAVE_SECRETA
jwt.expiration=86400000
```

4. Rode o projeto:
```bash
mvn spring-boot:run
```

## 📌 Endpoints

### 🔓 Autenticação (público)

| Método | URL | Descrição |
|--------|-----|-----------|
| POST | `/auth/register` | Cadastrar usuário |
| POST | `/auth/login` | Fazer login e receber token |

### 👤 Usuários (autenticado)

| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/users` | Listar todos os usuários |
| GET | `/users/{id}` | Buscar usuário por ID |
| GET | `/users/email/{email}` | Buscar usuário por email |
| PUT | `/users/{id}` | Atualizar usuário |
| DELETE | `/users/{id}` | Deletar usuário |

### ✅ Tarefas (autenticado)

| Método | URL | Descrição |
|--------|-----|-----------|
| GET | `/tasks` | Listar tarefas do usuário logado |
| GET | `/tasks/{id}` | Buscar tarefa por ID |
| POST | `/tasks` | Criar tarefa |
| PUT | `/tasks/{id}` | Atualizar tarefa |
| DELETE | `/tasks/{id}` | Deletar tarefa |

## 🔐 Autenticação

A API usa JWT. Após o login, inclua o token no header de todas as requisições:

## 📝 Exemplos de uso

### Registro
```json
POST /auth/register
{
    "name": "Eduardo",
    "email": "eduardo@email.com",
    "password": "123456"
}
```

### Login
```json
POST /auth/login
{
    "email": "eduardo@email.com",
    "password": "123456"
}
```

### Criar tarefa
```json
POST /tasks
{
    "title": "Estudar Spring Boot",
    "description": "Aprender sobre JWT e Spring Security"
}
```

## 👨‍💻 Autor

Eduardo — [GitHub](https://github.com/eduardohacarvalho)