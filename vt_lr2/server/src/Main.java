import business.commandsService.CommandParser;
import presentation.dialogs.BaseDialog;
import presentation.userCommunicationConfig.SocketReader;
import presentation.userCommunicationConfig.SocketWriter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;

public class Main {
    final int portNumber = 3000;
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchAlgorithmException, IOException, InstantiationException, NoSuchMethodException, NoSuchFieldException {
        BaseDialog.startMessage();
        new CommandParser().Run();
    }

    private void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        while(true) {
            Socket connectionSocket = serverSocket.accept();
            Thread thread = new Thread(() -> {
                try {
                    BaseDialog.scanner.set(new SocketReader(connectionSocket.getInputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    BaseDialog.printer.set(new SocketWriter(connectionSocket.getOutputStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    new CommandParser().Run();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

    }
}
