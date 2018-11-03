// Named arguments
// As most programming languages Kotlin supports passing arguments to methods and constructors according to their order of definition.
// Kotlin also supports named arguments to allow clearer invocations and avoid mistakes that otherwise wouldn't be detected by the compiler.
fun process(target: String, command: String) = "$target -> $command"

check(process("worker1", "reboot") == "worker1 -> reboot")
check(process("worker1", command = "reboot") == "worker1 -> reboot")
check(process(target = "worker1", command = "reboot") == "worker1 -> reboot")
check(process(command = "reboot", target = "worker1") == "worker1 -> reboot")
