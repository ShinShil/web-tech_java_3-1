package business.libraryCommands.commands.book;

import persistance.dao.models.BookRecord;
import business.DatabaseProvider;
import business.libraryCommands.ILibraryCommand;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ViewBooks implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        BookRecord[] books = DatabaseProvider.bookDatabase.getAllBooks();
        BookHelper.printBooks(books);
    }
}
