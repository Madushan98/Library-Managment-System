package UI.CLI.CLICommands;

import Library.Entity.Book;
import Library.Service.LibraryService;
import UI.Interfaces.Command;

import java.util.List;

public class DisplayBorrowedBooksCommand implements Command {

    private final LibraryService libraryService;
    public DisplayBorrowedBooksCommand(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public void execute() {
        List<Book> allBooks = libraryService.getBorrowedBooks();
        System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
        for (Book book : allBooks) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.getAvailability() ? "Yes" : "No");
        }
    }

    @Override
    public String getDescription() {
        return "Display borrowed books";
    }
}
