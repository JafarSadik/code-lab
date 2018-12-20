package kata.consolecanvas.editor.commands

import kata.consolecanvas.common.Runtime
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class QuitCommandTest {

    @Mock
    lateinit var runtimeMock: Runtime

    @Test
    fun `Quit command should terminate the application`() {
        QuitCommand(runtimeMock).execute()
        verify(runtimeMock).exit(0)
    }
}