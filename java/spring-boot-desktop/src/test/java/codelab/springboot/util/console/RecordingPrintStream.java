package codelab.springboot.util.console;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * A test utility that extends {@link PrintStream} and makes it possible to record all lines
 * written to an {@link OutputStream}. This is a good alternative to mocking everything.
 */
public class RecordingPrintStream extends PrintStream {
    private volatile ByteArrayOutputStream outputStream;

    /**
     * Creates a {@link PrintStream} object that records everything in {@link ByteArrayOutputStream}.
     * Use method getLines() to retrieve all lines written to this stream.
     */
    public static RecordingPrintStream record() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        RecordingPrintStream printStream = new RecordingPrintStream(new PrintStream(outputStream));
        printStream.outputStream = outputStream;
        return printStream;
    }

    /**
     * Retrieves all lines written to the underlying {@link ByteArrayOutputStream}
     */
    public List<String> getLines() {
        List<String> lines = new LinkedList<>();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        return lines;
    }

    /**
     * Use record() methods to construct {@link RecordingPrintStream}
     */
    private RecordingPrintStream(OutputStream out) {
        super(out);
    }
}
