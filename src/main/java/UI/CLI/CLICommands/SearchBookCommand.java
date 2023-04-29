package UI.CLI.CLICommands;

import Library.Database.*;
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

        System.out.println("Please select one method:");
        System.out.println("1. Search according to the title");
        System.out.println("2. Search according to the author");
        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Enter the title of the book to search for: ");
            scanner.nextLine();
            String title = scanner.nextLine();
            System.out.println(title);
            List<Book> searchResults = libraryService.search(new TitleSearchStrategy(),
                    title);
            if (searchResults.isEmpty()) {
                System.out.println("No books found with the title: " + title);
            } else {
                System.out.println("Search results: \n");
                System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author",
                        "Available");
                for (Book book : searchResults) {
                    System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                            book.getAuthor(), book.getAvailability() ? "Yes" : "No");
                }
            }
        } else if (choice == 2) {
            System.out.print("Enter the author of the book to search for: ");
            scanner.nextLine();
            String author = scanner.nextLine();
            List<Book> searchResult = libraryService.search(new AuthorSearchStrategy(),
                    author);
            if (searchResult.isEmpty()) {
                System.out.println("No books found with the author: " + author);
            } else {
                System.out.println("Search results: \n");
                System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author",
                        "Available");
                for (Book book : searchResult) {
                    System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                            book.getAuthor(), book.getAvailability() ? "Yes" : "No");
                }
            }
        } else {
            System.out.println("Invalid choice");
        }
    }

    @Override
    public String getDescription() {
        return "Search Book";
    }
}
