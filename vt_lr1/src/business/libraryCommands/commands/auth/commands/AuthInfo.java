package business.libraryCommands.commands.auth.commands;

import business.libraryCommands.AuthProvider;
import business.libraryCommands.ILibraryCommand;
import business.libraryCommands.commands.auth.AuthHelper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AuthInfo implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        AuthHelper.printLogInfo(AuthProvider.authManager.getAuthUser());
    }
}
