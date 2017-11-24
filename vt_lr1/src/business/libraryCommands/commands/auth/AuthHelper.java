package business.libraryCommands.commands.auth;

import persistance.dao.models.User;
import persistance.dao.models.UserRole;

import java.util.Scanner;

public class AuthHelper {
    public static User readNewUser() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter login: ");
        String login = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();
        System.out.print("Enter email: ");
        String email = scan.nextLine();
        System.out.print("Enter key for admin role: ");
        String adminKey = scan.nextLine();
        User user = new User();
        user.name = login;
        user.email = email;
        user.password = password;
        user.role = adminKey.equals(IAdminKey.adminKey) ? UserRole.Admin : UserRole.User;
        return user;
    }

    public static AuthModel readAuthData() {
        AuthModel authModel = new AuthModel();
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter login: ");
        authModel.login = scan.nextLine();
        System.out.print("Enter password: ");
        authModel.password = scan.nextLine();
        return authModel;
    }

    public static void printTryAuthState(AuthModelState state) {
        switch(state) {
            case Valid:
                System.out.println("Login and password are valid. You are done.");
                break;
            case WrongPassword:
                System.out.println("Wrong password");
                break;
            case WrongLogin:
                System.out.println("Wrong login");
                break;
        }
    }

    public static void printLogInfo(User user) {
        if(user == null) {
            System.out.println("User is logged out");
        } else {
            System.out.println("User is logged on");
            System.out.println("Login: " + user.name);
            System.out.println("Role: " + user.role);
        }

    }
}
