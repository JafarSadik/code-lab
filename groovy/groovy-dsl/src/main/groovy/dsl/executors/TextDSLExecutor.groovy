package dsl.executors

import org.codehaus.groovy.control.CompilerConfiguration

trait TextDSLExecutor {
    @Lazy
    private def shell = new GroovyShell(withDelegatingScript())

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
