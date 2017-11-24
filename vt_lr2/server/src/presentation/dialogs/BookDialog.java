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
    public static BookRecord readNewBook() {
        Scanner scanner = new Scanner(System.in);
        BookRecord book = new BookRecord();
        printer.print("Enter author of the book: ");
        book.author = scanner.nextLine();
        printer.print("Enter name of the book: ");
        book.name = scanner.nextLine();
        return book;
    }

    public static void printIfCantFindAdminWithLogin() {
        printer.println("Can't find admin with such login");
    }

    public static void printAfterNewBookAdded() {
        printer.println("New book has been succesfully added");
    }

    public static void printAfterBookDeleted() {
        printer.println("book has been succesfully deleted");
    }

    public static void printBookWithIdNotFound() {
        printer.println("Book with specified id wasn't found");
    }

    public static void printAfterBookUpdated() {
        printer.println("book has been successfully updated");
    }

    public static String readAdminName() {
        Scanner scan = new Scanner(System.in);
        printer.print("Enter admin name: ");
        return scanner.nextLine();
    }

    public static List<RecordSearchParams> readSearchBooksParams() throws IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        printer.println("Enter the fields for search, you can use space as separator. There are 3 fields: id, name, author");
        String[] fieldsForSearch = scanner.nextLine().split(" ");
        Field[] validBookFields = IBookFields.class.getDeclaredFields();
        List<RecordSearchParams> res = new ArrayList<>();
        for (String fieldForSearch : fieldsForSearch) {
            boolean ifFind = false;
            for (Field field : validBookFields) {
                ifFind = ifFind || field.get(field.getClass()).toString().equals(fieldForSearch);
            }
            if(!ifFind) {
                printer.println(fieldForSearch + " is unknown field");
            } else {
                RecordSearchParams params = new RecordSearchParams();
                params.field = fieldForSearch;
                printer.print("Enter string for search for field " + fieldForSearch + ": ");
                String input = scanner.nextLine();
                params.value = input;
                res.add(params);
            }
        }
        return res;
    }

    public static BookRecord getBookWithId() throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);
        printer.print("Enter book id: ");
        int id = scanner.nextInt();
        BookRecord[] books = DatabaseProvider.bookDatabase.getAllBooks();
        BookRecord book = null;
        if(Arrays.stream(books).anyMatch(b -> b.id == id)) {
            book = Arrays.stream(books).filter(b -> b.id == id).findFirst().get();
        }
        return book;
    }

    public static int readBooksPerPageAmount() {
        printer.print("Enter amount of books per page(0): ");
        return new Scanner(System.in).nextInt();
    }

    public static void printBooksWithPaging(BookRecord[] books, int amountOnPage) throws IOException {
        if(books.length == 0) {
            printer.println("There are no books in database");
        } else {
            for(int page = 0; page * amountOnPage < books.length; ++page) {
                printer.println("Page " + (page + 1));
                int endOfPage = (page + 1) * amountOnPage;
                if(endOfPage > books.length) {
                    endOfPage = books.length;
                }
                printBooks(Arrays.copyOfRange(books, page * amountOnPage, endOfPage));
                if(endOfPage != books.length) {
                    printer.println("Enter for next page");
                    new BufferedReader(new InputStreamReader(System.in)).readLine();
                }
            }
            printer.println("\ndone. All pages have been printed.");
        }
    }

    public static void printBooks(BookRecord[] books, boolean needHeader) {
        if(books.length > 0) {
            if (needHeader) {
                printBooksHeader();
            }
            for (BookRecord book :
                    books) {
                printer.print(String.format("%d\t%s\t%s\n", book.id, book.name, book.author));
            }
        }else {
            printer.println("There no books");
        }
    }

    public static void printBooks(BookRecord[] books) {
        printBooks(books, true);
    }

    public static void printBooksHeader() {
        printer.println("Id\tName\tAuthor");
    }
}
