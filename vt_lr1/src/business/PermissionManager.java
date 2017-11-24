package business;

import business.libraryCommands.IAvailableCommands;
import org.apache.commons.lang3.ArrayUtils;
import persistance.dao.models.User;
import persistance.dao.models.UserRole;

import java.util.HashMap;
import java.util.Map;

public class PermissionManager {
    private Map<UserRole, String[]> permissions = new HashMap<>();
    private String[] unAuthCommands = {
            IAvailableCommands.exit,
            IAvailableCommands.auth,
            IAvailableCommands.authInfo,
            IAvailableCommands.deleteAcc,
            IAvailableCommands.help,
            IAvailableCommands.register,
            IAvailableCommands.logout
    };

    public PermissionManager() {
        configurePermissions();
    }

    public boolean isCommandAllowed(String command) {
        User user = AuthProvider.authManager.getAuthUser();
        boolean res = false;
        String[] allowedCommands = user == null
                ? unAuthCommands
                : ArrayUtils.addAll(unAuthCommands, permissions.get(user.role));
        for (String allowedCommand: allowedCommands) {
            res = res || command.equals(allowedCommand);
        }
        return res;
    }

    private void configurePermissions() {
        permissions.put(UserRole.User, new String[]{
                IAvailableCommands.viewBooks,
                IAvailableCommands.search
        });

        permissions.put(UserRole.Admin, ArrayUtils.addAll(permissions.get(UserRole.User), new String[]{
                IAvailableCommands.edit,
                IAvailableCommands.deleteBook,
                IAvailableCommands.addBook
        }));
    }

}
