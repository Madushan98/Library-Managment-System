package UI.CLI.Commands;

import Library.Entity.Book;
import Library.Service.LibraryService;
import UI.CLI.Interfaces.Command;

import java.util.List;

public class DisplayAvailableBooksCommand implements Command {

    private final LibraryService libraryService;

    public DisplayAvailableBooksCommand(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public void execute() {
        List<Book> allBooks = libraryService.getAvailableBooks();
        System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
        for (Book book : allBooks) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.getAvailability() ? "Yes" : "No");
        }
    }

    @Override
    public String getDescription() {
        return  "Display available books";
    }
}
