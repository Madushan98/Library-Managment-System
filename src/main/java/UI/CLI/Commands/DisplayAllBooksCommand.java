package UI.CLI.Commands;

import Library.Entity.Book;
import Library.Service.LibraryService;
import UI.CLI.Interfaces.Command;

import java.util.List;

public class DisplayAllBooksCommand implements Command {

    private final LibraryService libraryService;

    public DisplayAllBooksCommand(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public void execute() {
        List<Book> allBooks = libraryService.getAllBooks();
        System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
        for (Book book : allBooks) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.getAvailability() ? "Yes" : "No");
        }
    }

    @Override
    public void getDescription() {
        System.out.println("Display all books");
    }
}
