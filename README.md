# PetFeliz API

A PetFeliz API é uma aplicação desenvolvida para gerenciar informações relacionadas a usuários, pets e petshops. Ela permite a criação, atualização, busca e exclusão de registros de usuários, pets e petshops, além de fornecer autenticação e autorização para acesso aos recursos.

## Funcionalidades

A API oferece as seguintes funcionalidades:

### Autenticação e Autorização

- Autenticação de usuários através de email e senha.
- Geração de token JWT para usuários autenticados.
- Autorização baseada em roles para acessar os recursos da API.

### Usuários

- Listar todos os usuários cadastrados.
- Buscar um usuário pelo ID.
- Atualizar as informações de um usuário.
- Excluir um usuário pelo ID.
- Registrar um novo usuário.

### Pets

- Listar todos os pets cadastrados.
- Buscar um pet pelo ID.
- Listar todos os pets de um usuário específico pelo ID do usuário.
- Criar um novo pet.
- Atualizar as informações de um pet.
- Excluir um pet pelo ID.

### Petshops

- Listar todos os petshops cadastrados.
- Buscar um petshop pelo ID.
- Criar um novo petshop.
- Atualizar as informações de um petshop.
- Excluir um petshop pelo ID.

## Endpoints

### Autenticação

- **POST /auth/register**: Registra um novo usuário.
```
{
  "name": "Novo Usuário",
  "email": "novo_usuario@example.com",
  "password": "senha123",
  "address": {
    "street": "Rua Exemplo",
    "buildingNumber": "123",
    "district": "Bairro Exemplo",
    "city": "Cidade Exemplo",
    "state": "Estado Exemplo",
    "country": "País Exemplo",
    "postalCode": "12345678",
    "latitude": "123",
    "longitude": "456"
  },
  "phone": "1234567890",
  "gender": "M",
  "role": "USER"
}
```

- **POST /auth/login**: Autentica um usuário por meio de login e gera um token JWT.
```
{
  "email": "usuario@example.com",
  "password": "senha123"
}
```

### Usuários

- **GET /user**: Retorna todos os usuários.
```
- Não requer corpo de requisição
```
- **GET /user/{id}**: Retorna um usuário específico pelo ID.
```
- Não requer corpo de requisição
```
- **PUT /user/{id}**: Atualiza as informações de um usuário pelo ID.
```
{
  "name": "Novo Nome",
  "email": "novo_email@example.com",
  "address": {
    "street": "Nova Rua",
    "buildingNumber": "456",
    "district": "Novo Bairro",
    "city": "Nova Cidade",
    "state": "Novo Estado",
    "country": "Novo País",
    "postalCode": "87654321",
    "latitude": "456",
    "longitude": "789"
  },
  "phone": "9876543210",
  "gender": "F",
  "role": "ADMIN"
}

```
- **DELETE /user/{id}**: Exclui um usuário pelo ID.
```
- Não requer corpo de requisição
```
### Pets

- **GET /pet**: Retorna todos os pets.
```
- Não requer corpo de requisição
```
- **GET /pet/user/{userId}**: Retorna todos os pets de um usuário específico pelo ID do usuário.
```
- Não requer corpo de requisição
```
- **GET /pet/{id}**: Retorna um pet específico pelo ID.
```
- Não requer corpo de requisição
```
- **POST /pet**: Cria um novo pet.
```
{
  "name": "Novo Pet",
  "species": "Cachorro",
  "breed": "Golden Retriever",
  "weight": 20.5,
  "color": "Amarelo",
  "gender": "M",
  "user": {
    "id": 1
  },
  "age": 3
}
```
- **PUT /pet/{id}**: Atualiza as informações de um pet pelo ID.
```
{
  "name": "Novo Nome do Pet",
  "species": "Gato",
  "breed": "Persa",
  "weight": 5.7,
  "color": "Branco",
  "gender": "F",
  "user": {
    "id": 2
  },
  "age": 2
}
```
- **DELETE /pet/{id}**: Exclui um pet pelo ID.
```
- Não requer corpo de requisição
```
### Petshops

- **GET /petshop**: Retorna todos os petshops.
```
- Não requer corpo de requisição
```
- **GET /petshop/{id}**: Retorna um petshop específico pelo ID.
```
- Não requer corpo de requisição
```
- **POST /petshop**: Cria um novo petshop.
```
{
  "name": "Novo Petshop",
  "phone": "987654321",
  "email": "contato@novopetshop.com",
  "openingHours": "Seg-Sex 8:00-18:00",
  "petshopService": ["Banho", "Tosa"],
  "address": {
    "street": "Rua Nova",
    "buildingNumber": "789",
    "district": "Novo Bairro",
    "city": "Nova Cidade",
    "state": "Novo Estado",
    "country": "Novo País",
    "postalCode": "54321678",
    "latitude": "789",
    "longitude": "012"
  },
  "averageRating": 4.5
}
```
- **PUT /petshop/{id}**: Atualiza as informações de um petshop pelo ID.
```
{
  "name": "Novo Nome do Petshop",
  "phone": "123456789",
  "email": "contato@novonomepetshop.com",
  "openingHours": "Seg-Sex 9:00-19:00",
  "petshopService": ["Vacinação", "Hospedagem"],
  "address": {
    "street": "Rua Nova 2",
    "buildingNumber": "456",
    "district": "Novo Bairro 2",
    "city": "Nova Cidade 2",
    "state": "Novo Estado 2",
    "country": "Novo País 2",
    "postalCode": "87654321",
    "latitude": "345",
    "longitude": "678"
  },
  "averageRating": 4.2
}
```
- **DELETE /petshop/{id}**: Exclui um petshop pelo ID.
```
- Não requer corpo de requisição
```
## Tecnologias Utilizadas

A PetFeliz API foi desenvolvida utilizando as seguintes tecnologias:

- Java
- Spring Boot
- Spring Security
- Spring Web
- Hibernate
- H2
- Lombok
- Jakarta Persistence API (JPA)
---
## Estrutura do Projeto

A estrutura do projeto é organizada da seguinte forma:

- **controller**: Contém os controladores responsáveis por receber as requisições HTTP e direcioná-las para os serviços adequados.
- **entity**: Contém as entidades JPA que representam os modelos de dados da aplicação.
- **infra/security**: Contém as classes responsáveis pela configuração de segurança da aplicação, autenticação, autorização e geração de tokens JWT.
- **repository**: Contém as interfaces de repositório que realizam a comunicação com o banco de dados.
- **service**: Contém as classes de serviço que realizam a lógica de negócio da aplicação.
---
## Configuração do Ambiente

Para executar a PetFeliz API localmente, siga as instruções abaixo:

1. Clone o repositório para sua máquina local.
2. Certifique-se de ter o JDK e Maven instalados.
3. Configure as credenciais do H2 no arquivo `application.properties` para testar a aplicação.
4. Execute a aplicação.
5. Acesse a API através da URL base `http://localhost:8080`.
---
## Autor

<a href="https://github.com/rafael-isidro">
    <img style="border-radius: 50%;" src="https://avatars.githubusercontent.com/u/118776145?v=4" width="100px;" alt="Foto de perfil - Rafael Isidro"/>
    <br />
    <sub><b>Rafael Santos Isidro</b></sub>
</a> 
<br />
