def printLocation = {println "robot at ($x, $y)"}

// Make use of groovy language syntax
moveTo 10, 10
(1..10).each {
    moveLeft 10
    moveRight 11
    printLocation()
}
assertLocation 20, 10
println("-----------------")

// Or achieve the same using DSL-specific control structure
times(10) {
    moveLeft 10
    moveRight 11
    printLocation()
}
assertLocation 30, 10