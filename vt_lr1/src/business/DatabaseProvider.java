package business;

import persistance.dao.BookDatabaseFile;
import persistance.dao.IBookDatabase;
import persistance.dao.IUserDatabase;
import persistance.dao.UserDatabaseFile;

import java.io.IOException;

public class DatabaseProvider {
    public static IBookDatabase bookDatabase;
    public static IUserDatabase userDatabase;

    static {
        try {
            bookDatabase = new BookDatabaseFile();
            userDatabase = new UserDatabaseFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
