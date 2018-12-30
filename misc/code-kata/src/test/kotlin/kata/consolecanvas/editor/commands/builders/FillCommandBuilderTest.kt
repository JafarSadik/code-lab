package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.editor.commands.FillCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FillCommandBuilderTest : BaseCommandBuilderTest() {

    override fun commandBuilder() = FillCommandBuilder

    @Test
    fun `should verify if the command can be handled`() {
        assertCanHandleCommands(
                "B 0 0 o",
                "B 5 4 x",
                "B 12 32 _"
        )

        assertCannotHandleCommands(
                "B",
                "B 1 1",
                "? 1 1 x"
        )
    }

    @Test
    fun `should build a command given valid syntax`() {
        assertThat(buildCommand("B 5 5 x"))
                .isInstanceOf(FillCommand::class.java)
    }
}