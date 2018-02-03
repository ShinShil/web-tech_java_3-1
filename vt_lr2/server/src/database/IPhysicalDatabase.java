package database;

import org.jdom2.JDOMException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface IPhysicalDatabase {
    Object[] getRecords() throws IOException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException, JDOMException;
    void add(Object obj) throws IOException, NoSuchFieldException, IllegalAccessException, JDOMException;
    void update(Object oldObj, Object newObj) throws IOException, NoSuchFieldException, IllegalAccessException, JDOMException;
    void deleteRecord(Object obj) throws IOException, NoSuchFieldException, IllegalAccessException;
}
