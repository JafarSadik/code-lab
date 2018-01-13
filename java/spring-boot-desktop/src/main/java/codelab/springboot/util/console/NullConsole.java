package codelab.springboot.util.console;

/**
 * A {@link Console} implementation utilizing Null Object pattern.
 * Any call made to this object is silently ignored.
 */
public class NullConsole implements Console {
    @Override
    public void println() {
        // does nothing
    }

    @Override
    public void println(String line) {
        // does nothing
    }
}
