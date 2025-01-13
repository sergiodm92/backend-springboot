# OrderNow App

## Description
This project is a backend application developed in Java 21 using Spring Boot, Hibernate, and Maven. It is structured in a modular and scalable way, divided into multiple business modules for better organization and maintainability.

## Project Structure
```
project
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── ordernow
│       │           └── api
│       │               ├── Application.java
│       │               ├── config
│       │               │   └── DatabaseConfig.java
│       │               ├── core
│       │               │   ├── exceptions
│       │               │   │   ├── CustomException.java
│       │               │   │   ├── GlobalExceptionHandler.java
│       │               │   └── utils
│       │               │       ├── Constants.java
│       │               │       └── PasswordUtils.java
│       │               ├── modules
│       │               │   ├── auth
│       │               │   │   ├── controllers
│       │               │   │   │   └── AuthController.java
│       │               │   │   ├── services
│       │               │   │   │   ├── AuthService.java
│       │               │   │   │   └── JwtService.java
│       │               │   │   ├── dto
│       │               │   │   │   ├── LoginRequestDTO.java
│       │               │   │   │   ├── LoginResponseDTO.java
│       │               │   │   │   └── RegisterUserDTO.java
│       │               │   │   ├── entities
│       │               │   │   │   └── AuthUser.java
│       │               │   │   ├── repositories
│       │               │   │   │   └── AuthUserRepository.java
│       │               │   │   └── utils
│       │               │   │       └── JwtTokenUtil.java
│       │               │   ├── users
│       │               │   │   ├── controllers
│       │               │   │   │   └── UserController.java
│       │               │   │   ├── services
│       │               │   │   │   └── UserService.java
│       │               │   │   ├── dto
│       │               │   │   │   ├── UserDTO.java
│       │               │   │   │   └── UpdateUserDTO.java
│       │               │   │   ├── entities
│       │               │   │   │   └── User.java
│       │               │   │   ├── repositories
│       │               │   │   │   └── UserRepository.java
│       │               │   │   └── mappers
│       │               │   │       └── UserMapper.java
│       │               │   └── roles
│       │               │       ├── controllers
│       │               │       │   └── RoleController.java
│       │               │       ├── services
│       │               │       │   └── RoleService.java
│       │               │       ├── dto
│       │               │       │   ├── RoleDTO.java
│       │               │       │   └── UpdateRoleDTO.java
│       │               │       ├── entities
│       │               │       │   └── Role.java
│       │               │       ├── repositories
│       │               │       │   └── RoleRepository.java
│       │               │       └── mappers
│       │               │           └── RoleMapper.java
│       │               └── security
│       │                   ├── SecurityConfig.java
│       │                   ├── JwtAuthenticationFilter.java
│       │                   ├── JwtAuthorizationFilter.java
│       │                   └── CustomAuthenticationEntryPoint.java
│       └── resources
│           ├── application.properties
│           ├── application-dev.properties
│           ├── application-prod.properties
│           └── db
│               └── migrations
│                   ├── V1__Initial_Schema.sql
│                   └── V2__Add_Roles.sql
├── pom.xml
└── README.md

```

## Modules
1. **Auth**: Handles authentication and JWT token management.
2. **Users**: Manages user data with basic CRUD operations.
3. **Roles**: Manages roles and their associations with users.

## Technologies Used
- Java 21
- Spring Boot
- Hibernate
- PostgreSQL
- Maven
- JWT
- Flyway/Liquibase for database migrations

## Setup Instructions
1. Clone the repository:
   ```
   git clone <repository-url>
   ```
2. Navigate to the project directory:
   ```
   cd project
   ```
3. Update the `application.properties` file with your PostgreSQL database credentials.
4. Build the project using Maven:
   ```
   mvn clean install
   ```
5. Run the application:
   ```
   mvn spring-boot:run
   ```

## Usage
- Access the API endpoints for authentication, user management, and role management as defined in the respective controllers.

## License
This project is licensed under the MIT License.
