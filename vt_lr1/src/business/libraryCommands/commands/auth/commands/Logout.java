package business.libraryCommands.commands.auth.commands;

import business.libraryCommands.AuthProvider;
import business.libraryCommands.ILibraryCommand;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Logout implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        AuthProvider.authManager.exit();
        System.out.println("Done.");
    }
}
