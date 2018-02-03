package business.configuration;

import database.IFileDatabase;
import database.implementation.SQLiteDatabase;
import database.implementation.SettingsDatabase;
import persistance.models.Room;
import persistance.models.User;

import java.sql.SQLException;

public class PhysicalDatabaseProvider {
    public static IFileDatabase userDatabase;
    public static IFileDatabase roomsDatabase;
    public static SettingsDatabase settingsDatabase;

    static {
        try {
            userDatabase = new SQLiteDatabase("jdbc:sqlite:D://messages.db", "users", User.class);
            roomsDatabase = new SQLiteDatabase("jdbc:sqlite:D://messages.db", "rooms", Room.class);
            settingsDatabase = new SettingsDatabase("jdbc:sqlite:D://messages.db", "settings", null);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
