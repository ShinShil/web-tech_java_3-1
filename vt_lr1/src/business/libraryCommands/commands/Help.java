package business.libraryCommands.commands;

import business.libraryCommands.IAvailableCommands;
import business.libraryCommands.ILibraryCommand;

import java.lang.reflect.Field;

public class Help implements ILibraryCommand {
    @Override
    public void Invoke(String[] tokens) throws IllegalAccessException {
        for (Field command:
             IAvailableCommands.class.getDeclaredFields()) {
            System.out.println(command.get(command.getClass()).toString());
        }
    }
}
