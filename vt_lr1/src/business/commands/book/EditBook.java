package business.commands.book;

import persistance.dao.models.BookRecord;
import business.configuration.DatabaseProvider;
import business.commandsService.ILibraryCommand;
import presentation.dialogs.BookDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class EditBook implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        BookRecord oldBook = BookDialog.getBookWithId();
        if(oldBook != null) {
            BookRecord newBook = BookDialog.readNewBook();
            DatabaseProvider.bookDatabase.update(oldBook, newBook);
            System.out.println("book has been successfully updated");
        } else {
            System.out.println("book with specified id wasn't found");
        }
    }
}
