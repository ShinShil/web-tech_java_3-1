package business.commands.auth;

import business.configuration.AuthProvider;
import business.commandsService.ILibraryCommand;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Logout implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        AuthProvider.authManager.exit();
        System.out.println("Done.");
    }
}
