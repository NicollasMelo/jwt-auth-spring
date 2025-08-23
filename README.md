# JWT Auth Spring

Este projeto é uma **API de autenticação** desenvolvida em **Spring Boot**, utilizando **JWT (JSON Web Token)** para autenticação e segurança de endpoints. 

Ele permite criar usuários, autenticar e proteger rotas com tokens JWT. Ideal para estudar **autenticação moderna** em aplicações Java.

## Funcionalidades

- Cadastro de usuários com senha criptografada (bcrypt)
- Login com geração de JWT
- Validação de tokens para acesso a endpoints protegidos
- Controle de acesso via roles (futuro)
- Configuração de CORS para front-end

## Tecnologias

- Java 21
- Spring Boot 3
- Spring Security
- JWT
- Hibernate / JPA
- MySQL (ou qualquer banco configurado via application.properties)

## Endpoints

- `POST /auth/signup` - Cria um novo usuário
- `POST /auth/login` - Autentica usuário e retorna JWT
- Outras rotas protegidas com JWT (`/user/**`, etc.)

## Documentação

A documentação da API que foi implementada está disponível em:

[Documentação JWT Auth](https://github.com/NicollasMelo/jwt-auth-spring)

## Como executar

1. Clone o repositório:

```bash
git clone git@github.com:NicollasMelo/jwt-auth-spring.git
cd jwt-auth-spring
