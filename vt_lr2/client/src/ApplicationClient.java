import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ApplicationClient {
    static final int portNumber = 3000;
    static final String serverName = "localhost";
    public static void main(String[] args) throws IOException {
        startClient();
    }

    private static void startClient() throws IOException {
        Socket connectionSocket = new Socket(serverName, portNumber);
        PrintWriter printWriter = new PrintWriter(connectionSocket.getOutputStream());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        System.out.println("Connect to the server");
        BufferedReader clientInputReader = new BufferedReader(new InputStreamReader(System.in));
        String clientInput;
        String serverResponse;
        while(true) {
            clientInput = clientInputReader.readLine();
            printWriter.println(clientInput);
            printWriter.flush();
            if(clientInput.equalsIgnoreCase("exit")) {
                break;
            }
            Thread thread = new Thread(new ReadingThread(bufferedReader));
            thread.start();
        }
        System.out.println("Exiting from the system...");
        System.exit(0);
    }

    private static class ReadingThread implements Runnable {
        BufferedReader bufferedReader;
        public  ReadingThread(Object parameter) {
            bufferedReader = (BufferedReader) parameter;
        }

        @Override
        public void run() {
            String serverResponse;
            try {
                while ((serverResponse = bufferedReader.readLine())!=null) {
                    System.out.println(serverResponse);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
