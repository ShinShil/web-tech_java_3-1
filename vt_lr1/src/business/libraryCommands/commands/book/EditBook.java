package business.libraryCommands.commands.book;

import persistance.dao.models.BookRecord;
import business.DatabaseProvider;
import business.libraryCommands.ILibraryCommand;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class EditBook implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        BookRecord oldBook = BookHelper.getBookWithId();
        if(oldBook != null) {
            BookRecord newBook = BookHelper.readNewBook();
            DatabaseProvider.bookDatabase.update(oldBook, newBook);
            System.out.println("book has been successfully updated");
        } else {
            System.out.println("book with specified id wasn't found");
        }
    }
}
