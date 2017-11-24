package business.libraryCommands.commands.book;

import persistance.dao.models.BookRecord;
import business.DatabaseProvider;
import business.libraryCommands.ILibraryCommand;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DeleteBook implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        BookRecord book = BookHelper.getBookWithId();
        if(book != null) {
            DatabaseProvider.bookDatabase.delete(book);
            System.out.println("book has been successfully deleted");
        } else {
            System.out.println("book with specified id wasn't found");
        }
    }
}
