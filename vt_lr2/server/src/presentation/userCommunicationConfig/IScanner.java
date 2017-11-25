package presentation.userCommunicationConfig;

import java.io.IOException;

public interface IScanner {
    String nextLine() throws IOException;
    int nextInt() throws IOException;
}
