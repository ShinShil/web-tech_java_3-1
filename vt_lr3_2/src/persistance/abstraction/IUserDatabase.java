package persistance.abstraction;

import persistance.models.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface IUserDatabase {
    void add(User user) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InstantiationException, InvocationTargetException;
    void delete(User user) throws IllegalAccessException, NoSuchFieldException, IOException;
    User[] getAllUsers() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException;
    User findUserWithLogin(String login) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException;
}
