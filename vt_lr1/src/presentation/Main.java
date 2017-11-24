package presentation;

import business.libraryCommands.CommandParser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        System.out.println("Application has started, enter commands\nEnter help for help");
        new CommandParser().Run();
    }
}
