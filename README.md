# ☕ jdbc-triggers-procedures: Persistência de Dados com Java & JDBC

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-Migrations-CC0202?style=for-the-badge&logo=flyway&logoColor=white)
![DIO](https://img.shields.io/badge/DIO-Java%20Fundamentals-orange?style=for-the-badge)

## 📌 Sumário
* [Sobre o Projeto](#-sobre-o-projeto)
* [Principais Conceitos Abordados](#-principais-conceitos-abordados)
  * [Versionamento de Banco com Flyway](#1-versionamento-de-banco-com-flyway)
  * [Manipulação e Operações CRUD](#2-manipulação-e-operações-crud)
  * [Controle Manual de Transações e Commits](#3-controle-manual-de-transações-e-commits)
  * [Modelagem de Relacionamentos (1:N e N:N)](#4-modelagem-de-relacionamentos-1n-e-nn)
  * [Recursos Avançados: Triggers, Views e Procedures](#5-recursos-avançados-triggers-views-e-procedures)
* [💡 Destaque: Mock de Dados de Teste com DataFaker](#-destaque-mock-de-dados-de-teste-com-datafaker)
* [Tecnologias Utilizadas](#-tecnologias-utilizadas)
* [Estrutura do Repositório](#-estrutura-do-repositório)
* [Configuração do Ambiente e Como Executar](#-configuração-do-ambiente-e-como-executar)
* [Licença](#-licença)

---

## 📖 Sobre o Projeto

O repositório **jdbc-triggers-procedures** foi desenvolvido como projeto prático do curso **"JDBC: Explorando persistência de Dados com Java"**, módulo de encerramento da trilha **Java Fundamentals** da [Digital Innovation One (DIO)](https://www.dio.me/).

O objetivo principal deste projeto é explorar os fundamentos da API nativa **JDBC (Java Database Connectivity)**, dispensando frameworks ORM de alto nível em um primeiro momento para consolidar o entendimento íntimo de conexões, tráfego de dados relacionais via SQL, gerenciamento de transações atômicas e evolução controlada de schemas via migrações com **Flyway**.

---

## 🧠 Principais Conceitos Abordados

### 1. Versionamento de Banco com Flyway
* Gerenciamento e versionamento evolutivo do esquema do banco de dados relacional.
* Criação e rastreamento de scripts SQL (`V1__...sql`, `V2__...sql`, etc.), garantindo reprodutibilidade entre diferentes ambientes de desenvolvimento e produção sem alterações manuais no banco.

### 2. Manipulação e Operações CRUD
* Ciclo de vida da conexão JDBC via `DriverManager` e `DataSource`.
* Criação de instruções seguras com `PreparedStatement` para prevenir ataques de **SQL Injection**.
* Iteração e extração de dados através de ponteiros relacionais com `ResultSet`.
* Execução otimizada de comandos com `executeUpdate()` e `executeQuery()`.

### 3. Controle Manual de Transações e Commits
* Desativação do modo padrão de autocommit (`connection.setAutoCommit(false)`).
* Agrupamento de operações interdependentes sob o princípio **ACID** (Atomicidade, Consistência, Isolamento e Durabilidade).
* Confirmação manual de lotes com `connection.commit()` e tratamento defensivo de falhas com desfazimento seguro via `connection.rollback()`.

### 4. Modelagem de Relacionamentos (1:N e N:N)
* **Relacionamento 1:N (Um para Muitos):** Mapeamento de entidades com chaves estrangeiras (`FOREIGN KEY`), garantindo integridade referencial ao persistir dados mestres e dependentes.
* **Relacionamento N:N (Muitos para Muitos):** Criação e consumo de tabelas associativas/intermediárias via SQL, com junções (`INNER JOIN`) e mapeamento bidirecional no código Java.

### 5. Recursos Avançados: Triggers, Views e Procedures
* **Triggers (Gatilhos):** Regras de negócio automáticas disparadas pelo motor do banco após eventos de `INSERT`, `UPDATE` ou `DELETE` (ex.: registros de auditoria ou atualização de saldos).
* **Views (Visões):** Encapsulamento de consultas relacionais complexas com múltiplos joins para simplificar e reaproveitar leituras na aplicação Java.
* **Stored Procedures:** Execução de rotinas compiladas no servidor MySQL disparadas a partir de chamadas JDBC com `CallableStatement`.

---

## 💡 Destaque: Mock de Dados de Teste com DataFaker

> [!TIP]
> ### 🎲 Dica Pro: Povoamento Automatizado com `net.datafaker.Faker`
> Em vez de cadastrar registros de teste manualmente via comandos estáticos ou valores fixos (como `"Teste 1"`, `"Teste 2"`), utilize a biblioteca **DataFaker** para alimentar o banco relacional em massa com dados realistas e dinâmicos de vendedores/funcionários:

```groovy
// Adicione ao seu build.gradle:
dependencies {
    testImplementation 'net.datafaker:datafaker:2.4.2'
    // ou implementation 'net.datafaker:datafaker:2.4.2'
}
```

```java
import net.datafaker.Faker;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

public class DataSeeder {
    public static void main(String[] args) {
        // Inicializa o Faker localizado para português do Brasil
        Faker faker = new Faker(new Locale("pt", "BR"));

        for (int i = 0; i < 20; i++) {
            // Gera nome completo realista
            String nome = faker.name().fullName();

            // Gera salário entre R$ 2.000,00 e R$ 15.000,00
            BigDecimal salario = BigDecimal.valueOf(faker.number().randomDouble(2, 2000, 15000));

            // Gera data de nascimento entre 18 e 60 anos
            LocalDate dataNascimento = faker.date().birthday(18, 60)
                                            .toInstant()
                                            .atZone(ZoneId.systemDefault())
                                            .toLocalDate();

            System.out.printf("Inserindo: %s | Salário: R$ %.2f | Nasc: %s%n",
                    nome, salario, dataNascimento);

            // Basta passar essas variáveis para o seu SellerDao.insert(...)
        }
    }
}
```

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 17+
* **Conectividade:** JDBC (Java Database Connectivity)
* **Driver:** MySQL Connector/J
* **Migração de Banco de Dados:** Flyway
* **Geração de Dados Falsos:** DataFaker
* **SGBD:** MySQL 8.0+
* **Gerenciador de Dependências e Build:** Gradle

---

## 📁 Estrutura do Repositório

Organização em camadas das rotinas JDBC e recursos de migração gerenciados via Gradle:

```text
jdbc-triggers-procedures/
├── gradle/
│   └── wrapper/             # Arquivos binários e propriedades do Gradle Wrapper
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/dio/jdbc/
│   │   │       ├── config/  # Gerenciamento de conexões (ConnectionFactory)
│   │   │       ├── dao/     # Data Access Object com queries SQL manuais
│   │   │       ├── model/   # Entidades de domínio (POJOs)
│   │   │       └── Main.java# Execução e testes das rotinas JDBC
│   │   └── resources/
│   │       ├── db/migration/# Scripts versionados de migração Flyway (V1, V2...)
│   │       └── application.properties / db.properties
├── build.gradle             # Declaração de dependências e plugins (Flyway, MySQL)
├── gradlew                  # Wrapper executável para sistemas Unix/Linux/macOS
├── gradlew.bat              # Wrapper executável para sistemas Windows
└── settings.gradle          # Configuração de nome e módulos do projeto
```

---

## ⚙️ Configuração do Ambiente e Como Executar

### Pré-requisitos
* **JDK 17** ou superior instalado e configurado nas variáveis de ambiente (`JAVA_HOME`).
* Instância do **MySQL Server** ativa localmente ou via container Docker.
* **Gradle** (opcional, pois o repositório já inclui o **Gradle Wrapper** `./gradlew`).

### 1. Criar o Banco de Dados no MySQL
Execute o comando SQL no seu cliente de preferência (Workbench, DBeaver ou terminal):

```sql
CREATE DATABASE IF NOT EXISTS jdbcdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. Configurar as Propriedades de Conexão
Edite o arquivo `src/main/resources/db.properties` (ou correspondente) com suas credenciais locais:

```properties
db.url=jdbc:mysql://localhost:3306/jdbcdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
db.user=seu_usuario
db.password=sua_senha
```

### 3. Clonar e Executar o Projeto
1. Clone o repositório:
   ```bash
   git clone https://github.com/SEU_USUARIO/jdbc-triggers-procedures.git
   cd jdbc-triggers-procedures
   ```

2. Compile o projeto e execute a classe principal utilizando o Gradle Wrapper:
   * **Linux/macOS:**
     ```bash
     ./gradlew build
     ./gradlew run
     ```
   * **Windows (PowerShell / CMD):**
     ```cmd
     gradlew.bat build
     gradlew.bat run
     ```

3. *(Opcional)* Se configurou o plugin do Flyway no `build.gradle`, execute as migrações manualmente com:
   ```bash
   ./gradlew flywayMigrate
   ```

---

## 🤝 Contribuição

1. Faça um Fork do repositório (`git clone https://github.com/SEU_USUARIO/jdbc-triggers-procedures.git`).
2. Crie uma branch para sua alteração (`git checkout -b feature/minha-melhoria`).
3. Confirme suas mudanças (`git commit -m 'feat: adiciona rotina com DataFaker para teste de carga'`).
4. Envie sua branch (`git push origin feature/minha-melhoria`).
5. Abra um **Pull Request**.

---

## 📄 Licença

Este projeto está sob a licença [MIT](LICENSE).

---
<p align="center">Desenvolvido durante a formação <b>Java Fundamentals</b> na <b>DIO</b> 🚀</p>

