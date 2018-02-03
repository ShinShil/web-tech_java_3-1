package business.auth;

import business.configuration.AuthProvider;
import business.configuration.DatabaseProvider;
import presentation.model.AuthModel;
import persistance.models.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class AuthManager {
    private User authUser = null;
    public String getAuthUserName() {
        return authUser == null ? "" : authUser.login;
    }

    public void login(AuthModel authModel) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchAlgorithmException {
        authUser = getRegisteredUser(authModel);
    }

    public void register(String login, String password) throws IllegalAccessException, InvocationTargetException, IOException, InstantiationException, NoSuchMethodException, NoSuchFieldException, NoSuchAlgorithmException {
        int userRole = login.equals("shin") ? 1 : 0;
        User user = new User();
        user.login = login;
        user.password = AuthProvider.passwordAuth.encodePassword(password);
        user.role = userRole;
        DatabaseProvider.userDatabase.add(user);
    }

    public void logout() {
        authUser = null;
    }

    public User getAuthUser() {
        return authUser;
    }

    public User getRegisteredUser(AuthModel authModel) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchAlgorithmException {
        User user = DatabaseProvider.userDatabase.findUserWithLogin(authModel.login);
        return user != null
                ? AuthProvider.passwordAuth.isUserPasswordValid(user, authModel.password) ? user : null
                : null;
    }

    public AuthModelState getUserTryAuthState(AuthModel authModel) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchAlgorithmException {
        User user = DatabaseProvider.userDatabase.findUserWithLogin(authModel.login);
        AuthModelState res;
        if(user != null) {
            if(AuthProvider.passwordAuth.isUserPasswordValid(user, authModel.password)) {
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
