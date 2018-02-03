package business.configuration;

import database.implementation.SettingsDatabase;
import persistance.abstraction.IRoomsDatabase;
import persistance.abstraction.IUserDatabase;
import persistance.implementation.Rooms;
import persistance.implementation.Users;

import java.io.IOException;

public class DatabaseProvider {

    public static IUserDatabase userDatabase;
    public static IRoomsDatabase roomsDatabase;

    static {
        try {
            roomsDatabase = new Rooms();
            userDatabase = new Users();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
