package business.commands.book;

import business.configuration.AuthProvider;
import persistance.models.BookRecord;
import business.configuration.DatabaseProvider;
import business.commandsService.ILibraryCommand;
import presentation.dialogs.BookDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AddBook implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        BookRecord book = BookDialog.readNewBook();
        DatabaseProvider.bookDatabase.add(book);
        AuthProvider.emailSender.notifyAboutNewBook(book);
        BookDialog.printAfterNewBookAdded();
    }
}
