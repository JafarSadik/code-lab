# Spring Boot with Groovy
This example demonstrates how to create a simple REST endpoint using 
spring boot with groovy and gradle.

# Build and run
Run the application using 'run' task from 'application' plugin.
```
gradle run
```

You might want to generate a self-contained application with all necessary 
dependencies and an embedded a servlet container by using spring-boot gradle plugin. 
By default the plugin embeds Tomcat but with a bit of configuration, it's possible to use Jetty.
```
gradle build
java -jar build/libs/spring-boot.jar
```

And yet another way to run this application.
```
gradle bootRun
```

Server response for [http://localhost:8080/](http://localhost:8080/)
```json
{
  "version" : "1.0",
  "description" : "index page",
  "author" : {
    "name" : "Jafar Sadik",
    "email" : "dzafar.sadik@gmail.com"
    }
}
```