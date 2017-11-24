package business.commands.book;

import persistance.models.BookRecord;
import business.configuration.DatabaseProvider;
import business.commandsService.ILibraryCommand;
import presentation.dialogs.BaseDialog;
import presentation.dialogs.BookDialog;

import java.awt.print.Book;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class DeleteBook implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        BookRecord book = BookDialog.getBookWithId();
        if(book != null) {
            DatabaseProvider.bookDatabase.delete(book);
            BookDialog.printAfterBookDeleted();
        } else {
            BookDialog.printBookWithIdNotFound();
        }
    }
}
