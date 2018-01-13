package codelab.springboot.util.console;

import java.io.PrintStream;

/**
 * A generic implementation of {@link Console} interface that delegates all calls to
 * provided {@link PrintStream} object.
 */
public class GenericConsole implements Console {
    private final PrintStream out;

    public GenericConsole(PrintStream out) {
        this.out = out;
    }

    @Override
    public void println() {
        out.println();
    }

    @Override
    public void println(String line) {
        out.println(line);
    }
}
