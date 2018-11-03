// 'with' can be used to access object members in more concise way, no need to prefix each member with the instance name
data class Configuration(val minThreads: Int, val maxThreads: Int)
val config = Configuration(5, 50)

with(config) {
    check("between $minThreads and $maxThreads threads" == "between 5 and 50 threads")
}
