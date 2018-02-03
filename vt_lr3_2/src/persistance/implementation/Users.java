package persistance.implementation;

import business.configuration.PhysicalDatabaseProvider;
import database.IFileDatabase;
import persistance.abstraction.IUserDatabase;
import persistance.models.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;

public class Users implements IUserDatabase {
    private IFileDatabase database = PhysicalDatabaseProvider.userDatabase;

    public Users() throws IOException {

    }

    @Override
    public void add(User user) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        try {
            database.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        Optional<User> userOptionsl = Arrays.stream(users).filter(user -> user.login.equals(login)).findFirst();
        return userOptionsl.isPresent() ? userOptionsl.get() : null ;
    }
}
