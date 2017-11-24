package business.libraryCommands.commands.auth.commands;

import business.AuthProvider;
import business.DatabaseProvider;
import business.libraryCommands.ILibraryCommand;
import business.libraryCommands.commands.auth.AuthHelper;
import persistance.dao.models.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class Register implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchAlgorithmException {
        User newUser = AuthHelper.readNewUser();
        newUser.password = AuthProvider.passwordAuth.encodePassword(newUser.password);
        DatabaseProvider.userDatabase.add(newUser);
        System.out.println("New user has  been successfully registered");
    }
}
