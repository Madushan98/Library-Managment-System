package UI.CLI.CLICommands;

import Library.Service.LibraryService;
import UI.Interfaces.Command;

import java.util.Scanner;

public class AddBookCommand implements Command {
    private final LibraryService libraryService;
    private final Scanner scanner;

    public AddBookCommand(LibraryService libraryService, Scanner scanner) {
        this.libraryService = libraryService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter the book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the book author: ");
        String author = scanner.nextLine();
        boolean isSuccess = libraryService.addBook(title, author);
        if (isSuccess) {
            System.out.println("The book has been added to the library.");
        } else {
            System.out.println("The book is already in the library.");
        }
    }

    @Override
    public String getDescription() {
        return  "Add a new book";
    }
}

