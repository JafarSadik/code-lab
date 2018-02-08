import dsl.RobotDslEngine

def engine = new RobotDslEngine()

engine.with {
    execute {
        moveTo 10, 10
        assertLocation 10, 10
    }

    execute {
        moveUp 4
        moveDown 3
        moveLeft 1
        moveRight 10
        assertLocation 19, 9
    }

    execute """
        moveTo 0, 0
        moveDown 10
        moveRight 10
        assertLocation 10, 10
    """
/*
    // External DSL (file)
    println "\nOutput from external DSL (file):"
    def script = new File("script2.roboscript").getText();
    execute(script)
    assertLocation 30, 10*/
}