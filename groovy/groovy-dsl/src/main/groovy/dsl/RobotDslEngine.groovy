package dsl

import org.codehaus.groovy.control.CompilerConfiguration

class RobotDslEngine {
    @Lazy
    private def shell = new GroovyShell(new CompilerConfiguration(scriptBaseClass: DelegatingScript.class.name))
    private def robot = new Robot()

    def execute(Closure dslClosure) {
        dslClosure.delegate = robot
        dslClosure()
    }

    def execute(String source) {
        def script = shell.parse(source)
        script.setDelegate(robot)
        script.run();
    }
}
