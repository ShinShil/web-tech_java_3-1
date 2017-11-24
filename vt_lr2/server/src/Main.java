import business.commandsService.CommandParser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchAlgorithmException, IOException, InstantiationException, NoSuchMethodException, NoSuchFieldException {
        System.out.println("Enter commands");
        new CommandParser().Run();
    }
}
