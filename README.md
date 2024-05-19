# Sistema de Gestão de Chamados SheHelps

## Descrição do Projeto

Este é um sistema de gestão de chamados desenvolvido em Java utilizando o Spring Boot. O sistema permite que usuários registrem e gerenciem chamados técnicos, enquanto técnicos e administradores podem visualizar e editar os chamados. O sistema implementa funcionalidades de autenticação, autorização, e diferentes níveis de acesso com base nos tipos de usuário.

## Funcionalidades

- **Registro de Usuário:** Permite que novos usuários se registrem no sistema.
- **Autenticação:** Autenticação de usuários utilizando Spring Security.
- **Gerenciamento de Chamados:** Usuários podem criar, editar e excluir chamados.
- **Visualização de Chamados:** Técnicos e administradores podem visualizar e gerenciar chamados com base no status e no setor de direcionamento.
- **Níveis de Acesso:** Diferentes funcionalidades disponíveis para usuários, técnicos e administradores.

## Tecnologias Utilizadas

- **Java**
- **Spring Boot**
- **Spring Security**
- **Thymeleaf**
- **Hibernate / JPA**
- **MySQL**
- **HTML / CSS**

## Requisitos de Instalação

- **Java**
- **Maven**
- **MySQL**

## Configuração do Projeto

1. Clone o repositório para sua máquina local:
    ```bash
    git clone https://github.com/manuscruz/shehelps.git
    ```

2. Navegue até o diretório do projeto:
    ```bash
    cd shehelps
    ```

3. Configure o banco de dados MySQL:
    - Crie um banco de dados chamado `shehelps`.
    - Atualize as configurações do banco de dados no arquivo `application.properties`:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/shehelps
      spring.datasource.username=seu-usuario
      spring.datasource.password=sua-senha
      ```

4. Compile e execute o projeto usando Maven:
    ```bash
    mvn spring-boot:run
    ```

5. Acesse a aplicação em seu navegador:
    ```
    http://localhost:8080
    ```

## Estrutura do Projeto

### Diretórios Principais

- **src/main/java/com/soulcode/demo/controller**: Contém os controladores responsáveis por manipular as requisições HTTP.
- **src/main/java/com/soulcode/demo/models**: Contém as classes de modelo (entidades).
- **src/main/java/com/soulcode/demo/repositories**: Contém as interfaces de repositório que estendem `JpaRepository`.
- **src/main/java/com/soulcode/demo/service**: Contém as classes de serviço que implementam a lógica de negócio.
- **src/main/resources/templates**: Contém os arquivos de template Thymeleaf para renderização das páginas HTML.
- **src/main/resources/static**: Contém arquivos estáticos como CSS e JavaScript.

## Endpoints Principais

- **/login**: Página de login.
- **/cadastro**: Página de registro de novos usuários.
- **/chamado**: Página de criação de novos chamados (acessível apenas para usuários logados).
- **/user-tickets**: Página que exibe os chamados aguardando técnico do usuário logado.
- **/edit-user-ticket/{id}**: Página de edição de chamados do usuário logado.

