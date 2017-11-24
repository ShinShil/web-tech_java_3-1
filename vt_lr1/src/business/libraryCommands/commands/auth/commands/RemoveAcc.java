package business.libraryCommands.commands.auth.commands;

import business.DatabaseProvider;
import business.libraryCommands.AuthProvider;
import business.libraryCommands.ILibraryCommand;
import business.libraryCommands.commands.auth.AuthHelper;
import business.libraryCommands.commands.auth.AuthModel;
import business.libraryCommands.commands.auth.AuthModelState;
import persistance.dao.models.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class RemoveAcc implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        AuthModel authModel = AuthHelper.readAuthData();
        AuthModelState tryAuthState = AuthProvider.authManager.getUserTryAuthState(authModel);
        if(tryAuthState == AuthModelState.Valid) {
            if(AuthProvider.authManager.getAuthUserName().equals(authModel.login)) {
                AuthProvider.authManager.exit();
            }
            User user =DatabaseProvider.userDatabase.findUserWithLogin(authModel.login);
            DatabaseProvider.userDatabase.delete(user);
        } else {
            AuthHelper.printTryAuthState(tryAuthState);
        }
    }
}
