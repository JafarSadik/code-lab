import domain.Robot
import dsl.RobotDSL

class Main {
    static void main(String[] args) {
        def robot = new Robot()
        def robotDSL = new RobotDSL(robot)
        robotDSL.execute(loadScript(args));
    }

    private static String loadScript(String[] args) {
        new File(args[0]).getText()
    }
}
