### README.md

# Projeto Universidade

## Descrição

Este projeto é uma aplicação de gerenciamento universitário desenvolvida com Spring Boot para disciplina de Desenvolvimento de Sistemas Web II do curso de Bacharelado em Tecnologia da Informação da Universidade Federal do Rio Grande do Norte (UFRN). A aplicação permite a gestão de turmas, alunos e professores, incluindo a criação, atualização, listagem e remoção (física e lógica) de registros. Além disso, a aplicação permite a associação de alunos a turmas e professores a disciplinas. A aplicação inclui uma camada de segurança com autenticação e autorização usando Spring Security.

## Estrutura do Projeto

A estrutura do projeto é organizada da seguinte forma:

```
src
└── main
    └── java
        └── com.imd.universidade
            ├── controller
            │   ├── AlunoController.java
            │   ├── AuthenticationController.java
            │   ├── ProfessorController.java
            │   └── TurmaController.java
            ├── DTO
            │   ├── AlunoDTO.java
            │   ├── AuthenticationDTO.java
            │   ├── ProfessorDTO.java
            │   ├── RegisterDTO.java
            │   └── TurmaDTO.java
            ├── enums
            │   ├── Genero.java
            │   └── UserRoles.java
            ├── model
            │   ├── AlunoEntity.java
            │   ├── ProfessorEntity.java
            │   ├── TurmaEntity.java
            │   └── UserEntity.java
            ├── repository
            │   ├── AlunoRepository.java
            │   ├── ProfessorRepository.java
            │   ├── TurmaRepository.java
            │   └── UserRepository.java
            ├── security
            │   └── SecurityConfiguration.java
            ├── service
            │   ├── AlunoService.java
            │   ├── AuthorizationService.java
            │   ├── ProfessorService.java
            │   └── TurmaService.java
            ├── UniversidadeApplication.java
            └── DataLoader.java
```

## Funcionalidades

- **Autenticação e Autorização** 
    - Login de usuários
    - Registro de novos usuários 
    - Autenticação via JWT
    - Controle de acesso baseado em roles (ADMIN e USER)


- **Turmas**
    - Criar uma nova turma
    - Listar todas as turmas
    - Obter uma turma por ID
    - Atualizar uma turma
    - Remover fisicamente uma turma
    - Remover logicamente uma turma (inativar)
    - Matricular alunos em uma turma
    - Desmatricular alunos de uma turma
    - Associar professor a uma turma
    - Desassociar professor de uma turma

- **Alunos**
    - Criar um novo aluno
    - Listar todos os alunos
    - Obter um aluno por ID
    - Atualizar um aluno
    - Remover fisicamente um aluno
    - Remover logicamente um aluno (inativar)

- **Professores**
    - Criar um novo professor
    - Listar todos os professores
    - Obter um professor por ID
    - Atualizar um professor
    - Remover fisicamente um professor
    - Remover logicamente um professor (inativar)

## Requisitos

- Java 11 ou superior
- Maven


## Uso

### Endpoints

#### Autenticação
- POST /auth/login

  - Realiza login de um usuário
  - Exemplo de JSON:
```json
{
"login": "admin",
"password": "admin"
}
```
- POST /auth/register

  - Registra um novo usuário
  - Exemplo de JSON:
```json
  {
  "login": "novoUsuario",
  "password": "senha123",
  "role": "USER"
  }
```
#### Turmas

- **GET** `/turmas`
    - Retorna todas as turmas

- **GET** `/turmas/{id}`
    - Retorna uma turma pelo ID

- **POST** `/turmas`
    - Cria uma nova turma
    - Exemplo de JSON:
      ```json
      {
        "nome": "Linguagem de Programação I",
        "codigo": "IMD0102",
        "ativo": true
      }
      ```

- **PUT** `/turmas`
    - Atualiza uma turma existente
    - Exemplo de JSON:
      ```json
      {
        "id": 1,
        "nome": "Linguagem de Programação II",
        "codigo": "IMD0103",
        "alunos": [1, 2, 3, 4],
        "professorDisciplina": 1,
        "ativo": true
      }
      ```

- **DELETE** `/turmas/{id}`
    - Remove fisicamente uma turma

- **DELETE** `/turmas/inativar/{id}`
    - Remove logicamente uma turma (inativar)

- **POST** `/turmas/matricular?turmaId={turmaId}&alunoId={alunoId}`
    - Matricula um aluno em uma turma

- **POST** `/turmas/desmatricular?turmaId={turmaId}&alunoId={alunoId}`
    - Desmatricula um aluno de uma turma

- **POST** `/turmas/associarProfessor?turmaId={turmaId}&professorId={professorId}`
    - Associa um professor a uma turma

- **POST** `/turmas/desassociarProfessor?turmaId={turmaId}&professorId={professorId}`
    - Desassocia um professor de uma turma

#### Alunos

- **GET** `/alunos`
    - Retorna todos os alunos

- **GET** `/alunos/{id}`
    - Retorna um aluno pelo ID

- **POST** `/alunos`
    - Cria um novo aluno
    - Exemplo de JSON:
      ```json
      {
        "nome": "João da Silva",
        "cpf": "123.456.789-00",
        "matricula": 12345,
        "genero": "MASCULINO",
        "curso": "Engenharia de Computação",
        "dataNascimento": "01/01/2000"
      }
      ```

- **PUT** `/alunos`
    - Atualiza um aluno existente

- **DELETE** `/alunos/{id}`
    - Remove fisicamente um aluno

- **DELETE** `/alunos/inativar/{id}`
    - Remove logicamente um aluno (inativar)

#### Professores

- **GET** `/professores`
    - Retorna todos os professores

- **GET** `/professores/{id}`
    - Retorna um professor pelo ID

- **POST** `/professores`
    - Cria um novo professor
    - Exemplo de JSON:
      ```json
      {
        "nome": "Maria dos Santos",
        "cpf": "987.654.321-00",
        "matricula": 54321,
        "genero": "FEMININO",
        "departamento": "Computação",
        "dataNascimento": "02/02/1980",
        "salario": 8000,
        "disciplinaAssociada": "Programação"
      }
      ```

- **PUT** `/professores`
    - Atualiza um professor existente

- **DELETE** `/professores/{id}`
    - Remove fisicamente um professor

- **DELETE** `/professores/inativar/{id}`
    - Remove logicamente um professor (inativar)

## Testes

Para testar a aplicação, use o Insomnia ou outro software de sua escolha. Os exemplos de requisições estão descritos acima. 


## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

---

Desenvolvido por [Alinne Alessandra](https://github.com/alinnealess).
