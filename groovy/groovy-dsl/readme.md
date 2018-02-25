# Groovy Robot DSL

Build project and run scripts
```bash
gradle build
java -jar build/libs/groovy-dsl.jar script1.robot
java -jar build/libs/groovy-dsl.jar script2.robot
java -jar build/libs/groovy-dsl.jar script3.robot
```

Run scripts with gradle 'application' plugin
```bash
gradle run -Dscript=script1.robot
gradle run -Dscript=script2.robot
gradle run -Dscript=script3.robot
```

Read more about [Groovy Domain-Specific Languages](http://docs.groovy-lang.org/docs/latest/html/documentation/core-domain-specific-languages.html)