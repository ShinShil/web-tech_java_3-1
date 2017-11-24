package business.commands;

import business.commandsService.IAvailableCommands;
import business.commandsService.ILibraryCommand;
import business.configuration.AuthProvider;
import persistance.dao.models.UserRole;
import presentation.dialogs.HelpDialog;

import java.lang.reflect.Field;

public class Help implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws IllegalAccessException {
        System.out.println("Commands for any user");
        HelpDialog.printCommands(AuthProvider.permissionManager.unAuthCommands);
        System.out.println("Commands for auth user");
        HelpDialog.printCommands(AuthProvider.permissionManager.permissions.get(UserRole.User));
        System.out.println("Commands for auth admin");
        HelpDialog.printCommands(AuthProvider.permissionManager.permissions.get(UserRole.Admin));
    }
}
