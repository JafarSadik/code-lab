package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.editor.commands.ClearCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ClearCommandBuilderTest : BaseCommandBuilderTest() {

    override fun commandBuilder() = ClearCommandBuilder

    @Test
    fun `should verify if the command can be handled`() {
        assertCanHandleCommands(
                "C"
        )

        assertCannotHandleCommands(
                "C 0",
                "C 1 0",
                "C 1 2 3",
                "? 1 1"
        )
    }

    @Test
    fun `should build a command given valid syntax`() {
        assertThat(buildCommand("C"))
                .isInstanceOf(ClearCommand::class.java)
    }
}