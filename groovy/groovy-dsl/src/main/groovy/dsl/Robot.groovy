package dsl

import groovy.transform.AutoClone
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@ToString
@AutoClone
@Canonical
@EqualsAndHashCode
class Robot implements Cloneable {
    int x, y

    def moveTo(int x, int y) {
        this.x = x
        this.y = y
    }

    def moveUp(int distance) {
        this.y -= distance
    }

    def moveDown(int distance) {
        this.y += distance
    }

    def moveLeft(int distance) {
        this.x -= distance
    }

    def moveRight(int distance) {
        this.x += distance
    }

    def assertLocation(int x, int  y) {
        assert x == this.x && y == this.y
    }

    def times(int n, Closure closure) {
        n.times {
            closure()
        }
    }
}
