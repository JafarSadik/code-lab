import java.util.concurrent.atomic.AtomicLong
import kotlin.reflect.KProperty
import kotlin.properties.*

// Delegated properties are not backed by a class field and delegate getting and setting to another piece of code.
// Example applications include lazy initialization, caching and configuration.
annotation class Value(val key: String)

object Config: ReadWriteProperty<Any?, Any?>  {
    override operator fun getValue(thisRef: Any?, property: KProperty<*>): Any? {
        println("[GET VALUE] ref = $thisRef, property = ${property.name})")
        return config[configKey(property)]
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Any?) {
        println("[SET VALUE] ref = $thisRef, property = ${property.name}, value = $value)")
        config[configKey(property)] = value
    }

    private var config = mutableMapOf<String, Any?>(
            "admin.email" to "admin@soft.com",
            "admin.name" to "Robert"
    )

    private fun configKey(property: KProperty<*>): String {
        val valueAnnotation = property.annotations.find { it is Value } as Value
        return valueAnnotation.key
    }
}

class NotificationService {
    @Value("admin.email")
    val adminEmail by Config

    @Value("admin.name")
    val adminName by Config

    @Value("admin.salary")
    var adminSalary by Config

}

val service = NotificationService()
check(service.adminEmail == "admin@soft.com")
check(service.adminName == "Robert")
check(service.adminSalary == null)

service.adminSalary = 120000
check(service.adminSalary == 120000)
check(NotificationService().adminSalary == 120000)


object GameTimer: ReadOnlyProperty<Any?, Long> {
    private val startTime = systemTime()

    override operator fun getValue(thisRef: Any?, property: KProperty<*>) = gameTime()

    fun gameTime() = systemTime() - startTime

    fun systemTime() = System.currentTimeMillis()
}

class Game {
    val time: Long by GameTimer

    fun gameLoop() {
        while (time < 500) {
            println(time)
            Thread.sleep(100)
        }
    }
}

Game().gameLoop()

// The Kotlin standard library provides factory methods for several useful kinds of delegates.
// 1. Lazy properties
val lazyValue: Int by lazy {
    println("computed!")
    1234
}
check(lazyValue == 1234)

// 2. Observable properties
class User {
    var name: String by Delegates.observable("<no name>") { prop, old, new ->
        println("$prop $old -> $new")
    }
}

val user = User()
user.name = "first"
user.name = "second"

// 3. Properties backed by a map
class Profile(map: MutableMap<String, Any>) {
    val name: String by map
    var age: Int by map
}

val profile = Profile(mutableMapOf("name" to "John", "age" to 20))
check(profile.name == "John" && profile.age == 20)

profile.age = 21
check(profile.name == "John" && profile.age == 21)

