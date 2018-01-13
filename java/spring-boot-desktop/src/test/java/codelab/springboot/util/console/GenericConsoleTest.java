package codelab.springboot.util.console;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class GenericConsoleTest {
    RecordingPrintStream printStream = RecordingPrintStream.record();

    @Test
    public void printlnTest() {
        // After printing few lines to generic console
        GenericConsole console = new GenericConsole(printStream);
        console.println("Date: 12:11:00, 11 November 2017");
        console.println("btcusd price: 12.10, volume: 2123");
        console.println();

        //We expect them to be printed to the supporting PrintStream
        List<String> outputLines = printStream.getLines();
        assertThat(outputLines).hasSize(3);
        assertThat(outputLines.get(0)).isEqualTo("Date: 12:11:00, 11 November 2017");
        assertThat(outputLines.get(1)).isEqualTo("btcusd price: 12.10, volume: 2123");
        assertThat(outputLines.get(2)).isEmpty();
    }
}