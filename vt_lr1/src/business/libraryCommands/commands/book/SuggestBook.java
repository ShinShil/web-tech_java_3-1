package business.libraryCommands.commands.book;

import business.AuthProvider;
import business.DatabaseProvider;
import business.libraryCommands.ILibraryCommand;
import business.libraryCommands.commands.auth.AuthModel;
import persistance.dao.models.BookRecord;
import persistance.dao.models.User;
import persistance.dao.models.UserRole;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class SuggestBook implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchAlgorithmException {
        BookRecord bookRecord = BookHelper.readNewBook();
        String adminName = BookHelper.readAdminName();
        User admin = DatabaseProvider.userDatabase.findUserWithLogin(adminName);
        if(admin == null || admin.role != UserRole.Admin) {
            System.out.println("Can't find admin with such login");
        } else {
            User sender = AuthProvider.authManager.getAuthUser();
            AuthProvider.emailSender.suggestNewBook(bookRecord, admin, sender);
        }
    }
}
