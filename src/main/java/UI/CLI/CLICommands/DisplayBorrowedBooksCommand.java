package UI.CLI.CLICommands;

import Library.Service.LibraryService;
import UI.Interfaces.Command;

public class DisplayBorrowedBooksCommand implements Command {

    private final LibraryService libraryService;
    public DisplayBorrowedBooksCommand(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public void execute() {
        // TODO: Implement this method
    }

    @Override
    public String getDescription() {
        return "Display borrowed books";
    }
}
