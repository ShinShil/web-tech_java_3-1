package presentation.dialogs;

import business.configuration.constants.IAdminKey;
import presentation.models.AuthModel;
import business.auth.AuthModelState;
import persistance.models.User;
import persistance.models.UserRole;

import java.util.Scanner;

public class AuthDialog extends BaseDialog {
    public static User readNewUser() {
        Scanner scan = new Scanner(System.in);
        printer.print("Enter login: ");
        String login = scanner.nextLine();
        printer.print("Enter password: ");
        String password = scanner.nextLine();
        printer.print("Enter email: ");
        String email = scanner.nextLine();
        printer.print("Enter key for admin role: ");
        String adminKey = scanner.nextLine();
        User user = new User();
        user.name = login;
        user.email = email;
        user.password = password;
        user.role = adminKey.equals(IAdminKey.adminKey) ? UserRole.Admin : UserRole.User;
        return user;
    }

    public static void printAfterUserRegistered() {
        printer.println("New user has  been successfully registered");
    }

    public static AuthModel readAuthData() {
        AuthModel authModel = new AuthModel();
        Scanner scan = new Scanner(System.in);
        printer.print("Enter login: ");
        authModel.login = scanner.nextLine();
        printer.print("Enter password: ");
        authModel.password = scanner.nextLine();
        return authModel;
    }

    public static void printTryAuthState(AuthModelState state) {
        switch(state) {
            case Valid:
                printer.println("Login and password are valid. You are done.");
                break;
            case WrongPassword:
                printer.println("Wrong password");
                break;
            case WrongLogin:
                printer.println("Wrong login");
                break;
        }
    }

    public static void printLogInfo(User user) {
        if(user == null) {
            printer.println("User is logged out");
        } else {
            printer.println("User is logged on");
            printer.println("Login: " + user.name);
            printer.println("Role: " + user.role);
        }

    }
}
