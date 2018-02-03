import business.auth.AuthManager;
import business.commandsService.CommandParser;
import org.jdom2.JDOMException;
import presentation.dialogs.BaseDialog;
import presentation.userCommunicationConfig.CLIPrinter;
import presentation.userCommunicationConfig.SocketReader;
import presentation.userCommunicationConfig.SocketWriter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.AuthProvider;
import java.security.NoSuchAlgorithmException;

public class ApplicationServer {
    final static int portNumber = 3000;
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchAlgorithmException, IOException, InstantiationException, NoSuchMethodException, NoSuchFieldException, JDOMException {
        BaseDialog.startMessage();
        Thread serverThread = new Thread(() -> {
            try {
                startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();
        business.configuration.AuthProvider.authManager.set(new AuthManager());
        new CommandParser().Run();
    }

    private static void startServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(portNumber);
        while(true) {
            Socket connectionSocket = serverSocket.accept();
            new CLIPrinter().println("new client connected");
            Thread thread = new Thread(() -> {
                try {
                    business.configuration.AuthProvider.authManager.set(new AuthManager());
                    BaseDialog.scanner.set(new SocketReader(connectionSocket.getInputStream()));
                    BaseDialog.printer.set(new SocketWriter(connectionSocket.getOutputStream()));
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
                } catch (JDOMException e) {
                    e.printStackTrace();
                }
            });
            thread.setDaemon(true);
            thread.start();
        }

    }
}
