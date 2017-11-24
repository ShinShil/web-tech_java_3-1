package business;

import persistance.dao.models.BookRecord;
import persistance.dao.models.User;
import persistance.dao.models.UserRole;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class EmailSender {
    public void notifyAboutNewBook(BookRecord newBook) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        User[] users = DatabaseProvider.userDatabase.getAllUsers();
        String message = getNewBookMessage(newBook);
        for(User user : users) {
            sendEmail(user.email, message);
        }
    }

    public void suggestNewBook(BookRecord book, User admin, User sender) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        if(admin.role == UserRole.Admin) {
            String message = getSuggestNewBookMessage(book, sender.name);
            sendEmail(admin.email, message);
        }
    }

    private void sendEmail(String userMail, String message) {
        System.out.println("Email has been sent to " + userMail + " with message: " + message);
    }

    private String getNewBookMessage(BookRecord bookRecord) {
        return "New book has been added." + castBookToString(bookRecord);
    }

    private String getSuggestNewBookMessage(BookRecord bookRecord, String senderName) {
        return "Hello, user with login: " + senderName + " suggest to add new book - " + castBookToString(bookRecord);
    }

    private String castBookToString(BookRecord bookRecord) {
        return "Id: " + bookRecord.id + ", Author: " + bookRecord.author + ", Name: "
                + bookRecord.name;
    }
}
