package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.editor.commands.QuitCommand
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class QuitCommandBuilderTest : BaseCommandBuilderTest() {

    override fun commandBuilder() = QuitCommandBuilder

    @Test
    fun `should verify if the command can be handled`() {
        assertCanHandleCommands(
                "Q"
        )

        assertCannotHandleCommands(
                "Q 1",
                "Q 1 2 3",
                "?"
        )
    }

    @Test
    fun `should build a command given valid syntax`() {
        assertThat(buildCommand("Q"))
                .isInstanceOf(QuitCommand::class.java)
    }
}