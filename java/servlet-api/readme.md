# Servlet API examples
This project presents a basic usage of Servlet API:
1. servlets
2. filters
3. listeners
4. sessions, cookies
5. jsp, jspx, standard actions, jstl, custom tags
6. container based security
7. log4j logging

# Build and run
Minimum requirement is JDK 8 and Maven 3.
 
Build the project: 
```
mvn clean install
```

Choose any submodule and run it with jetty container. Maven will automatically download Jetty 9 with all its dependencies.
```
cd servlets/simple-servlet
mvn jetty:run
```

By default [jetty listens on port 8080](http://localhost:8080). 



