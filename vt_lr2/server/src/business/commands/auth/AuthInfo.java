package business.commands.auth;

import business.configuration.AuthProvider;
import business.commandsService.ILibraryCommand;
import presentation.dialogs.AuthDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AuthInfo implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        AuthDialog.printLogInfo(AuthProvider.authManager.get().getAuthUser());
    }
}
