package presentation.userCommunicationConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SocketReader implements  IScanner {
    private BufferedReader bufferedReader;

    public SocketReader(InputStream inputStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public String nextLine() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public int nextInt() throws IOException {
        String s = "";
        char ch = 'q';
        return Integer.parseInt(bufferedReader.readLine());
    }
}
