# Cursos JDBC

Aplicação console em Java para gerenciamento de cursos (CRUD completo), com persistência em banco de dados PostgreSQL via JDBC.

## Funcionalidades

- Inserir curso
- Listar cursos
- Atualizar curso
- Deletar curso

## Tecnologias

- Java 17
- Maven
- PostgreSQL
- JDBC (driver oficial `org.postgresql:postgresql`)

## Como rodar o projeto

### Pré-requisitos

- JDK 17 ou superior
- Maven
- PostgreSQL instalado e rodando localmente

### 1. Clonar o repositório

```
git clone <url-do-repositorio>
cd cursos-jdbc
```

### 2. Criar o banco de dados

No pgAdmin (ou psql), crie um banco chamado `cursos_db` e rode o script abaixo:

```sql
CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carga_horaria INTEGER NOT NULL,
    professor VARCHAR(100)
);
```

### 3. Configurar a conexão com o banco

Copie o arquivo de exemplo e preencha com sua senha:

```
copy src\main\resources\database.properties.example src\main\resources\database.properties
```

Edite `src/main/resources/database.properties` e ajuste `db.password` para a senha do seu PostgreSQL local.

> O arquivo `database.properties` não é versionado (está no `.gitignore`), pois contém credenciais sensíveis.

### 4. Compilar e rodar

```
mvn compile
mvn exec:java -Dexec.mainClass="com.fatec.App"
```

## Estrutura do projeto

```
src/main/java/com/fatec/
├── App.java                    → menu principal (switch case)
├── conexao/
│   └── ConnectionFactory.java  → conexão com o PostgreSQL
├── modelo/
│   └── Curso.java              → classe que representa um curso
└── dao/
    └── CursoDAO.java           → lógica de inserir/listar/atualizar/deletar
```
