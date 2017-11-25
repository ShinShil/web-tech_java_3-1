package presentation.dialogs;

import persistance.models.BookRecord;
import business.configuration.DatabaseProvider;
import persistance.models.RecordSearchParams;
import business.configuration.constants.IBookFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookDialog extends BaseDialog{
    public static BookRecord readNewBook() throws IOException {
        BookRecord book = new BookRecord();
        printer.get().print("Enter author of the book: ");
        book.author = scanner.get().nextLine();
        printer.get().print("Enter name of the book: ");
        book.name = scanner.get().nextLine();
        return book;
    }

    public static void printIfCantFindAdminWithLogin() {
        printer.get().println("Can't find admin with such login");
    }

    public static void printAfterNewBookAdded() {
        printer.get().println("New book has been succesfully added");
    }

    public static void printAfterBookDeleted() {
        printer.get().println("book has been succesfully deleted");
    }

    public static void printBookWithIdNotFound() {
        printer.get().println("Book with specified id wasn't found");
    }

    public static void printAfterBookUpdated() {
        printer.get().println("book has been successfully updated");
    }

    public static String readAdminName() throws IOException {
        printer.get().print("Enter admin name: ");
        return scanner.get().nextLine();
    }

    public static List<RecordSearchParams> readSearchBooksParams() throws IllegalAccessException, IOException {
        printer.get().println("Enter the fields for search, you can use space as separator. There are 3 fields: id, name, author");
        String[] fieldsForSearch = scanner.get().nextLine().split(" ");
        Field[] validBookFields = IBookFields.class.getDeclaredFields();
        List<RecordSearchParams> res = new ArrayList<>();
        for (String fieldForSearch : fieldsForSearch) {
            boolean ifFind = false;
            for (Field field : validBookFields) {
                ifFind = ifFind || field.get(field.getClass()).toString().equals(fieldForSearch);
            }
            if(!ifFind) {
                printer.get().println(fieldForSearch + " is unknown field");
            } else {
                RecordSearchParams params = new RecordSearchParams();
                params.field = fieldForSearch;
                printer.get().print("Enter string for search for field " + fieldForSearch + ": ");
                String input = scanner.get().nextLine();
                params.value = input;
                res.add(params);
            }
        }
        return res;
    }

    public static BookRecord getBookWithId() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        printer.get().print("Enter book id: ");
        int id = scanner.get().nextInt();
        BookRecord[] books = DatabaseProvider.bookDatabase.getAllBooks();
        BookRecord book = null;
        if(Arrays.stream(books).anyMatch(b -> b.id == id)) {
            book = Arrays.stream(books).filter(b -> b.id == id).findFirst().get();
        }
        return book;
    }

    public static int readBooksPerPageAmount() {
        printer.get().print("Enter amount of books per page(0): ");
        return new Scanner(System.in).nextInt();
    }

    public static void printBooksWithPaging(BookRecord[] books, int amountOnPage) throws IOException {
        if(books.length == 0) {
            printer.get().println("There are no books in database");
        } else {
            for(int page = 0; page * amountOnPage < books.length; ++page) {
                printer.get().println("Page " + (page + 1));
                int endOfPage = (page + 1) * amountOnPage;
                if(endOfPage > books.length) {
                    endOfPage = books.length;
                }
                printBooks(Arrays.copyOfRange(books, page * amountOnPage, endOfPage));
                if(endOfPage != books.length) {
                    printer.get().println("Enter for next page");
                    new BufferedReader(new InputStreamReader(System.in)).readLine();
                }
            }
            printer.get().println("\ndone. All pages have been printed.");
        }
    }

    public static void printBooks(BookRecord[] books, boolean needHeader) {
        if(books.length > 0) {
            if (needHeader) {
                printBooksHeader();
            }
            for (BookRecord book :
                    books) {
                printer.get().print(String.format("%d\t%s\t%s\n", book.id, book.name, book.author));
            }
        }else {
            printer.get().println("There no books");
        }
    }

    public static void printBooks(BookRecord[] books) {
        printBooks(books, true);
    }

    public static void printBooksHeader() {
        printer.get().println("Id\tName\tAuthor");
    }
}
