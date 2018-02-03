package database.implementation;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import database.IFileDatabase;
import database.constants.IFindConstants;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class CsvFileDatabase implements IFileDatabase {
    private String fileName;
    private CSVReader reader;
    private CSVWriter writer;
    private Class type;

    public CsvFileDatabase(String fileName, Class type) throws IOException {
        this.fileName = fileName;
        this.type = type;
    }

    public Object[] getRecords() throws IOException, InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        List<String[]> rows = readRows();
        List<Object> res = new ArrayList<>();
        for (String[] row :
                rows) {
            res.add(castCsvRecToObject(row));
        }
        return res.toArray();
    }

    public void add(Object obj) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<String[]> rows = readRows();
        rows.add(castObjectToCsvRec(obj));
        writeRows(rows);
    }

    public void update(Object oldObj, Object newObj) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<String[]> rows = readRows();
        int recordIndex = findRecord(rows, oldObj);
        if(recordIndex != IFindConstants.notFound) {
            rows.set(recordIndex, castObjectToCsvRec(newObj));
            writeRows(rows);
        }
    }

    public void deleteRecord(Object obj) throws IOException, NoSuchFieldException, IllegalAccessException {
        List<String[]> rows = readRows();
        int recordIndex = findRecord(rows, obj);
        if(recordIndex != IFindConstants.notFound) {
            rows.remove(recordIndex);
            writeRows(rows);
        }
    }

    private void writeRows(List<String[]> rows) throws IOException {
        writer = new CSVWriter(new FileWriter(fileName));
        writer.writeAll(rows);
        writer.close();
    }

    private List<String[]> readRows() throws IOException {
        reader = new CSVReader(new FileReader(fileName));
        List<String[]> rows = reader.readAll();
        reader.close();
        return  rows;
    }

    private int findRecord(List<String[]> rows, Object obj) throws IOException, NoSuchFieldException, IllegalAccessException {
        for(int i = 0; i<rows.size(); ++i) {
            if(isCsvEqualWithObject(rows.get(i), obj)) {
                return i;
            }
        }
        return IFindConstants.notFound;
    }

    private String[] castObjectToCsvRec(Object obj) throws NoSuchFieldException, IllegalAccessException {
        List<String> res = new ArrayList<>();
        for (Field field :
                obj.getClass().getDeclaredFields()) {
            res.add(field.get(obj).toString());
        }
        String[] tmp = {};
        tmp = res.toArray(tmp);
        return tmp;
    }

    private Object castCsvRecToObject(String[] csvRec) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor<?> ctor = type.getConstructor();
        Object res = ctor.newInstance();
        Field[] fields = type.getDeclaredFields();
        for(int i = 0; i<fields.length; ++i) {
            if(fields[i].getType().getName().equals("int")) {
                res.getClass().getDeclaredField(fields[i].getName()).set(res, Integer.parseInt(csvRec[i]));
            } else if (fields[i].getType().isEnum()) {
                res.getClass().getDeclaredField(fields[i].getName()).set(res, Enum.valueOf((Class<Enum>)fields[i].getType(), csvRec[i]));
            }
            else {
                res.getClass().getDeclaredField(fields[i].getName()).set(res, csvRec[i]);
            }
        }
        return res;
    }

    private boolean isCsvEqualWithObject(String[] csv, Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            String prop = fields[i].get(obj).toString();
            if (!(prop.equals(csv[i]))) {
                return false;
            }
        }
        return true;
    }
}