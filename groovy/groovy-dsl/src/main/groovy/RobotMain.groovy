import dsl.RobotDslEngine

class RobotMain {
    static void main(String[] args) {
        def script = new File(args[0]).getText()
        new RobotDslEngine().execute(script);
    }
}
