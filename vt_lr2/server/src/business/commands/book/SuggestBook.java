package business.commands.book;

import business.configuration.AuthProvider;
import business.configuration.DatabaseProvider;
import business.commandsService.ILibraryCommand;
import persistance.models.BookRecord;
import persistance.models.User;
import persistance.models.UserRole;
import presentation.dialogs.BookDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class SuggestBook implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchAlgorithmException {
        BookRecord bookRecord = BookDialog.readNewBook();
        String adminName = BookDialog.readAdminName();
        User admin = DatabaseProvider.userDatabase.findUserWithLogin(adminName);
        if(admin == null || admin.role != UserRole.Admin) {
            BookDialog.printIfCantFindAdminWithLogin();
        } else {
            User sender = AuthProvider.authManager.getAuthUser();
            AuthProvider.emailSender.suggestNewBook(bookRecord, admin, sender);
        }
    }
}
