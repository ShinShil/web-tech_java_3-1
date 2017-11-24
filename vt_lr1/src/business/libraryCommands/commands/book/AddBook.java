package business.libraryCommands.commands.book;

import persistance.dao.models.BookRecord;
import business.DatabaseProvider;
import business.libraryCommands.ILibraryCommand;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class AddBook implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        BookRecord book = BookHelper.readNewBook();
        DatabaseProvider.bookDatabase.add(book);
        System.out.println("book has been succesfully added");
    }
}
