package dsl

class Robot {
    int x, y

    def moveTo(x, y) {
        this.x = x
        this.y = y
    }

    def moveUp(distance) {
        this.y -= distance
    }

    def moveDown(distance) {
        this.y += distance
    }

    def moveLeft(distance) {
        this.x -= distance
    }

    def moveRight(distance) {
        this.x += distance
    }

    def assertLocation(x, y) {
        assert x == this.x && y == this.y
    }

    def times(n, Closure closure) {
        n.times {
            closure()
        }
    }
}
