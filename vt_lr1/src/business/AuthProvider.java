package business;

import business.AuthManager;
import business.libraryCommands.commands.auth.PasswordAuth;

public class AuthProvider {
    public static AuthManager authManager;
    public static PasswordAuth passwordAuth;
    public static PermissionManager permissionManager;

    static {
        authManager = new AuthManager();
        passwordAuth = new PasswordAuth();
        permissionManager = new PermissionManager();
    }
}
