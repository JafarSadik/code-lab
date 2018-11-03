package hello

/*
 Hello World

 Compile & run:
 > kotlinc HelloWorld.kt
 > kotlin hello.HelloWorldKt

Compile, package & run:
 > kotlinc -d HelloWorld.jar HelloWorld.kt
 > kotlin -cp HelloWorld.jar hello.HelloWorldKt
*/

// The main entry point to kotlin applicaiton is function main.
fun main(args: Array<String>) {
    // A lot of useful functions are imported implicitly
    println("Hello World!")
}