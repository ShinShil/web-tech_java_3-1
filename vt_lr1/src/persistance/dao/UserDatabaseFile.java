package persistance.dao;

import business.DatabaseProvider;
import database.FileDatabase;
import persistance.dao.models.BookRecord;
import persistance.dao.models.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

public class UserDatabaseFile implements IUserDatabase {
    private FileDatabase database = new FileDatabase("users.txt", User.class);

    public UserDatabaseFile() throws IOException {

    }

    @Override
    public void add(User user) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        database.add(user);
    }

    @Override
    public void delete(User user) throws IllegalAccessException, NoSuchFieldException, IOException {
        database.deleteRecord(user);
    }

    @Override
    public User[] getAllUsers() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Object[] tmp = database.getRecords();
        return Arrays.copyOf(tmp, tmp.length, User[].class);
    }

    @Override
    public User findUserWithLogin(String login) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        User[] users = getAllUsers();
        Optional<User> userOptionsl = Arrays.stream(users).filter(user -> user.name.equals(login)).findFirst();
        return userOptionsl.isPresent() ? userOptionsl.get() : null;
    }
}
