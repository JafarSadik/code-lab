package kata.consolecanvas.editor.commands

/**
 * A base interface for all commands
 */
interface Command {
    /**
     * Executes the command that may depend on an encapsulated state.
     * Undoable commands need to store data necessary to undo the change.
     */
    fun execute()
}

/**
 * A base interface for all undoable commands
 */
interface UndoableCommand : Command {
    /**
     * Undoes the previously executed command
     */
    fun undo()
}