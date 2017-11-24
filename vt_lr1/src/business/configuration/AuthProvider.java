package business.configuration;

import business.EmailSender;
import business.PermissionManager;
import business.auth.AuthManager;
import business.auth.PasswordAuth;

public class AuthProvider {
    public static AuthManager authManager;
    public static PasswordAuth passwordAuth;
    public static PermissionManager permissionManager;
    public static EmailSender emailSender;

    static {
        authManager = new AuthManager();
        passwordAuth = new PasswordAuth();
        permissionManager = new PermissionManager();
        emailSender = new EmailSender();
    }
}
