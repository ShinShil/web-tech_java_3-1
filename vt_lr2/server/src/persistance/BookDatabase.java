package persistance;

import database.CSVDatabase;
import database.XMLDatabase;
import org.jdom2.JDOMException;
import persistance.models.BookRecord;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class BookDatabase implements IBookDatabase {
    private XMLDatabase database = new XMLDatabase(IDatabaseFiles.BooksXML, BookRecord.class);

    public BookDatabase() throws IOException {
    }

    @Override
    public void add(BookRecord book) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InstantiationException, InvocationTargetException, JDOMException {
        Object[] tmp = database.getRecords();
        book.id = tmp.length == 0 ? 0 : ((BookRecord)tmp[tmp.length - 1]).id + 1;
        database.add(book);
    }

    @Override
    public void update(BookRecord oldBook, BookRecord newBook) throws IllegalAccessException, NoSuchFieldException, IOException, JDOMException {
        newBook.id = oldBook.id;
        database.update(oldBook, newBook);
    }

    @Override
    public void delete(BookRecord book) throws IllegalAccessException, NoSuchFieldException, IOException {
        database.deleteRecord(book);
    }

    @Override
    public BookRecord[] getAllBooks() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, JDOMException {
        Object[] tmp = database.getRecords();
        return Arrays.copyOf(tmp, tmp.length, BookRecord[].class);
    }
}
