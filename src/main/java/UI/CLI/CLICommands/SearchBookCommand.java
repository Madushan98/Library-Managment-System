package UI.CLI.CLICommands;

import Library.Entity.Book;
import Library.Service.LibraryService;
import UI.Interfaces.Command;

import java.util.List;
import java.util.Scanner;

public class SearchBookCommand implements Command {

    private final LibraryService libraryService;

    private final Scanner scanner;

    public SearchBookCommand(LibraryService libraryService, Scanner scanner) {
        this.libraryService = libraryService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter the title of the book to search for: ");
        String title = scanner.nextLine();
        List<Book> searchResults = libraryService.searchBook(title);
        if (searchResults.isEmpty()) {
            System.out.println("No books found with the title: " + title);
        } else {
            System.out.println("Search results: \n");
            System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
            for (Book book : searchResults) {
                System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                        book.getAuthor(), book.getAvailability() ? "Yes" : "No");
            }
        }
    }

    @Override
    public String getDescription() {
        return null;
    }
}
