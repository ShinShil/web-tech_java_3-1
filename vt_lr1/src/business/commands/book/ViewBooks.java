package business.commands.book;

import business.configuration.constants.IViewBookConstants;
import persistance.models.BookRecord;
import business.configuration.DatabaseProvider;
import business.commandsService.ILibraryCommand;
import presentation.dialogs.BookDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ViewBooks implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        BookRecord[] books = DatabaseProvider.bookDatabase.getAllBooks();
        int bookOnPage = BookDialog.readBooksPerPageAmount();
        if(bookOnPage == IViewBookConstants.notNeedPaging) {
            BookDialog.printBooks(books);
        }else {
            BookDialog.printBooksWithPaging(books, bookOnPage);
        }
    }
}
