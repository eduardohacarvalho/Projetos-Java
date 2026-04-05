# 🛒 Spring Boot JPA — Web Services Project

Projeto desenvolvido durante o curso **Java COMPLETO — Programação Orientada a Objetos + Projetos**, ministrado por **Nelio Alves**.

Autor: **Eduardo Carvalho** — [github.com/eduardohacarvalho](https://github.com/eduardohacarvalho)

---

## 📋 Sobre o Projeto

API REST construída com **Spring Boot** e **JPA/Hibernate**, simulando um sistema de e-commerce com gerenciamento de usuários, produtos, categorias e pedidos. O foco principal é a implementação de relacionamentos entre entidades usando JPA, com persistência em banco de dados relacional.

---

## 🏗️ Modelo de Domínio

```
User ──< Order >── OrderItem >── Product >── Category
               └── Payment
```

- **User**: cliente que realiza pedidos
- **Order**: pedido vinculado a um cliente, com status e pagamento
- **OrderItem**: item de pedido com chave composta (Order + Product)
- **Product**: produto associado a uma ou mais categorias
- **Category**: categoria de produtos
- **Payment**: pagamento vinculado ao pedido (relação 1:1)
- **OrderStatus**: enum com os estados do pedido

---

## 🗂️ Estrutura do Projeto

```
src/
├── entities/
│   ├── enums/
│   │   └── OrderStatus.java
│   ├── pk/
│   │   └── OrderItemPK.java
│   ├── Category.java
│   ├── Order.java
│   ├── OrderItem.java
│   ├── Payment.java
│   ├── Product.java
│   └── User.java
├── repositories/
│   ├── CategoryRepository.java
│   ├── OrderRepository.java
│   ├── OrderItemRepository.java
│   ├── ProductRepository.java
│   └── UserRepository.java
├── resources/
│   ├── exceptions/
│   │   ├── ResourceExceptionHandler.java
│   │   └── StandardError.java
│   ├── CategoryResource.java
│   ├── OrderResource.java
│   ├── ProductResource.java
│   └── UserResource.java
└── services/
    ├── exceptions/
    │   ├── DataBaseException.java
    │   └── ResourceNotFoundException.java
    ├── CategoryService.java
    ├── OrderService.java
    ├── ProductService.java
    └── UserService.java
```

---

## 🔗 Endpoints da API

### Users — `/users`

| Método | Endpoint       | Descrição              |
|--------|----------------|------------------------|
| GET    | `/users`       | Lista todos os usuários |
| GET    | `/users/{id}`  | Busca usuário por ID   |
| POST   | `/users`       | Cria novo usuário      |
| PUT    | `/users/{id}`  | Atualiza usuário       |
| DELETE | `/users/{id}`  | Remove usuário         |

### Orders — `/orders`

| Método | Endpoint        | Descrição           |
|--------|-----------------|---------------------|
| GET    | `/orders`       | Lista todos os pedidos |
| GET    | `/orders/{id}`  | Busca pedido por ID |

### Products — `/products`

| Método | Endpoint          | Descrição             |
|--------|-------------------|-----------------------|
| GET    | `/products`       | Lista todos os produtos |
| GET    | `/products/{id}`  | Busca produto por ID  |

### Categories — `/categories`

| Método | Endpoint            | Descrição               |
|--------|---------------------|-------------------------|
| GET    | `/categories`       | Lista todas as categorias |
| GET    | `/categories/{id}`  | Busca categoria por ID  |

---

## ⚙️ Relacionamentos JPA Implementados

| Relacionamento | Entidades |
|---|---|
| `@ManyToOne` | Order → User |
| `@OneToMany` | User → Order, Order → OrderItem, Product → OrderItem |
| `@OneToOne` | Order ↔ Payment (`@MapsId`, `CascadeType.ALL`) |
| `@ManyToMany` | Product ↔ Category (tabela `tb_product_category`) |
| `@EmbeddedId` | OrderItem com chave composta `OrderItemPK` |

---

## 🚦 Status do Pedido

```java
WAITING_PAYMENT(1)
PAID(2)
SHIPPED(3)
DELIVERED(4)
CANCELLED(5)
```

---

## 🛡️ Tratamento de Erros

Implementado com `@ControllerAdvice`, retornando respostas padronizadas:

- **404 Not Found** → `ResourceNotFoundException`
- **400 Bad Request** → `DataBaseException` (violação de integridade)

Resposta padrão de erro:
```json
{
  "timestamp": "2024-01-01T00:00:00Z",
  "status": 404,
  "error": "Resource Not Found",
  "message": "Resource not found. Id: 99",
  "path": "/users/99"
}
```

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **Jakarta Persistence (JPA)**
- **Jackson** (serialização JSON com `@JsonFormat`, `@JsonIgnore`)
- **Maven**

---

## ▶️ Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/eduardohacarvalho/nome-do-repositorio.git
```

2. Acesse o diretório do projeto:
```bash
cd nome-do-repositorio
```

3. Execute com Maven:
```bash
./mvnw spring-boot:run
```

4. A API estará disponível em `http://localhost:8080`

