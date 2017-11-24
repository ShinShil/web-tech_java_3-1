package business.libraryCommands;

import business.AuthManager;
import business.libraryCommands.commands.auth.PasswordAuth;

public class AuthProvider {
    public static AuthManager authManager;
    public static PasswordAuth passwordAuth;

    static {
        authManager = new AuthManager();
        passwordAuth = new PasswordAuth();
    }
}
