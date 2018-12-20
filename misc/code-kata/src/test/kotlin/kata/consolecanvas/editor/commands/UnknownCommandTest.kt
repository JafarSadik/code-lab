package kata.consolecanvas.editor.commands

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.io.PrintStream

@RunWith(MockitoJUnitRunner::class)
class UnknownCommandTest {

    @Mock
    lateinit var outputMock: PrintStream

    @Test
    fun `UnknownCommand should print WARNING to the console`() {
        val unknownCommand = "E 1 1"

        UnknownCommand(unknownCommand, outputMock).execute()
        verify(outputMock).println(eq("Unknown command or invalid syntax"))
        verifyNoMoreInteractions(outputMock)
    }
}