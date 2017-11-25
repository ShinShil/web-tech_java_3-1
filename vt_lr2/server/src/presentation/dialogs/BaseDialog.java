package presentation.dialogs;

import presentation.userCommunicationConfig.CommunicationProvider;
import presentation.userCommunicationConfig.IPrinter;
import presentation.userCommunicationConfig.IScanner;

public abstract class BaseDialog {
    public static ThreadLocal<IPrinter> printer = new ThreadLocal<>();
    public static ThreadLocal<IScanner> scanner = new ThreadLocal<>();

    static {
        printer.set(CommunicationProvider.printer);
        scanner.set(CommunicationProvider.scanner);
    }

    public BaseDialog() {

    }

    public static void done() {
        printer.get().println("Done.");
    }

    public static void printOnExit() {
        printer.get().println("Shutting down...");
        printer.get().println("Finish");
    }

    public static void startMessage() {
        printer.get().println("ApplicationServer has started");
    }
}
