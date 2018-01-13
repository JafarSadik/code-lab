## Spring Boot Desktop Application: cryptocurrency price ticker

The example demonstrates basic building blocks of Spring Boot application and how to utilize dependency injection to 
create a testable software. Spring boot has a good support for application configuration, component-scan and wiring, 
auto configuration, REST endpoints and clients, testing, executable archives (JAR, WAR) and more.

The project makes use of Bitfinex REST API to fetch currency pairs and prices.   

### Build and run
Minimum requirement is JDK 8 and Maven 3. Run the following command:
```
cd PROJECT_ROOT_DIR
mvn clean install
cd target
java -jar spring-boot-desktop-0.1.jar
```



