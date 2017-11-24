package presentation.dialogs;

public class HelpDialog extends BaseDialog {
    public static void printCommands(String[] commands) {
        for(String command : commands) {
            printer.println("\t " + command);
        }
    }

    public static void commandsBelongs(String owner) {
        printer.println("Commands for " + owner);
    }
}
