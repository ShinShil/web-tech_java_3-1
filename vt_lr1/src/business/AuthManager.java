package business;

import business.libraryCommands.AuthProvider;
import business.libraryCommands.commands.auth.AuthModel;
import business.libraryCommands.commands.auth.AuthModelState;
import persistance.dao.models.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AuthManager {
    private User authUser = null;
    public String getAuthUserName() {
        return authUser == null ? "" : authUser.name;
    }

    public void auth(AuthModel authModel) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        authUser = getRegisteredUser(authModel);
    }

    public void exit() {
        authUser = null;
    }

    public User getAuthUser() {
        return authUser;
    }

    public User getRegisteredUser(AuthModel authModel) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        User user = DatabaseProvider.userDatabase.findUserWithLogin(authModel.login);
        return user != null
                ? AuthProvider.passwordAuth.isUserValid(user, authModel.password) ? user : null
                : null;
    }

    public AuthModelState getUserTryAuthState(AuthModel authModel) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        User user = DatabaseProvider.userDatabase.findUserWithLogin(authModel.login);
        AuthModelState res;
        if(user != null) {
            if(AuthProvider.passwordAuth.isUserValid(user, authModel.password)) {
                res = AuthModelState.Valid;
            } else {
                res = AuthModelState.WrongPassword;
            }
        } else {
            res = AuthModelState.WrongLogin;
        }
        return res;
    }
}
