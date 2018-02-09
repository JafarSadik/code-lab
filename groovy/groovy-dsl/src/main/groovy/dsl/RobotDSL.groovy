package dsl

import org.codehaus.groovy.control.CompilerConfiguration

class RobotDSL {
    @Lazy
    private def shell = new GroovyShell(withDelegatingScript())
    private def robot = new Robot()

    RobotDSL execute(Closure dslClosure) {
        dslClosure.delegate = robot
        dslClosure()
        this
    }

    RobotDSL execute(String source) {
        def script = shell.parse(source)
        script.setDelegate(robot)
        script.run()
        this
    }

    Robot getRobot() {
        return robot.clone()
    }

    private static CompilerConfiguration withDelegatingScript() {
        new CompilerConfiguration(scriptBaseClass: DelegatingScript.class.name)
    }
}
