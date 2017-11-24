package business.configuration;

import persistance.BookDatabaseFile;
import persistance.IBookDatabase;
import persistance.IUserDatabase;
import persistance.UserDatabaseFile;

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
