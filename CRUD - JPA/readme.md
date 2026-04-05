# ☕ JPA Puro — CRUD com EntityManager

Projeto desenvolvido durante o curso **Java COMPLETO — Programação Orientada a Objetos + Projetos**, ministrado por **Nelio Alves**.

Autor: **Eduardo Carvalho** — [github.com/eduardohacarvalho](https://github.com/eduardohacarvalho)

---

## 📋 Sobre o Projeto

Aplicação Java simples demonstrando as operações básicas de **CRUD** (Create, Read, Update, Delete) utilizando **JPA puro** com `EntityManager`, sem frameworks como Spring. O objetivo é entender o funcionamento da especificação JPA na sua forma mais fundamental, com controle manual de transações e ciclo de vida das entidades.

---

## 🗂️ Estrutura do Projeto

```
src/
└── org.jpa/
    ├── dominio/
    │   └── Pessoa.java       # Entidade JPA mapeada
    └── Main.java             # Demonstração do CRUD completo
```

---

## 🧩 Entidade Mapeada

### `Pessoa`

| Campo  | Tipo    | Descrição                        |
|--------|---------|----------------------------------|
| `id`   | Integer | Chave primária, gerada automaticamente (`IDENTITY`) |
| `nome` | String  | Nome da pessoa                   |
| `email`| String  | E-mail da pessoa                 |

```java
@Entity
public class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
}
```

---

## ⚙️ Operações CRUD Demonstradas

### ✅ Create — Persistir entidades
```java
em.getTransaction().begin();
em.persist(new Pessoa(null, "Carlos da Silva", "carlos@gmail.com"));
em.getTransaction().commit();
```

### 🔍 Read — Buscar por ID
```java
Pessoa p = em.find(Pessoa.class, 3);
```

### ✏️ Update — Atualizar dados dentro de uma transação
```java
em.getTransaction().begin();
p.setNome("Carlos Silva Atualizado");
p.setEmail("carlos.novo@gmail.com");
em.getTransaction().commit();
```

### 🗑️ Delete — Remover entidade
```java
em.getTransaction().begin();
em.remove(p);
em.getTransaction().commit();
```

---

## 🔑 Conceitos JPA Abordados

| Conceito | Descrição |
|---|---|
| `EntityManagerFactory` | Fábrica de `EntityManager`, criada uma vez por aplicação |
| `EntityManager` | Interface principal para operações de persistência |
| `em.persist()` | Insere uma nova entidade no banco |
| `em.find()` | Busca uma entidade pelo ID (chave primária) |
| `em.remove()` | Remove uma entidade gerenciada |
| `getTransaction()` | Controle manual de transações |
| `@Entity` | Marca a classe como entidade JPA |
| `@Id` + `@GeneratedValue` | Define chave primária com geração automática |
| `persistence.xml` | Arquivo de configuração da unidade de persistência (`exemplo-jpa`) |

---

## 🛠️ Tecnologias Utilizadas

- **Java**
- **JPA (javax.persistence)**
- **Hibernate** (implementação JPA)
- **Maven**

---

## ▶️ Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/eduardohacarvalho/nome-do-repositorio.git
```

2. Configure o `persistence.xml` em `src/main/resources/META-INF/` com os dados do seu banco:
```xml
<persistence-unit name="exemplo-jpa">
    <properties>
        <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/seu_banco"/>
        <property name="javax.persistence.jdbc.user" value="root"/>
        <property name="javax.persistence.jdbc.password" value="sua_senha"/>
        <property name="hibernate.hbm2ddl.auto" value="update"/>
    </properties>
</persistence-unit>
```

3. Execute a classe `Main.java`

---

## 📚 Referências

- [Jakarta Persistence (JPA)](https://jakarta.ee/specifications/persistence/)
- [Hibernate ORM](https://hibernate.org/orm/)
- Curso: Java COMPLETO — Programação Orientada a Objetos + Projetos — Nelio Alves