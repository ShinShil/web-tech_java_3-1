package presentation.dialogs;

import persistance.dao.models.BookRecord;
import business.configuration.DatabaseProvider;
import persistance.dao.models.RecordSearchParams;
import business.configuration.constants.IBookFields;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookDialog {
    public static BookRecord readNewBook() {
        Scanner scanner = new Scanner(System.in);
        BookRecord book = new BookRecord();
        System.out.print("Enter author of the book: ");
        book.author = scanner.nextLine();
        System.out.print("Enter name of the book: ");
        book.name = scanner.nextLine();
        return book;
    }

    public static String readAdminName() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter admin name: ");
        return scan.nextLine();
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

    public static int readBooksPerPageAmount() {
        System.out.print("Enter amount of books per page(0): ");
        return new Scanner(System.in).nextInt();
    }

    public static void printBooksWithPaging(BookRecord[] books, int amountOnPage) throws IOException {
        if(books.length == 0) {
            System.out.println("There are no books in database");
        } else {
            for(int page = 0; page * amountOnPage < books.length; ++page) {
                System.out.println("Page " + (page + 1));
                int endOfPage = (page + 1) * amountOnPage;
                if(endOfPage > books.length) {
                    endOfPage = books.length;
                }
                printBooks(Arrays.copyOfRange(books, page * amountOnPage, endOfPage));
                if(endOfPage != books.length) {
                    System.out.println("Enter for next page");
                    new BufferedReader(new InputStreamReader(System.in)).readLine();
                }
            }
            System.out.println("\nDone. All pages have been printed.");
        }
    }

    public static void printBooks(BookRecord[] books, boolean needHeader) {
        if(needHeader) {
            printBooksHeader();
        }
        for (BookRecord book :
                books) {
            System.out.printf("%d\t%s\t%s\n", book.id, book.name, book.author);
        }
    }

    public static void printBooks(BookRecord[] books) {
        printBooks(books, true);
    }

    public static void printBooksHeader() {
        System.out.println("Id\tName\tAuthor");
    }
}
