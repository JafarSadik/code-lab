// Lambda functions are a simple way to create functions ad-hoc. They can be very concise thanks to type inference and the implicit it variable.
val upperCase: List<(String) -> String> = listOf(
        {str:String -> str.toUpperCase()},
        {str -> str.toUpperCase()},
        String::toUpperCase,
        {it.toUpperCase()}
)

upperCase.forEach{ fn ->
    check(fn("world") == "WORLD")
}