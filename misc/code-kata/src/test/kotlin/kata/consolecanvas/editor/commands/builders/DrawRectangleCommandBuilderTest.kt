package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.editor.commands.DrawRectangleCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DrawRectangleCommandBuilderTest : BaseCommandBuilderTest() {

    override fun commandBuilder() = DrawRectCommandBuilder()

    @Test
    fun `should verify if the command can be handled`() {
        assertCanHandleCommands(
                "R 0 0 0 0",
                "R 0 0 10 5",
                "R 5 3 1 1"
        )

        assertCannotHandleCommands(
                "R",
                "R 1 1",
                "R 1 1 4",
                "R 1 1 2 2 3 3",
                "? 1 1 2 2 3"
        )
    }

    @Test
    fun `should build a command given valid syntax`() {
        assertThat(buildCommand("R 1 1 10 10"))
                .isInstanceOf(DrawRectangleCommand::class.java)
    }
}