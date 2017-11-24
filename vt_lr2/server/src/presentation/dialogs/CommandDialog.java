package presentation.dialogs;

public class CommandDialog extends BaseDialog {
    public static String getCommand() {
        return scanner.nextLine();
    }

    public static void printWhenNotAllowedCommand() {
        printer.println("You are not allowed to execute this command");
    }

    public static void printUnknowCommand(String command) {
        printer.println("Unknown command: " + command);
        printer.println("Type help for available commands");
    }
}
