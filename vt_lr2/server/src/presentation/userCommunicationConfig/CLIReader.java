package presentation.userCommunicationConfig;

import java.util.Scanner;

public class CLIReader implements IScanner {
    Scanner scan = new Scanner(System.in);

    @Override
    public String nextLine() {
        return scan.nextLine();
    }

    @Override
    public int nextInt() {
        return Integer.parseInt(nextLine());
    }
}
