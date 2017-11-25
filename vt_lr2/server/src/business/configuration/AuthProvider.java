package business.configuration;

import business.EmailSender;
import business.PermissionManager;
import business.auth.AuthManager;
import business.auth.PasswordAuth;

public class AuthProvider {
    public static ThreadLocal<AuthManager> authManager = new ThreadLocal<>();
    public static PasswordAuth passwordAuth;
    public static PermissionManager permissionManager;
    public static EmailSender emailSender;

    static {
        authManager.set(new AuthManager());
        passwordAuth = new PasswordAuth();
        permissionManager = new PermissionManager();
        emailSender = new EmailSender();
    }
}
