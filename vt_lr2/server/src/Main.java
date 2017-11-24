import business.commandsService.CommandParser;
import presentation.dialogs.BaseDialog;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchAlgorithmException, IOException, InstantiationException, NoSuchMethodException, NoSuchFieldException {
        BaseDialog.startMessage();
        new CommandParser().Run();
    }
}
