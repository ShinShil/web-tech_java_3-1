package business.libraryCommands.commands.auth;

import persistance.dao.models.User;

public class PasswordAuth {
    public boolean isUserValid(User user, String password) {
        return user.password.equals(encodePassword(password));
    }

    public String encodePassword(String password) {
        return password;
    }
}
