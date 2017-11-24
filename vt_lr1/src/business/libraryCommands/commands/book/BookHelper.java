package business.libraryCommands.commands.book;

import business.libraryCommands.commands.book.IBookFields;
import persistance.dao.models.BookRecord;
import business.DatabaseProvider;
import persistance.dao.models.RecordSearchParams;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookHelper {
    public static BookRecord readNewBook() {
        Scanner scanner = new Scanner(System.in);
        BookRecord book = new BookRecord();
        System.out.print("Enter author of the book: ");
        book.author = scanner.nextLine();
        System.out.print("Enter name of the book: ");
        book.name = scanner.nextLine();
        return book;
    }

    public static List<RecordSearchParams> readSearchBooksParams() throws IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the fields for search, you can use space as separator. There are 3 fields: id, name, author");
        String[] fieldsForSearch = scanner.nextLine().split(" ");
        Field[] validBookFields = IBookFields.class.getDeclaredFields();
        List<RecordSearchParams> res = new ArrayList<>();
        for (String fieldForSearch : fieldsForSearch) {
            boolean ifFind = false;
            for (Field field : validBookFields) {
                ifFind = ifFind || field.get(field.getClass()).toString().equals(fieldForSearch);
            }
            if(!ifFind) {
                System.out.println(fieldForSearch + " is unknown field");
            } else {
                RecordSearchParams params = new RecordSearchParams();
                params.field = fieldForSearch;
                System.out.print("Enter string for search for field " + fieldForSearch + ": ");
                String input = scanner.nextLine();
                params.value = input;
                res.add(params);
            }
        }
        return res;
    }

    public static BookRecord getBookWithId() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Enter book id: ");
        int id = scanner.nextInt();
        BookRecord[] books = DatabaseProvider.bookDatabase.getAllBooks();
        BookRecord book = null;
        if(Arrays.stream(books).anyMatch(b -> b.id == id)) {
            book = Arrays.stream(books).filter(b -> b.id == id).findFirst().get();
        }
        return book;
    }

    public static void printBook(BookRecord book) {
        System.out.println("Name: " + book.name);;
        System.out.println("Author: " + book.author);
    }

    public static void printBooks(BookRecord[] books) {
        System.out.println("Id\tName\tAuthor");
        for (BookRecord book :
                books) {
            System.out.printf("%d\t%s\t%s\n", book.id, book.name, book.author);
        }
    }
}
