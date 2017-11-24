package business.libraryCommands.commands;

import business.libraryCommands.ILibraryCommand;

public class Exit implements ILibraryCommand {

    @Override
    public void Invoke(String[] tokens) {
        System.out.println("Shutting down...");
        System.out.println("Finish");
    }
}
