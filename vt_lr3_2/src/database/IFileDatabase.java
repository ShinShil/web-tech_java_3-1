package database;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface IFileDatabase {
    Object[] getRecords() throws IOException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException;
    void add(Object obj) throws IOException, NoSuchFieldException, IllegalAccessException, SQLException;
    void update(Object oldObj, Object newObj) throws IOException, NoSuchFieldException, IllegalAccessException;
    void deleteRecord(Object obj) throws IOException, NoSuchFieldException, IllegalAccessException;
}
