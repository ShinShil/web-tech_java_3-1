package business.commands.auth;

import business.configuration.DatabaseProvider;
import business.configuration.AuthProvider;
import business.commandsService.ILibraryCommand;
import presentation.models.AuthModel;
import business.auth.AuthModelState;
import persistance.models.User;
import presentation.dialogs.AuthDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class RemoveAcc implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchAlgorithmException {
        AuthModel authModel = AuthDialog.readAuthData();
        AuthModelState tryAuthState = AuthProvider.authManager.getUserTryAuthState(authModel);
        if(tryAuthState == AuthModelState.Valid) {
            if(AuthProvider.authManager.getAuthUserName().equals(authModel.login)) {
                AuthProvider.authManager.exit();
            }
            User user =DatabaseProvider.userDatabase.findUserWithLogin(authModel.login);
            DatabaseProvider.userDatabase.delete(user);
            System.out.println("Done.");
        } else {
            AuthDialog.printTryAuthState(tryAuthState);
        }
    }
}
