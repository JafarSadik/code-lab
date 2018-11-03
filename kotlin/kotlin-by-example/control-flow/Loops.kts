// Loops are statements: for, while, do-while. They work similarly to most languages.
val cakes = listOf("carrot", "cheese", "chocolate")
println("List of cakes:")
for (cake in cakes) {
    println("\t- $cake")
}

var counter = 0
while (counter < 5) {
    //do sth
    counter++
}

do {
    // do sth
    counter--;
} while (counter > 0)


// Iterator operator should provide an implementation of iterator
data class Animal(val name: String)

class Zoo(val animals: List<Animal>) {
    operator fun iterator(): Iterator<Animal> {
        return animals.iterator();
    }
}

val zoo = Zoo(listOf(Animal("zebra"), Animal("lion")))
println("Animals in the zoo:")
for (animal in zoo) {
    println("\t- $animal")
}

// Iterators can be declared on existing types as an extension function.
// The following example implements an iterator with anonymous class
class Park(val animals: List<Animal>)

operator fun Park.iterator(): Iterator<Animal> {
    val it = animals.iterator()

    return object: Iterator<Animal> {
        override fun hasNext(): Boolean {
            return it.hasNext()
        }

        override fun next(): Animal {
            return it.next()
        }
    }
}

val park = Park(listOf(Animal("duck"), Animal("pigeon")))
println("Animals in the park:")
for (animal in park) {
    println("\t- $animal")
}
