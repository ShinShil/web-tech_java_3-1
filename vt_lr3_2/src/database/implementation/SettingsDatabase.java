package database.implementation;

import database.constants.ISettingsConstants;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SettingsDatabase extends SQLiteDatabase {
    public SettingsDatabase(String connectionString, String tableName, Class type) throws SQLException {
        super(connectionString, tableName, type);
    }

    public void SetLocale(String locale) {
        this.UpdateSettingByKey(ISettingsConstants.localeKey, locale);
    }

    public String GetLocale() {
        return this.getSettingByKey(ISettingsConstants.localeKey);
    }

    private String getSettingByKey(String key) {
        try (
                Connection con = DriverManager.getConnection(connectionString);
                Statement statement = con.createStatement();
        ) {
            String sql = "select value from " + tableName + " where key = \"" + key + "\"";
            ResultSet resultSet = statement.executeQuery(sql);
            String value = "";
            while (resultSet.next()) {
                   value = resultSet.getString("value");
            }
            return value;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void UpdateSettingByKey(String key, String newValue) {
        try (
                Connection con = DriverManager.getConnection(connectionString);
                Statement statement = con.createStatement();
        ) {
            String sql = "update " + tableName + " set value = \"" + newValue + "\" where key = \"" + key + "\"";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
