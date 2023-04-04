# Voll Med | Clínica médica

Este projeto foi desenvolvido com o objetivo de simular o sistema de gerenciamento de uma clínica médica atráves de uma API REST, contemplando as entidades Médico, Paciente e Consulta.

## Funcionalidades

- Cadastro de médicos, pacientes;
- Agendamento de consultas entre médicos e pacientes;
- Possibilidade de visualizar a agenda de consultas de cada médico e paciente.

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Junit
- Mockito
- MySQL
- Maven

## Como executar o projeto

Para executar o projeto em sua máquina local, siga os seguintes passos:

1. Clone este repositório em sua máquina local utilizando o comando `git clone https://github.com/SEU_USUARIO/NOME_DO_REPOSITORIO.git`
2. Certifique-se de ter o MySQL instalado em sua máquina e crie uma nova base de dados com o nome `clinica_medica`
3. Abra o projeto em sua IDE de preferência e configure o arquivo `application.properties` com suas credenciais de acesso ao banco de dados
4. Execute a classe `ApiApplication.java` para iniciar a aplicação
5. Acesse a API através da URL `http://localhost:8080`

## Endpoints da API

| Método | Endpoint                | Descrição                                           |
| ------ | -----------------------| ----------------------------------------------------|
| GET    | `/medicos`              | Retorna a lista de médicos cadastrados              |
| GET    | `/medicos/{id}`         | Retorna os dados de um médico específico            |
| POST   | `/medicos`              | Cadastra um novo médico na base de dados            |
| PUT    | `/medicos/{id}`         | Atualiza os dados de um médico específico           |
| DELETE | `/medicos/{id}`         | Deleta um médico específico da base de dados         |
| GET    | `/pacientes`            | Retorna a lista de pacientes cadastrados            |
| GET    | `/pacientes/{id}`       | Retorna os dados de um paciente específico          |
| POST   | `/pacientes`            | Cadastra um novo paciente na base de dados          |
| PUT    | `/pacientes/{id}`       | Atualiza os dados de um paciente específico         |
| DELETE | `/pacientes/{id}`       | Deleta um paciente específico da base de dados       |
| GET    | `/consultas`            | Retorna a lista de consultas agendadas              |
| GET    | `/consultas/{id}`       | Retorna os dados de uma consulta específica         |
| POST   | `/consultas`            | Agenda uma nova consulta na base de dados            |
| PUT    | `/consultas/{id}`       | Atualiza os dados de uma consulta específica         |
| DELETE | `/consultas/{id}`       | Cancela uma consulta específica da base de dados     |









