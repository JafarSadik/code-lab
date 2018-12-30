package kata.consolecanvas

import kata.consolecanvas.editor.Editor
import kata.consolecanvas.editor.EditorContext
import kata.consolecanvas.editor.commands.CommandParser

fun main(args: Array<String>) {
    val editorContext = EditorContext()
    val commandParser = CommandParser(editorContext)
    val editor = Editor(editorContext, commandParser)

    editor.run()
}