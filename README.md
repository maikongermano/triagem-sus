# Triagem SUS

Projeto desenvolvido para o Hackathon da FIAP - Pós-graduação em Arquitetura e Desenvolvimento em Java.  
O objetivo é otimizar o atendimento no SUS por meio de uma triagem inteligente que prioriza pacientes com base nos sintomas informados.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 (default) / PostgreSQL (comentado no `application.properties`)
- Swagger/OpenAPI (Springdoc)
- JUnit 5 / Mockito
- Maven

## 🧠 Arquitetura

Este projeto segue o padrão de **Arquitetura Limpa**, com separação de responsabilidades em:

- `domain.model` → Entidades JPA
- `domain.repository` → Interfaces dos repositórios
- `application.service` → Interfaces de serviços
- `infrastructure.service` → Implementações dos serviços
- `infrastructure.controller` → Controladores REST

## ✅ Funcionalidades

- **Cadastro de Pacientes**  
  Armazena informações como nome, CPF e data de nascimento.

- **Cadastro de Sintomas**  
  Permite registrar sintomas clínicos comuns reportados na triagem.

- **Triagem de Pacientes**  
  Realiza a triagem com base nos sintomas informados, classificando automaticamente:
  - `EMERGENCIA` → Ex: "dor no peito", "falta de ar"
  - `URGENTE` → Três ou mais sintomas leves
  - `NAO_URGENTE` → Um ou dois sintomas leves

- **Listagem de Triagens por Prioridade**  
  Retorna as triagens ordenadas por prioridade e data/hora da chegada.

- **Documentação com Swagger**  
  A interface interativa via Swagger facilita a visualização e teste das rotas.

- **Banco H2 em Memória (modo dev)**  
  Permite rodar o projeto sem depender de banco externo.

- **Cobertura de Testes Unitários**  
  Inclui testes para todos os serviços e controladores.

## 📦 Como Rodar Localmente

1. **Clone o repositório**
   ```bash
   git clone <repo-url>
   cd triagem-sus
   ```

2. **Compile e execute**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Acesse o Swagger UI**
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🔄 Alternar entre H2 e PostgreSQL

- O projeto já vem configurado com o banco H2 por padrão.
- Para usar PostgreSQL, edite o arquivo `src/main/resources/application.properties`:
  - Comente as linhas do H2
  - Descomente as linhas do PostgreSQL
  - Ajuste `username`, `password` e `url` conforme necessário

## 🧪 Testes

Execute os testes com:

```bash
./mvnw test
```

## 📈 Diagrama da Arquitetura

O arquivo `arquitetura-triagem-sus.png` representa graficamente os fluxos entre as camadas da aplicação.

## 📃 Relatório do Projeto

Consulte o arquivo `relatorio-triagem-sus.txt` para um resumo completo do problema, solução proposta, tecnologias utilizadas e próximos passos.

---

**Desenvolvido por:** Maikon Vinicius Germano  
**Hackathon FIAP - Fase 5 - Inovação para otimização de atendimento no SUS**
