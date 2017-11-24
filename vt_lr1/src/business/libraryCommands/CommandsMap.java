package business.libraryCommands;

import business.libraryCommands.commands.auth.commands.*;
import business.libraryCommands.commands.book.*;
import business.libraryCommands.commands.Exit;
import business.libraryCommands.commands.Help;
import org.apache.commons.lang3.ArrayUtils;
import persistance.dao.models.UserRole;

import java.util.HashMap;
import java.util.Map;

public class CommandsMap {
    private static Map<String, ILibraryCommand> commands = new HashMap<String, ILibraryCommand>();
    private static Map<UserRole, String[]> permissions = new HashMap<>();
    public static String[] unauthCommands = {
            IAvailableCommands.exit,
            IAvailableCommands.auth,
            IAvailableCommands.authInfo,
            IAvailableCommands.deleteAcc,
            IAvailableCommands.help,
            IAvailableCommands.register
    };
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

        permissions.put(UserRole.User, new String[]{
            IAvailableCommands.viewBooks,
            IAvailableCommands.search
        });

        permissions.put(UserRole.Admin, ArrayUtils.addAll(permissions.get(UserRole.User), new String[]{
            IAvailableCommands.edit,
            IAvailableCommands.deleteBook,
            IAvailableCommands.addBook
        }));
    }
    public static Map<String, ILibraryCommand> getCommands() {
        return commands;
    }
}
