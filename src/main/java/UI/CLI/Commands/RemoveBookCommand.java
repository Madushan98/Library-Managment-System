package UI.CLI.Commands;

import Library.Service.LibraryService;
import UI.CLI.Interfaces.Command;

import java.util.Scanner;

public class RemoveBookCommand implements Command {

    private final LibraryService libraryService;
    private final Scanner scanner;

    public RemoveBookCommand(LibraryService libraryService, Scanner scanner) {
        this.libraryService = libraryService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.print("Enter the book id: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean isSuccess = libraryService.removeBook(id);
        if (isSuccess) {
            System.out.println("The book has been removed from the library.");
        } else {
            System.out.println("The book is not in the library.");
        }
    }

    @Override
    public void getDescription() {
        System.out.println("Remove a book");

    }
}
