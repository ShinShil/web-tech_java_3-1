package database.implementation;

import database.IFileDatabase;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabase implements IFileDatabase {
    protected String connectionString;
    protected String tableName;
    protected Class type;

    public SQLiteDatabase(String connectionString, String tableName, Class type) throws SQLException {
        this.connectionString = connectionString;
        this.tableName = tableName;
        this.type = type;
        DriverManager.registerDriver(new org.sqlite.JDBC());
    }

    @Override
    public Object[] getRecords() throws IOException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        try (
                Connection con = DriverManager.getConnection(connectionString);
                Statement statement = con.createStatement();
        ) {
            String sql = "select * from " + tableName;
            ResultSet resultSet = statement.executeQuery(sql);
            List<Object> objects = new ArrayList<Object>();
            Field[] fields = type.getDeclaredFields();
            Constructor<?> ctor = type.getConstructor();
            while (resultSet.next()) {
                Object elem = ctor.newInstance();
                for (int i = 0; i < fields.length; ++i) {
                    if (fields[i].getType().getName().equals("int")) {
                        elem.getClass().getDeclaredField(fields[i].getName()).set(elem, resultSet.getInt(fields[i].getName()));
                    } else if (fields[i].getType().isEnum()) {
                        elem.getClass().getDeclaredField(fields[i].getName()).set(elem, Enum.valueOf((Class<Enum>) fields[i].getType(), resultSet.getString(fields[i].getName())));
                    } else {
                        elem.getClass().getDeclaredField(fields[i].getName()).set(elem, resultSet.getString(fields[i].getName()));
                    }
                }
                objects.add(elem);
            }
            return objects.toArray();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Object[0];
    }

    @Override
    public void add(Object obj) throws IOException, NoSuchFieldException, IllegalAccessException, SQLException {
        try (
                Connection con = DriverManager.getConnection(connectionString);
                Statement statement = con.createStatement();
        ) {
            String columns = "(";
            String values = "values(";
            for (Field field :
                    obj.getClass().getDeclaredFields()) {
                if (!field.getName().equals("id")) {
                    columns += field.getName() + ",";
                    if (field.getType().getName().equals("java.lang.String")) {
                        values += "\"" + field.get(obj).toString() + "\"" + ",";
                    } else {
                        values += field.get(obj).toString() + ",";
                    }
                }
            }
            columns = columns.substring(0, columns.length() - 1);
            values = values.substring(0, values.length() - 1);
            columns += ")";
            values += ")";

            String sql = "insert into " + tableName + columns + " " + values;
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object oldObj, Object newObj) throws IOException, NoSuchFieldException, IllegalAccessException {
        deleteRecord(oldObj);
        try {
            add(newObj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRecord(Object obj) throws IOException, NoSuchFieldException, IllegalAccessException {
        try (
                Connection con = DriverManager.getConnection(connectionString);
                Statement statement = con.createStatement();
        ) {
            String sql = "delete from " + tableName + " where id = " + obj.getClass().getDeclaredField("id").get(obj);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
