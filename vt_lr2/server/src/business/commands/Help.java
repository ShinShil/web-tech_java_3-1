package business.commands;

import business.commandsService.ILibraryCommand;
import business.configuration.AuthProvider;
import persistance.models.UserRole;
import presentation.dialogs.HelpDialog;

public class Help implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws IllegalAccessException {
        HelpDialog.commandsBelongs("any user");
        HelpDialog.printCommands(AuthProvider.permissionManager.unAuthCommands);
        HelpDialog.commandsBelongs("auth user");
        HelpDialog.printCommands(AuthProvider.permissionManager.permissions.get(UserRole.User));
        HelpDialog.commandsBelongs("auth admin");
        HelpDialog.printCommands(AuthProvider.permissionManager.permissions.get(UserRole.Admin));
    }
}
