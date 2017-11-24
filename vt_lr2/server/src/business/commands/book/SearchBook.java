package business.commands.book;

import business.configuration.DatabaseProvider;
import business.commandsService.ILibraryCommand;
import persistance.models.BookRecord;
import persistance.models.RecordSearchParams;
import presentation.dialogs.BookDialog;
import business.configuration.constants.IBookFields;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class SearchBook implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        List<RecordSearchParams> searchParams = BookDialog.readSearchBooksParams();
        if(searchParams != null && searchParams.size() > 0) {
            BookRecord[] books = DatabaseProvider.bookDatabase.getAllBooks();
            BookRecord[] findBooks = Arrays.stream(books).filter(bookRecord -> {
                boolean isPassFilter = true;
                for (RecordSearchParams param : searchParams) {
                    if (param.field.equals(IBookFields.author)) {
                        isPassFilter = isPassFilter && bookRecord.author.contains(param.value);
                    } else if (param.field.equals(IBookFields.id)) {
                        isPassFilter = isPassFilter && String.valueOf(bookRecord.id).contains(String.valueOf(param.value));
                    } else if (param.field.equals(IBookFields.name)) {
                        isPassFilter = isPassFilter && bookRecord.name.contains(param.value);
                    }
                }
                return isPassFilter;
            }).toArray(BookRecord[]::new);
            BookDialog.printBooks(findBooks);
        }
    }
}
