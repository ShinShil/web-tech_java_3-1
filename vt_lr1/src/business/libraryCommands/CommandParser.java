package business.libraryCommands;

import business.AuthProvider;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class CommandParser {
    private String command = "";

    public void Run() throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InstantiationException, InvocationTargetException, NoSuchAlgorithmException {
        Scanner in = new Scanner(System.in);
        while(!command.equals(IAvailableCommands.exit)) {
            command = in.nextLine();
            String[] tokens = getTokens(command);
            invokeCommand(tokens);
        }
    }

    private String[] getTokens(String command) {
        String[] tokens = command.split(" ");
        tokens[0] = tokens[0].toLowerCase();
        return tokens;
    }

    private void invokeCommand(String[] tokens) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchAlgorithmException {
        if(CommandsMap.getCommands().containsKey(tokens[0])) {
            if(AuthProvider.permissionManager.isCommandAllowed(tokens[0])) {
                CommandsMap.getCommands().get(tokens[0]).Invoke(tokens);
            } else {
                System.out.println("You are not allowed to execute this command");
            }
        } else {
            System.out.println("Unknown command: " + tokens[0]);
            System.out.println("Type help for available commands");
        }
    }
}
