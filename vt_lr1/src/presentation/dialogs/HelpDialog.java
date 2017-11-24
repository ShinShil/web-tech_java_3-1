package presentation.dialogs;

public class HelpDialog {
    public static void printCommands(String[] commands) {
        for(String command : commands) {
            System.out.println("\t " + command);
        }
    }
}
