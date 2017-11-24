package business.commands;

import business.commandsService.ILibraryCommand;

public class Exit implements ILibraryCommand {

    @Override
    public void Invoke(String[] tokens) {
        System.out.println("Shutting down...");
        System.out.println("Finish");
    }
}
