package presentation.dialogs;

import presentation.userCommunicationConfig.CommunicationProvider;
import presentation.userCommunicationConfig.IPrinter;
import presentation.userCommunicationConfig.IScanner;

public abstract class BaseDialog {
    public static IPrinter printer;
    public static IScanner scanner;

    static {
        printer = CommunicationProvider.printer;
        scanner = CommunicationProvider.scanner;
    }

    public BaseDialog() {

    }

    public BaseDialog(IPrinter printer, IScanner scanner) {
        this.printer = printer;
        this.scanner = scanner;
    }

    public static void done() {
        printer.println("Done.");
    }

    public static void printOnExit() {
        printer.println("Shutting down...");
        printer.println("Finish");
    }

    public static void startMessage() {
        printer.println("Application has started");
    }
}
