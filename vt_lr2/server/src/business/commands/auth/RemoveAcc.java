package business.commands.auth;

import business.auth.AuthModelState;
import business.commandsService.ILibraryCommand;
import business.configuration.AuthProvider;
import business.configuration.DatabaseProvider;
import persistance.models.User;
import presentation.dialogs.AuthDialog;
import presentation.dialogs.BaseDialog;
import presentation.models.AuthModel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class RemoveAcc implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchAlgorithmException {
        AuthModel authModel = AuthDialog.readAuthData();
        AuthModelState tryAuthState = AuthProvider.authManager.get().getUserTryAuthState(authModel);
        if(tryAuthState == AuthModelState.Valid) {
            if(AuthProvider.authManager.get().getAuthUserName().equals(authModel.login)) {
                AuthProvider.authManager.get().exit();
            }
            User user =DatabaseProvider.userDatabase.findUserWithLogin(authModel.login);
            DatabaseProvider.userDatabase.delete(user);
            BaseDialog.done();
        } else {
            AuthDialog.printTryAuthState(tryAuthState);
        }
    }
}
