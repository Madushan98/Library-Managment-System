package UI.CLI.CLICommands;

import Library.Service.LibraryService;
import UI.Interfaces.Command;

public class DisplayOverdueBooksCommand implements Command {
    private final LibraryService libraryService;

    public DisplayOverdueBooksCommand(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public void execute() {
        // TODO: Implement this method
    }

    @Override
    public String getDescription() {
        return "Display overdue books";
    }
}

