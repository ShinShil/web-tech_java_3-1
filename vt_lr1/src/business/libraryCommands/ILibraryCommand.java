package business.libraryCommands;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface ILibraryCommand {
    void Invoke(String[] tokens) throws NoSuchMethodException, IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException;
}
