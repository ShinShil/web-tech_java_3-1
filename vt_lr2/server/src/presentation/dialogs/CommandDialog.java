package presentation.dialogs;

import java.io.IOException;

public class CommandDialog extends BaseDialog {
    public static String getCommand() throws IOException {
        return scanner.get().nextLine();
    }

    public static void printWhenNotAllowedCommand() {
        printer.get().println("You are not allowed to execute this command");
    }

    public static void printUnknowCommand(String command) {
        printer.get().println("Unknown command: " + command);
        printer.get().println("Type help for available commands");
    }
}
