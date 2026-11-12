# QA Design Patterns API — Desafio DIO

Projeto de portfólio para o desafio **Design Patterns com Java: Dos Clássicos (GoF) ao Spring Framework**.

## Objetivo

API REST para **cadastrar e simular a execução de casos de teste de QA**, aplicando padrões de projeto em um contexto de qualidade de software. **Atenção:** a execução é simulada a partir de um resultado informado pelo cliente; a API não abre navegador nem executa testes reais.

## Tecnologias

- Java 17, Spring Boot 3.3.5, Maven
- Spring Web, Spring Data JPA, Bean Validation
- H2 em memória, Swagger UI / OpenAPI
- JUnit 5, Mockito, GitHub Actions

## Padrões aplicados

| Padrão | Implementação | Benefício |
|---|---|---|
| Singleton | `QaSettings` como bean `@Component` singleton do Spring | Configuração compartilhada |
| Strategy | `TestExecutionStrategy` + `FunctionalStrategy`, `RegressionStrategy`, `ExploratoryStrategy` | Escolher comportamento pelo tipo de teste |
| Facade | `TestCaseFacade` | Simplificar acesso à persistência, estratégia e configuração |
| Repository | `TestCaseRepository` (Spring Data JPA) | Abstrair persistência |

## Executar

Pré-requisitos: **JDK 17+** e **Maven 3.9+**.

```bash
mvn spring-boot:run
```

Swagger: http://localhost:8080/swagger-ui.html

Console H2: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:qapatterns`, usuário: `sa`, senha vazia)

## Endpoints

| Método | Caminho | Ação |
|---|---|---|
| POST | `/api/test-cases` | Criar caso |
| GET | `/api/test-cases` | Listar casos |
| GET | `/api/test-cases/{id}` | Consultar caso |
| POST | `/api/test-cases/{id}/execute` | Simular execução |
| DELETE | `/api/test-cases/{id}` | Excluir caso |

### Exemplos

Criar um caso de teste:

```bash
curl -X POST http://localhost:8080/api/test-cases -H "Content-Type: application/json" -d '{"title":"Validar login","type":"FUNCTIONAL"}'
```

Executar simulação (substitua `1` pelo ID retornado):

```bash
curl -X POST http://localhost:8080/api/test-cases/1/execute -H "Content-Type: application/json" -d '{"simulatedSuccess":true}'
```

Tipos válidos: `FUNCTIONAL`, `REGRESSION`, `EXPLORATORY`. Status: `NOT_EXECUTED`, `PASSED`, `FAILED`.

## Testes automatizados

```bash
mvn test
```

Os testes unitários cobrem criação de caso, seleção da estratégia de regressão e retorno de erro quando um ID não existe. A pipeline `.github/workflows/java-ci.yml` executa os testes em push e pull request.

## Estrutura

```text
src/main/java/com/portfolio/qapatterns/
  config/       # Singleton
  controller/   # Endpoints REST
  dto/          # Requisições validadas
  exception/    # Tratamento de 404
  model/        # Entidade e enums
  repository/   # JPA
  service/      # Facade
  strategy/     # Strategy e implementações
src/test/       # Testes JUnit/Mockito
.github/workflows/ # CI
```

## Limitações e melhorias futuras

- Persistência H2 em memória: dados são perdidos ao reiniciar a aplicação.
- A execução de testes é simulada; integrar futuramente com Cypress, Playwright ou Selenium.
- Adicionar autenticação, paginação e testes de integração HTTP.

## Entrega na DIO

1. Crie um repositório público no GitHub (ex.: `dio-qa-design-patterns`).
2. Envie os arquivos deste projeto e confirme que o GitHub Actions executou os testes.
3. Entregue a URL do repositório no desafio da DIO.

Projeto autoral para estudo e portfólio. Personalize o README com seu nome e perfil antes de publicar.
