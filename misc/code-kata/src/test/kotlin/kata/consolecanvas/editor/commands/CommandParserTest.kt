package kata.consolecanvas.editor.commands

import kata.consolecanvas.canvas.Canvas2D
import kata.consolecanvas.canvas.Point
import kata.consolecanvas.editor.EditorContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CommandParserTest {
    private val activeCanvas = Canvas2D(20, 20)
    private val editorContext = EditorContext(activeCanvas)
    private val commandParser = CommandParser(editorContext)

    @Test
    fun `should parse QuitCommand`() {
        assertThat(commandParser.parse("Q")).isInstanceOf(QuitCommand::class.java)
    }

    @Test
    fun `should parse NewCommand`() {
        assertThat(commandParser.parse("C 10 5")).isInstanceOf(NewCommand::class.java)
                .isEqualTo(NewCommand(editorContext, 10, 5))
    }

    @Test
    fun `should parse FillCommand`() {
        assertThat(commandParser.parse("B 1 2 o")).isInstanceOf(FillCommand::class.java)
                .isEqualTo(FillCommand(activeCanvas, Point(1, 2), 'o'))
    }

    @Test
    fun `should parse DrawLineCommand`() {
        assertThat(commandParser.parse("L 5 2 5 10")).isInstanceOf(DrawLineCommand::class.java)
                .isEqualTo(DrawLineCommand(activeCanvas, Point(5, 2), Point(5, 10)))
    }

    @Test
    fun `should parse DrawRectCommand`() {
        assertThat(commandParser.parse("R 1 2 9 7")).isInstanceOf(DrawRectangleCommand::class.java)
                .isEqualTo(DrawRectangleCommand(activeCanvas, Point(1, 2), Point(9, 7)))
    }

    @Test
    fun `should fallback to UnknownCommand when command not known`() {
        assertThat(commandParser.parse("? 1 2")).isInstanceOf(UnknownCommand::class.java)
        assertThat(commandParser.parse("X 1 2")).isInstanceOf(UnknownCommand::class.java)
    }

    @Test
    fun `should fallback to UnknownCommand for an invalid syntax`() {
        assertThat(commandParser.parse("C 2")).isInstanceOf(UnknownCommand::class.java)
        assertThat(commandParser.parse("B 1")).isInstanceOf(UnknownCommand::class.java)
        assertThat(commandParser.parse("L 1 1")).isInstanceOf(UnknownCommand::class.java)
        assertThat(commandParser.parse("R 2 1")).isInstanceOf(UnknownCommand::class.java)
    }

    @Test
    fun `should parse ClearCommand`() {
        assertThat(commandParser.parse("C")).isInstanceOf(ClearCommand::class.java);
    }
}