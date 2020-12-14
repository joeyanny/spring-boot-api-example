# Spring Boot API Example
 
 Example API to show use of Spring Boot and H2 in-memory database.
 
## Running application
 Run the application using the Spring Boot Maven plugin to run the code:
 
 ```
 mvn spring-boot:run
 ```
 
 **Note:** The `groupId` field in the `pom.xml` will need to be populated for the build to be successful.
 
## Swagger API documentation
When the API is running visit `http://localhost:8080/swagger-ui/` to see the API documentation rendered using Swagger.
 
## Testing application
 Make an API request to the following URLs to test the application:
 
 **Add new user**
 
 ```
 curl -X POST http://localhost:8080/users -H 'Content-Type: application/json' -d '{"firstName": "test", "lastName": "user"}'
 ```
 
 **Get all users**
 
 ```
 curl http://localhost:8080/users
 ```
 
 **Get one user**
 
 ```
 curl http://localhost:8080/users/1
 ```
 
 **Update existing user**
 
 ```
 curl -X PUT http://localhost:8080/users/1 -H 'Content-Type: application/json' -d '{"firstName": "test-updated", "lastName": "user-updated"}'
 ```

 **Delete user**
 
 ```
 curl -X DELETE http://localhost:8080/users/1
 ```