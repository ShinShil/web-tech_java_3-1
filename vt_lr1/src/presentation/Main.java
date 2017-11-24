package presentation;

import business.commandsService.CommandParser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, NoSuchAlgorithmException {
        System.out.println("Application has started, enter commands\nEnter help for help");
        new CommandParser().Run();
    }
}
