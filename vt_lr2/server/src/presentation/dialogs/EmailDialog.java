package presentation.dialogs;

public class EmailDialog extends BaseDialog{
    public static void printAfterEmailSende(String userMail, String message) {
        printer.get().println("Email has been sent to " + userMail + " with message: " + message);
    }
}
