## Groovy Robot DSL

A very simple, internal [Domain Specific Language](https://martinfowler.com/bliki/DomainSpecificLanguage.html) for 
controlling a robot on a grid-based landscape. 

### Build and run 
Minimum requirements: jdk 8, groovy 2.4, gradle 4.5

Run scripts with gradle 'application' plugin
```bash
gradle run -Dscript=examples/script1
gradle run -Dscript=examples/script2
gradle run -Dscript=examples/script3
gradle run -Dscript=examples/script4
```

Build project and run scripts
```bash
gradle build
java -jar build/libs/groovy-dsl.jar examples/script1
java -jar build/libs/groovy-dsl.jar examples/script2
java -jar build/libs/groovy-dsl.jar examples/script3
java -jar build/libs/groovy-dsl.jar examples/script4
```

Read more about [Groovy Domain-Specific Languages](http://docs.groovy-lang.org/docs/latest/html/documentation/core-domain-specific-languages.html)