package presentation.userCommunicationConfig;

import java.io.OutputStream;
import java.io.PrintWriter;

public class SocketWriter implements IPrinter {
    private PrintWriter printWriter;

    public SocketWriter(OutputStream outputStream) {
        printWriter = new PrintWriter(outputStream);
    }

    @Override
    public void print(String line) {
        printWriter.print(line);
    }

    @Override
    public void println(String line) {
        print(line);
        print("\n");
    }
}
