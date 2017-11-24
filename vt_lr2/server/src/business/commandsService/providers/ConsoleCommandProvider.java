package business.commandsService.providers;

import business.commandsService.ICommandProvider;
import presentation.userCommunicationConfig.CLIReader;

import java.util.Scanner;

public class ConsoleCommandProvider implements ICommandProvider {
    private CLIReader reader;

    public ConsoleCommandProvider() {
        reader = new CLIReader();
    }
    @Override
    public String getCommand() {
        return reader.nextLine();
    }
}
