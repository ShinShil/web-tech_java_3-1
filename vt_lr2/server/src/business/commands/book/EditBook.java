package business.commands.book;

import org.jdom2.JDOMException;
import persistance.models.BookRecord;
import business.configuration.DatabaseProvider;
import business.commandsService.ILibraryCommand;
import presentation.dialogs.BookDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class EditBook implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, JDOMException {
        BookRecord oldBook = BookDialog.getBookWithId();
        if(oldBook != null) {
            BookRecord newBook = BookDialog.readNewBook();
            DatabaseProvider.bookDatabase.update(oldBook, newBook);
            BookDialog.printAfterBookUpdated();
        } else {
            BookDialog.printBookWithIdNotFound();
        }
    }
}
