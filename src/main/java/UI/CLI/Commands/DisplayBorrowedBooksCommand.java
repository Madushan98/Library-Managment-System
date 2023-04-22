package UI.CLI.Commands;

import Library.Service.LibraryService;
import UI.CLI.Interfaces.Command;

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
