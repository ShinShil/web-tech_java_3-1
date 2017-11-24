package business.commands.auth;

import business.configuration.AuthProvider;
import business.configuration.DatabaseProvider;
import business.commandsService.ILibraryCommand;
import persistance.models.User;
import presentation.dialogs.AuthDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class Register implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchAlgorithmException {
        User newUser = AuthDialog.readNewUser();
        newUser.password = AuthProvider.passwordAuth.encodePassword(newUser.password);
        DatabaseProvider.userDatabase.add(newUser);
        AuthDialog.printAfterUserRegistered();
    }
}
