// Map declaration and access
val map = mapOf<String, Any>("key" to 1)
check("ann" to 42 == Pair("ann", 42))
check(map["key"] == 1)
check(map.getValue("key") == 1)
check(map["unknown"] == null)

val mapWithDefault = map.withDefault { "** $it **" }
check(mapWithDefault["key"] == 1)
check(mapWithDefault.getValue("unknown") == "** unknown **")


