import groovy.json.JsonBuilder
import groovy.xml.MarkupBuilder

// register a shutdown hook
addShutdownHook { -> println "Shutdown..." }

// multiline strings that support expression evaluation
def user = 'John'
println """
        ---------------------
            Welcome, $user
        ---------------------
        """.stripIndent();

// default representation for numbers is BigDecimal
def c = 3.2; def d = 0.99999999999999988888888888888888
println "${c + d}, type of number literal is ${c.class}"

// Groovy is dynamically typed language so you don't have to assign type to variable.
// It's also possible to change type of variable
def currentTime = new Date()
currentTime = "Current time is " + currentTime
println currentTime

// Groovy supports explicit types as well. It supports a mix of dynamic and static typed language.
// Groovy runtime can detect if you declared static type and then assigned incorrect type to it.
double value = 3.12321313

// declare a list; toString method output is very readable even for lists within lists
def list = ['simple', 'groovy', 'application']
def listOfLists = [['simple', 'groovy'], 'application']
println list
println listOfLists

// process map keys
def map = [1: 'simple', 2: 'groovy', 3: 'application'];
map.each { it -> println "${it.key}: ${it.value}" }

// lists and maps use square brackets; use colon [:] to distinguish empty map and empty list
def emptyList = []
def emptyMap = [:]

// groovy supports standard index access but it's also possible to reference last
// index by using negative index or referencing sub-array
def languages = ['java', 'groovy', 'scala', 'clojure']
println "Last item: " + languages[-1]
println "First two languages: " + languages[0..1]

// calculating first elements in Fibonacci sequence:
println "First items in Fibonacci sequence: ${fib(1)}, ${fib(2)}, ${fib(3)}, ${fib(4)}, ${fib(5)}, ${fib(6)}, ${fib(7)}, ..."

// When using equals operator '==' groovy invokes equals method so the following are valid statements:
println "1234 == 1234: ${1234 == 1234}; 1234.is(1234): ${1234.is(1234)}"
println "alice == alice: ${'alice' == 'alice'}"

// Groovy beans automatically generate getters, setters and custom constructors like new Node(id: 1)
println "new Node(1) == new Node(1): ${new Node(id: 1) == new Node(id: 1)}; new Node(1).is(new Node(1)): ${new Node(id: 1).is(new Node(id: 1))}"

// Elvis operator '?:'
def val1 = null, val2 = "value"
println "Groovy supports elvis operator; val1: ${val1 ?: 'empty'}, val2: ${val2 ?: 'empty'}"
assert (val1 ?: 'empty') == (val1 ? val1 : 'empty')

// Safety reference operator '?.'
Node node1 = new Node(id: 222), node2 = null
println "Groovy supports safety reference operator; node1: ${node1?.id}; node2: ${node2?.id}"

// Testing simple state machine
println """Simple state machine:
                A.nextState(): ${StateMachine.A.nextState()}
                A.nextState().nextState(): ${StateMachine.A.nextState().nextState()}
                A.nextState().nextState().nextState(): ${StateMachine.A.nextState().nextState().nextState()}"""

// A shortcut for creating arrays
assert (1..11) == [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11].asList()

// Adding elements to list
def numbers = []
numbers.add(1)
numbers << 2
numbers << 3
numbers << 4
numbers.addAll([5, 6])
numbers = numbers + 7
numbers = numbers + [8, 9]
assert numbers == (1..9)

// Removing elements from list
numbers.removeAll([6, 7])
numbers = numbers - 5
numbers = numbers - [3, 4, 8, 9]
assert numbers == [1, 2]
assert 1 in numbers && 2 in numbers

// Iterate over map
def map2 = ['a': 1, 'b': 2, 'c': 3];
map.each { println it.key + " : " + it.value }

// Apply flat map operation on map items; verify expected result using groovy assertion
assert ['a': 1, 'b': 2].collect { key, val -> key + val } == ['a1', 'b2']

// Apply operation on each element of an array
languages = ['java', 'groovy'];
assert languages*.toUpperCase() == ['JAVA', 'GROOVY']
assert languages.collect { it.toUpperCase() } == ['JAVA', 'GROOVY']

// Passing parameters to closure
def sum = { a, b -> a + b }
assert sum(5, 11) == 16
assert [a: 1, b: 2] == { [b: 2, a: 1] }()

// Build a new function by parametrizing existing
def inc = { sum(it, 1) }
assert inc(3) == 4

// Groovy provides built in regular expressions.
//  /regexp/ to define regular expression
//  ==~ to match string against pattern,
//  =~ to create matcher that allows to access regular expression groups
def allowedChars = "[^;:()&^,@\\s]"
def emailRegexp = /${allowedChars}+@${allowedChars}+/
assert 'ds@gmail.com' ==~ emailRegexp
def emailListSeparator = "\\s*[;,:]?\\s*"
def emailListRegex = /^(${emailRegexp}${emailListSeparator})*(${emailRegexp})$/
assert 'ds@gmail.com;as@gmail.com' ==~ emailListRegex
assert 'ds@gmail.com   as@gmail.com' ==~ emailListRegex
assert 'ds@gmail.com    ,   as@gmail.com' ==~ emailListRegex

def emailMatcher = ('ds@gmail.com    ,   as@gmail.com' =~ emailRegexp);
assert emailMatcher.findAll() == ['ds@gmail.com', 'as@gmail.com']

//Support for XML
def xml = new MarkupBuilder();
xml.person(id: 2) {
    name 'John'
    email 'ds@gmail.com'
}
println xml.toString() + "\n"

//Support for JSON
def json = new JsonBuilder();
json.person(id: 3) {
    name 'John'
    email(['ds@gmail.com', 'ds@hotmail.com'])
}
println json.toPrettyString() + "\n"

// Expando class allows to dynamically add methods and fields to instance.
def expando = new Expando()
expando.user = "Norvig"
expando.email = "norvig@gmail.com"
expando.userInfo = { -> delegate.user + ': ' + delegate.email }
assert expando.userInfo() == 'Norvig: norvig@gmail.com'

// Groovy supports missing fields and methods
def o = new MissingFieldsAndMethods()
assert o.field1 == 1    //field exists
assert o.field2 == '2'  //field exists
assert o.field3 == [3]  //field exists
assert !o.field4        //field missing
assert !o.field5        //field missing
assert !o.field6        //field missing
assert o.methodA()      //method exists
assert o.methodB()      //method exists
assert !o.methodC()     //method missing
assert !o.methodD()     //method missing
assert o.missingProperties == ['field4', 'field5', 'field6']
assert o.missingMethods == ['methodC', 'methodD']

String.metaClass.wrapper = { -> "::" + delegate + "::" }
assert "abc".wrapper() == "::abc::"

// Function to calculate Fibonacci sequence value at position N.
// Groovy keeps code concise by: not requiring public keyword, end-line semicolons or return statements
int fib(int N) {
    N <= 2 ? N : fib(N - 1) + fib(N - 2)
}

class Node {
    private int id;

    boolean equals(o) {
        this.id == o.id
    }

    int hashCode() {
        return 0
    }
}

enum StateMachine {
    A{ StateMachine nextState() { B }},
    B{ StateMachine nextState() { C }},
    C{ StateMachine nextState() { A }}
    abstract StateMachine nextState()
}

class MissingFieldsAndMethods {
    def field1 = 1
    def field2 = '2'
    def field3 = [3]
    def missingProperties = []
    def missingMethods = []

    def methodA() {
        'methodA'
    }

    def methodB() {
        'methodB'
    }

    def propertyMissing(String name) {
        missingProperties << name
        null
    }

    def methodMissing(String name, args) {
        missingMethods << name
        null
    }
}