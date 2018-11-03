// Kotlin uses == for structural comparision and === for referential comparison.
val authors = setOf("Shakespeare", "Hemingway", "Twain")
val writers = setOf("Twain", "Shakespeare", "Hemingway")

check(authors == writers)
check(!(authors === writers))