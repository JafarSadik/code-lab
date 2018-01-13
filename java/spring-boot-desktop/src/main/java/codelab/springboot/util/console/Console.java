package codelab.springboot.util.console;

/**
 * Supports console I/O operations.
 * The abstraction helps to decouple an implementation from system-specific calls.
 */
public interface Console {
    /**
     * Prints a single line to the output.
     */
    void println(String line);

    /**
     * Prints empty line to the output
     */
    void println();
}
