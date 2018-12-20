package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.editor.commands.NewCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class NewCommandBuilderTest : BaseCommandBuilderTest() {

    override fun commandBuilder() = NewCommandBuilder()

    @Test
    fun `should verify if the command can be handled`() {
        assertCanHandleCommands(
                "C 0 0",
                "C 10 5"
        )

        assertCannotHandleCommands(
                "C",
                "C 1",
                "C 1 2 3",
                "? 1 1"
        )
    }

    @Test
    fun `should build a command given valid syntax`() {
        assertThat(buildCommand("C 10 10"))
                .isInstanceOf(NewCommand::class.java)
    }
}