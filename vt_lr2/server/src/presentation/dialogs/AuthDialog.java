package presentation.dialogs;

import business.configuration.constants.IAdminKey;
import presentation.models.AuthModel;
import business.auth.AuthModelState;
import persistance.models.User;
import persistance.models.UserRole;

import java.io.IOException;
import java.util.Scanner;

public class AuthDialog extends BaseDialog {
    public static User readNewUser() throws IOException {
        printer.get().print("Enter login: ");
        String login = scanner.get().nextLine();
        printer.get().print("Enter password: ");
        String password = scanner.get().nextLine();
        printer.get().print("Enter email: ");
        String email = scanner.get().nextLine();
        printer.get().print("Enter key for admin role: ");
        String adminKey = scanner.get().nextLine();
        User user = new User();
        user.name = login;
        user.email = email;
        user.password = password;
        user.role = adminKey.equals(IAdminKey.adminKey) ? UserRole.Admin : UserRole.User;
        return user;
    }

    public static void printAfterUserRegistered() {
        printer.get().println("New user has  been successfully registered");
    }

    public static AuthModel readAuthData() throws IOException {
        AuthModel authModel = new AuthModel();
        printer.get().print("Enter login: ");
        authModel.login = scanner.get().nextLine();
        printer.get().print("Enter password: ");
        authModel.password = scanner.get().nextLine();
        return authModel;
    }

    public static void printTryAuthState(AuthModelState state) {
        switch(state) {
            case Valid:
                printer.get().println("Login and password are valid. You are done.");
                break;
            case WrongPassword:
                printer.get().println("Wrong password");
                break;
            case WrongLogin:
                printer.get().println("Wrong login");
                break;
        }
    }

    public static void printLogInfo(User user) {
        if(user == null) {
            printer.get().println("User is logged out");
        } else {
            printer.get().println("User is logged on");
            printer.get().println("Login: " + user.name);
            printer.get().println("Role: " + user.role);
        }

    }
}
