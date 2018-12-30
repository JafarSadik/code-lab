package kata.consolecanvas.editor.commands

import kata.consolecanvas.editor.EditorContext
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class UndoCommandTest {

    @Mock
    lateinit var previousCommandMock: UndoableCommand

    @Spy
    lateinit var commandHistory: Stack<UndoableCommand>

    @Test
    fun `Undo command should undo previously executed command`() {
        UndoCommand(EditorContext(commandHistory = stackOf(previousCommandMock))).execute()
        verify(previousCommandMock).undo()
    }

    @Test
    fun `Undo command should do nothing when command history is empty`() {
        UndoCommand(EditorContext(commandHistory = commandHistory)).execute()
        verify(commandHistory).isNotEmpty()
        verifyNoMoreInteractions(commandHistory)
    }

    private fun <T> stackOf(vararg elements: T): Stack<T> {
        val stack = Stack<T>()
        elements.forEach { stack.push(it) }
        return stack
    }
}