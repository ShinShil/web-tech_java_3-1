package presentation.userCommunicationConfig;

public class CLIPrinter implements IPrinter {
    @Override
    public void print(String line) {
        System.out.print(line);
    }

    @Override
    public void println(String line) {
        print(line);
        System.out.println("");
    }
}
