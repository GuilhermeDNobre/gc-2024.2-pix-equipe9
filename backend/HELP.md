# Começando

<p align="center">
<a href="#contributing">Contribuindo</a> •
 <a href="#documentation">Documentação de referência</a> • 
 <a href="#guides">Guias</a> 
</p>

<h3 id="contributing">Como Contribuir para este Projeto</h3>
Este projeto é uma [API Rest](https://www.ibm.com/br-pt/topics/rest-apis)  desenvolvida com Spring Boot, utilizando Gradle como gerenciador de dependências, PostgreSQL como banco de dados e Flyway para controle de migrações.

#### Siga as instruções abaixo para contribuir:

#### Requisitos
Certifique-se de ter os seguintes requisitos instalados:

- Java 17
- PostgreSQL 16+
- Git para controle de versão

#### Passo a Passo para Contribuir
1. Faça o clone do Repositório

```bash
git clone https://github.com/silobocarvalho/gc-2024.2-pix-equipe9
```
2. entre no diretorio do projeto
```bash
cd gc-2024.2-pix-equipe9 
```
3. Atualize os indices com fecth
```bash
git fetch
```
4. entre na branch develop
```bash
git checkout develop 
```

5. Configure as Variáveis de Ambiente

```env
DB_NAME=jdbc:nome_do_banco_de_dados
DB_USERNAME=seu_usuario_do_banco
DB_PASSWORD=sua_senha_do_banco
SPRING_PROFILES_ACTIVE=dev
```

6. Rode o Projeto Localmente

6. Crie sua Contribuição
   Siga os padrões de codificação definidos pelo projeto.
   Documente novas funcionalidades no arquivo README.md.
7. Faça um Pull Request
   Crie um branch com um nome descritivo para sua contribuição:

```bash
git checkout -b feature/nome-da-feature
```
Faça commit das alterações:

```bash
git add .
git commit -m "Descrição clara da alteração"
```
> **⚠️**: adicione commits a cada alteração relevante:

Após finalizar envie para o repositorio remoto
```bash
git push origin feature/nome-da-feature
```
Abra um Pull Request da sua branch para a branch develop explicando o que foi alterado.

8. Revise Feedbacks
   Acompanhe os comentários no Pull Request e faça ajustes necessários.
   Garanta que as verificações automáticas (se configuradas) passem antes de finalizar.

<h3 id="documentation"> Reference Documentation</h3>
For further reference, please consider the following sections:

* [O que é Api Rest](https://www.ibm.com/br-pt/topics/rest-apis)
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.5/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.5/gradle-plugin/packaging-oci-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.3.5/reference/web/servlet.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.3.5/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Flyway Migration](https://docs.spring.io/spring-boot/3.3.5/how-to/data-initialization.html#howto.data-initialization.migration-tool.flyway)
* [Spring Security](https://docs.spring.io/spring-boot/3.3.5/reference/web/spring-security.html)

<h3 id="guides">Guias</h3>
The following guides illustrate how to use some features concretely:

* [Construindo um RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)

