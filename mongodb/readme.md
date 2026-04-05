# 📝 MongoDB API - Users & Posts

Projeto desenvolvido durante o curso **Java COMPLETO — Programação Orientada a Objetos + Projetos**, ministrado por **Nelio Alves**.

Autor: **Eduardo Carvalho** — [github.com/eduardohacarvalho](https://github.com/eduardohacarvalho)

---
## 📋 Sobre o Projeto
API REST desenvolvida com Spring Boot e MongoDB para gerenciamento de usuários e posts, com suporte a comentários e busca avançada.

## 🚀 Tecnologias

- Java
- Spring Boot
- Spring Data MongoDB
- MongoDB
- Maven

## 📁 Estrutura do Projeto

src/main/java/com/eduardohacarvalho/mongodb 

├── config # Configuração e carga inicial de dados 

├── domain # Entidades (User, Post) 

├── dto # Objetos de transferência de dados

├── repository # Repositórios MongoDB

├── resources # Endpoints REST 

└── services # Regras de negócio

## ⚙️ Pré-requisitos

- Java 17+
- MongoDB rodando localmente na porta 27017
- Maven

## 🔧 Configuração

1. Clone o repositório: git clone https://github.com/SEU_USUARIO/mongodb-api.git

2. Configure o application.properties: spring.data.mongodb.uri=mongodb://localhost:27017/workshop_mongo

3. Rode o projeto: mvn spring-boot:run

Ao iniciar, o projeto popula automaticamente o banco com dados de exemplo via classe Instantiation.

## 📌 Endpoints

### 👤 Usuários

| Método | URL | Descrição |
|--------|-----|-----------|
| GET | /users | Listar todos os usuários |
| GET | /users/{id} | Buscar usuário por ID |
| GET | /users/{id}/posts | Listar posts do usuário |
| POST | /users | Criar usuário |
| PUT | /users/{id} | Atualizar usuário |
| DELETE | /users/{id} | Deletar usuário |

### 📄 Posts

| Método | URL | Descrição |
|--------|-----|-----------|
| GET | /posts/{id} | Buscar post por ID |
| GET | /posts/titlesearch?text= | Buscar posts pelo título |
| GET | /posts/fullsearch | Busca avançada por texto e data |

## 🔍 Busca Avançada

O endpoint /posts/fullsearch aceita os seguintes parâmetros:

| Parâmetro | Descrição | Exemplo |
|-----------|-----------|---------|
| text | Texto a buscar no título, corpo e comentários | Viagem |
| minDate | Data mínima (yyyy-MM-dd) | 2018-03-21 |
| maxDate | Data máxima (yyyy-MM-dd) | 2018-03-23 |

Exemplo de uso: GET /posts/fullsearch?text=viagem&minDate=2018-03-21&maxDate=2018-03-23

## 📝 Exemplos de uso

### Criar usuário POST /users { "name": "Eduardo", "email": "eduardo@email.com" }

### Buscar posts por título GET /posts/titlesearch?text=viagem

## 🗂️ Modelo de Dados

### User { "id": "...", "name": "Maria Brown", "email": "maria@gmail.com", "posts": [...] }

### Post { "id": "...", "date": "2018-03-21", "title": "Partiu viagem", "body": "Vou viajar para SP. Abraços!", "author": { "id": "...", "name": "Maria Brown" }, "comments": [ { "text": "Boa viagem mano!", "date": "2018-03-21", "author": { "id": "...", "name": "Alex Green" } } ] }

