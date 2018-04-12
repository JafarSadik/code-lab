## Groovy Robot DSL

A very simple, internal [Domain Specific Language](https://martinfowler.com/bliki/DomainSpecificLanguage.html) for 
controlling a robot on a grid-based landscape. 

### Build and run 
Minimum requirements: jdk 8, groovy 2.4, gradle 4.5

Run scripts with gradle 'application' plugin
```bash
gradle run -Dscript=script1
gradle run -Dscript=script2
gradle run -Dscript=script3
gradle run -Dscript=script4
```

Build project and run scripts
```bash
gradle build
java -jar build/libs/groovy-dsl.jar script1
java -jar build/libs/groovy-dsl.jar script2
java -jar build/libs/groovy-dsl.jar script3
java -jar build/libs/groovy-dsl.jar script4
```

Read more about [Groovy Domain-Specific Languages](http://docs.groovy-lang.org/docs/latest/html/documentation/core-domain-specific-languages.html)