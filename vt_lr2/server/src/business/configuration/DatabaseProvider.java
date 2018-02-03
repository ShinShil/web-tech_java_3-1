package business.configuration;

import persistance.BookDatabase;
import persistance.IBookDatabase;
import persistance.IUserDatabase;
import persistance.UserDatabase;

import java.io.IOException;

public class DatabaseProvider {
    public static IBookDatabase bookDatabase;
    public static IUserDatabase userDatabase;

    static {
        try {
            bookDatabase = new BookDatabase();
            userDatabase = new UserDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
