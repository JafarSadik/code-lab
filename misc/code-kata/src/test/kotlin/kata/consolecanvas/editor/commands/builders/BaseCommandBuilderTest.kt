package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.canvas.Canvas2D
import kata.consolecanvas.editor.EditorContext
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue

abstract class BaseCommandBuilderTest {

    private val anyEditorContext = EditorContext(Canvas2D(10, 10))

    abstract fun commandBuilder(): CommandBuilder

    fun assertCanHandleCommands(vararg validCommands: String) {
        validCommands.forEach { assertTrue(commandBuilder().canHandle(it)) }
    }

    fun assertCannotHandleCommands(vararg invalidCommands: String) {
        invalidCommands.forEach { assertFalse(commandBuilder().canHandle(it)) }
    }

    fun buildCommand(command: String) = commandBuilder().build(anyEditorContext, command)
}