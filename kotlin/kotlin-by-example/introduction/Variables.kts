// Variables
// Kotlin has a powerful type inference. Type declaration on variables is optional.
// Immutability is not enforced though it's recommended. Whenever possible declare values(val) instead of variables(var).
var a: String = "some string"
val b: Int = 1
val c = 3

// Null safety
// In an effort to protect against NullPointerException, variable types in Kotlin don't allow assignment of null
// For a variable to hold null it must be explicitly specified as nullable.
var nullable: String? = "this can be set to null"
nullable = null
