package kata.consolecanvas.editor.commands.builders

import kata.consolecanvas.editor.EditorContext
import kata.consolecanvas.editor.commands.Command

abstract class CommandBuilder(pattern: String) {
    private val regex: Regex = pattern.toRegex()

    fun canHandle(command: String) = regex.matches(command)

    fun build(editorContext: EditorContext, command: String): Command = build(editorContext, regex.find(command)!!.groupValues)

    protected abstract fun build(editorContext: EditorContext, regexGroups: List<String>): Command
}