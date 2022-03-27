# account-api
an Api create an account and make transactions
JUnit test and as well as integration tests are available.

### Tech Stack

- Java 11
- Spring Boot
- Spring Data JPA
- Restful API
- OpenAPI documentation
- H2 In memory database
- JUnit 5
- Maven
### Run & Build

#### Maven
___
*$PORT: 8080*
```ssh
$ cd /account-api
$ mvn clean install
$ mvn spring-boot:run

### Swagger UI will be run on this url
`http://localhost:${PORT}/swagger-ui.html`
