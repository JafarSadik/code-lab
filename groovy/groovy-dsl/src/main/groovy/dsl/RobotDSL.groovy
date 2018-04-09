package dsl

import domain.Robot
import dsl.syntax.Assertions
import dsl.syntax.Commands
import dsl.syntax.ControlStructures
import dsl.syntax.sugar.SyntacticSugar
import org.codehaus.groovy.control.CompilerConfiguration

class RobotDSL implements Commands, ControlStructures, Assertions, SyntacticSugar {
    @Delegate
    final Robot robot;

    @Lazy
    private def shell = new GroovyShell(withDelegatingScript())

    RobotDSL(Robot robot) {
        this.robot = robot
    }

    def execute(Closure dslClosure) {
        dslClosure.delegate = this
        dslClosure()
        this
    }

    def execute(String source) {
        def script = shell.parse(source)
        script.setDelegate(this)
        script.run()
        this
    }

    private static CompilerConfiguration withDelegatingScript() {
        new CompilerConfiguration(scriptBaseClass: DelegatingScript.class.name)
    }
}
