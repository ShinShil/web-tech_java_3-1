package business.commands.book;

import persistance.dao.models.BookRecord;
import business.configuration.DatabaseProvider;
import business.commandsService.ILibraryCommand;
import presentation.dialogs.BookDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ViewBooks implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        BookRecord[] books = DatabaseProvider.bookDatabase.getAllBooks();
        BookDialog.printBooks(books);
    }
}
