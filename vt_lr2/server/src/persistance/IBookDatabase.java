package persistance;

import org.jdom2.JDOMException;
import persistance.models.BookRecord;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface IBookDatabase {
    void add(BookRecord book) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InstantiationException, InvocationTargetException, JDOMException;
    void update(BookRecord oldBook, BookRecord newBook) throws IllegalAccessException, NoSuchFieldException, IOException, JDOMException;
    void delete(BookRecord book) throws IllegalAccessException, NoSuchFieldException, IOException;
    BookRecord[] getAllBooks() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException, JDOMException;
}
