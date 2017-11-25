package business.commands.auth;

import business.configuration.AuthProvider;
import business.commandsService.ILibraryCommand;
import presentation.models.AuthModel;
import business.auth.AuthModelState;
import presentation.dialogs.AuthDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class Auth implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchAlgorithmException {
        AuthModel authModel = AuthDialog.readAuthData();
        AuthProvider.authManager.get().auth(authModel);
        AuthModelState tryAuthState = AuthProvider.authManager.get().getUserTryAuthState(authModel);
        AuthDialog.printTryAuthState(tryAuthState);
    }
}
