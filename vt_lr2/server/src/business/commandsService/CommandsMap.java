package business.commandsService;

import business.commands.Exit;
import business.commands.Help;
import business.commands.auth.*;
import business.commands.book.*;

import java.util.HashMap;
import java.util.Map;

public class CommandsMap {
    private static Map<String, ILibraryCommand> commands = new HashMap<String, ILibraryCommand>();

    static {
        commands.put(IAvailableCommands.exit, new Exit());
        commands.put(IAvailableCommands.addBook, new AddBook());
        commands.put(IAvailableCommands.edit, new EditBook());
        commands.put(IAvailableCommands.auth, new Auth());
        commands.put(IAvailableCommands.deleteBook, new DeleteBook());
        commands.put(IAvailableCommands.viewBooks, new ViewBooks());
        commands.put(IAvailableCommands.help, new Help());
        commands.put(IAvailableCommands.register, new Register());
        commands.put(IAvailableCommands.deleteAcc, new RemoveAcc());
        commands.put(IAvailableCommands.search, new SearchBook());
        commands.put(IAvailableCommands.logout, new Logout());
        commands.put(IAvailableCommands.authInfo, new AuthInfo());
        commands.put(IAvailableCommands.suggest, new SuggestBook());
    }
    public static Map<String, ILibraryCommand> getCommands() {
        return commands;
    }
}
