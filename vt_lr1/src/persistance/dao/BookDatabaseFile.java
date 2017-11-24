package persistance.dao;

import database.FileDatabase;
import persistance.dao.models.BookRecord;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class BookDatabaseFile implements IBookDatabase {
    private FileDatabase database = new FileDatabase("test.txt", BookRecord.class);

    public BookDatabaseFile() throws IOException {
    }

    @Override
    public void add(BookRecord book) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        Object[] tmp = database.getRecords();
        book.id = tmp.length == 0 ? 0 : ((BookRecord)tmp[tmp.length - 1]).id + 1;
        database.add(book);
    }

    @Override
    public void update(BookRecord oldBook, BookRecord newBook) throws IllegalAccessException, NoSuchFieldException, IOException {
        newBook.id = oldBook.id;
        database.update(oldBook, newBook);
    }

    @Override
    public void delete(BookRecord book) throws IllegalAccessException, NoSuchFieldException, IOException {
        database.deleteRecord(book);
    }

    @Override
    public BookRecord[] getAllBooks() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Object[] tmp = database.getRecords();
        return Arrays.copyOf(tmp, tmp.length, BookRecord[].class);
    }
}
