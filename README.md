# Templa - Sistema de Agendamento de Cursos

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Stripe](https://img.shields.io/badge/Stripe-5469d4?style=for-the-badge&logo=stripe&logoColor=ffffff)
![Twilio](https://img.shields.io/badge/Twilio-F22F46?style=for-the-badge&logo=TwiliologoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)

[![Licence](https://img.shields.io/github/license/Ileriayo/markdown-badges?style=for-the-badge)](./LICENSE)

## 📘 Sobre o Projeto

**Templa** é uma plataforma para conectar **alunos** e **professores** em um ambiente simples e seguro.  
Os professores podem cadastrar cursos e turmas, enquanto os alunos podem realizar inscrições e pagamentos online.

💡 O objetivo do projeto é **facilitar o processo de agendamento e inscrição em cursos particulares**, com foco em escalabilidade
e boas práticas de desenvolvimento.

## 🛠️ Descrição Técnica

A aplicação foi desenvolvida como uma **API RESTful** utilizando **Spring Boot**, permitindo a interação entre alunos e professores em um fluxo simples e seguro:

- **Cadastro de usuários**: alunos e professores podem se registrar na plataforma.
- **Gestão de cursos**: professores criam cursos e adicionam turmas com dias e horários disponíveis.
- **Inscrição de alunos**: alunos podem se matricular nos cursos desejados.
- **Processo de pagamento**: ao finalizar a inscrição, o aluno é redirecionado para uma tela segura de pagamento.
- **Confirmação e notificações**:
    - Após o pagamento, o aluno é automaticamente adicionado à turma do curso.
    - O professor recebe uma notificação sobre a nova matrícula.
    - O aluno recebe uma mensagem de boas-vindas confirmando sua inscrição.

## ✨ Funcionalidades

- 👤 Cadastro de usuários (alunos e professores).
- 📚 Criação de cursos por professores.
- 🕒 Adição de turmas com horários disponíveis.
- 📝 Matrícula de alunos em cursos.
- 🔔 Notificações para alunos e professores.
- 💳 Integração com sistema de pagamento seguro.
- 🔐 Autenticação e autorização de usuários.
- 📑 Documentação da API com **Swagger**.
- ✅ Testes unitários e de integração.

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot**
- **Gradle**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Spring Security**
- **Swagger/OpenAPI**
- **JUnit + Mockito**

## 🚀 Como Executar o Projeto

Siga os passos abaixo para rodar o projeto localmente:

### Pré-requisitos:

- Java 21 ou superior instalado.
- Gradle instalado (ou use o wrapper `./gradlew`).
- Docker
- Configurar .env com as variáveis necessárias (veja o exemplo em `.env.example`).

### Passos para Execução:

1. Clone o repositório

```bash
git clone https://github.com/lucasmoraist/templa.git
```

2. Configure o arquivo `.env` com as variáveis necessárias.

```.env
POSTGRES_USER=user
POSTGRES_HOST=localhost
POSTGRES_DB=database
POSTGRES_PASSWORD=password
POSTGRES_PORT=5432
```

3. Rode o Docker Compose

```bash
docker-compose up -d
```

4. Execute o projeto

```bash
./gradlew bootRun
```

## 🛤️ Melhorias Futuras

- Implementar WebSocket para notificações em tempo real.
- Implementar envio de mensagens
- Adicionar serviço de pagamento.

## Licença
Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.