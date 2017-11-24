package presentation.userCommunicationConfig;

public class CommunicationProvider {
    public static IPrinter printer;
    public static IScanner scanner;

    static {
        printer = new CLIPrinter();
        scanner = new CLIReader();
    }
}
