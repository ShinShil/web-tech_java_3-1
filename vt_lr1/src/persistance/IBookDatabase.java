package persistance;

import persistance.models.BookRecord;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface IBookDatabase {
    void add(BookRecord book) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InstantiationException, InvocationTargetException;
    void update(BookRecord oldBook, BookRecord newBook) throws IllegalAccessException, NoSuchFieldException, IOException;
    void delete(BookRecord book) throws IllegalAccessException, NoSuchFieldException, IOException;
    BookRecord[] getAllBooks() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException;
}
