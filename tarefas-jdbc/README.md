# Tarefas JDBC

Aplicação console em Java para gestão de tarefas, com categorias, status de conclusão e filtros, com persistência em banco de dados PostgreSQL via JDBC.

## Funcionalidades

- Criar tarefa
- Listar todas as tarefas
- Editar tarefa
- Marcar tarefa como concluída
- Excluir tarefa
- Filtrar tarefas por categoria
- Filtrar tarefas por status (concluída / pendente)

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
cd tarefas-jdbc
```

### 2. Criar o banco de dados

No pgAdmin (ou psql), crie um banco chamado `tarefas_db` e rode o script abaixo:

```sql
CREATE TABLE tarefa (
    id SERIAL PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    concluida BOOLEAN NOT NULL DEFAULT FALSE
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
│   └── Tarefa.java             → classe que representa uma tarefa
└── dao/
    └── TarefaDAO.java          → lógica de CRUD e filtros (categoria/status)
```
