# Sistema de Gerenciamento de Biblioteca

Sistema web para gerenciar o fluxo de uma biblioteca, substituindo o controle manual e eliminando problemas como falta de rastreio de livros e ausência de alertas para devoluções em atraso.

## Pré-requisitos

- Java 17+
- Maven 3.8+
- MySQL 8+

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
   ```

2. Configure o banco de dados em `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse em `http://localhost:8080`

## Tecnologias

- Java + Spring Web
- Thymeleaf, HTML5 e CSS3
- MySQL

