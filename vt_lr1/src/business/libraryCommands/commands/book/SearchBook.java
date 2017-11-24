package business.libraryCommands.commands.book;

import business.DatabaseProvider;
import business.libraryCommands.ILibraryCommand;
import persistance.dao.models.BookRecord;
import persistance.dao.models.RecordSearchParams;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class SearchBook implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        List<RecordSearchParams> searchParams = BookHelper.readSearchBooksParams();
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
            if(findBooks.length > 0) {
                BookHelper.printBooks(findBooks);
            } else {
                System.out.println("No books found");
            }
        }
    }
}
