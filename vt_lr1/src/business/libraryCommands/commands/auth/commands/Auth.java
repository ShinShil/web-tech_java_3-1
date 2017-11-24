package business.libraryCommands.commands.auth.commands;

import business.libraryCommands.AuthProvider;
import business.libraryCommands.ILibraryCommand;
import business.libraryCommands.commands.auth.AuthHelper;
import business.libraryCommands.commands.auth.AuthModel;
import business.libraryCommands.commands.auth.AuthModelState;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Auth implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        AuthModel authModel = AuthHelper.readAuthData();
        AuthProvider.authManager.auth(authModel);
        AuthModelState tryAuthState = AuthProvider.authManager.getUserTryAuthState(authModel);
        AuthHelper.printTryAuthState(tryAuthState);
    }
}
