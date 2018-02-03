package business.configuration;

import business.auth.AuthManager;
import business.auth.PasswordAuth;

public class AuthProvider {
    public static AuthManager authManager;
    public static PasswordAuth passwordAuth;

    static {
        authManager = new AuthManager();
        passwordAuth = new PasswordAuth();
    }
}
