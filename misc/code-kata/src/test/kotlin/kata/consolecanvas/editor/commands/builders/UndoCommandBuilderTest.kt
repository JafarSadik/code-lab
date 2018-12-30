package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.editor.commands.UndoCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class UndoCommandBuilderTest : BaseCommandBuilderTest() {

    override fun commandBuilder() = UndoCommandBuilder

    @Test
    fun `should verify if the command can be handled`() {
        assertCanHandleCommands(
                "U"
        )

        assertCannotHandleCommands(
                "U ?",
                "U 1",
                "U 1 2 3",
                "? 1 1"
        )
    }

    @Test
    fun `should build a command given valid syntax`() {
        assertThat(buildCommand("U"))
                .isInstanceOf(UndoCommand::class.java)
    }
}