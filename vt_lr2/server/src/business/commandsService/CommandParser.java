package business.commandsService;

import business.configuration.AuthProvider;
import org.jdom2.JDOMException;
import presentation.dialogs.CommandDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class CommandParser {
    private String command = "";

    public void Run() throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InstantiationException, InvocationTargetException, NoSuchAlgorithmException, JDOMException {
        while(!command.equals(IAvailableCommands.exit)) {
            command = CommandDialog.getCommand();
            String[] tokens = getTokens(command);
            invokeCommand(tokens);
        }
    }

    private String[] getTokens(String command) {
        String[] tokens = command.split(" ");
        tokens[0] = tokens[0].toLowerCase();
        return tokens;
    }

    private void invokeCommand(String[] tokens) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchAlgorithmException, JDOMException {
        if(CommandsMap.getCommands().containsKey(tokens[0])) {
            if(AuthProvider.permissionManager.isCommandAllowed(tokens[0])) {
                CommandsMap.getCommands().get(tokens[0]).Invoke(tokens);
            } else {
                CommandDialog.printWhenNotAllowedCommand();
            }
        } else {
            CommandDialog.printUnknowCommand(tokens[0]);
        }
    }
}
