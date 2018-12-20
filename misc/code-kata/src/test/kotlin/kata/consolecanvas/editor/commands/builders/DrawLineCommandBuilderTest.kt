package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.editor.commands.DrawLineCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class DrawLineCommandBuilderTest : BaseCommandBuilderTest() {

    override fun commandBuilder() = DrawLineCommandBuilder()

    @Test
    fun `should verify if the command can be handled`() {
        assertCanHandleCommands(
                "L 0 0 0 0",
                "L 0 0 10 5",
                "L 5 3 1 1"
        )

        assertCannotHandleCommands(
                "L",
                "L 1 1",
                "L 1 1 2 2 3",
                "? 1 1 2 2 3"
        )
    }

    @Test
    fun `should build a command given valid syntax`() {
        assertThat(buildCommand("L 1 1 10 10"))
                .isInstanceOf(DrawLineCommand::class.java)
    }
}