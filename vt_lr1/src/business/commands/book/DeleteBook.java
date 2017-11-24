package business.commands.book;

import persistance.dao.models.BookRecord;
import business.configuration.DatabaseProvider;
import business.commandsService.ILibraryCommand;
import presentation.dialogs.BookDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DeleteBook implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        BookRecord book = BookDialog.getBookWithId();
        if(book != null) {
            DatabaseProvider.bookDatabase.delete(book);
            System.out.println("book has been successfully deleted");
        } else {
            System.out.println("book with specified id wasn't found");
        }
    }
}
