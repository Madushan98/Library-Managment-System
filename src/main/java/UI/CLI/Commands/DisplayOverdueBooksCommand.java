package UI.CLI.Commands;

import Library.Service.LibraryService;
import UI.CLI.Interfaces.Command;

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
    public void getDescription() {
        System.out.println("Display overdue books");
    }
}

