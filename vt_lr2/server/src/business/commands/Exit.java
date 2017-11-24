package business.commands;

import business.commandsService.ILibraryCommand;
import presentation.dialogs.BaseDialog;

public class Exit implements ILibraryCommand {

    @Override
    public void Invoke(String[] tokens) {
        BaseDialog.printOnExit();
    }
}
