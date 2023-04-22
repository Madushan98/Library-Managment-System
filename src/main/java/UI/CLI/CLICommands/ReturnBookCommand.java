package UI.CLI.CLICommands;

import Library.Service.LibraryService;
import UI.Interfaces.Command;

import java.util.Scanner;

public class ReturnBookCommand implements Command {

        private final LibraryService libraryService;
        private final Scanner scanner;

        public ReturnBookCommand(LibraryService libraryService, Scanner scanner) {
                this.libraryService = libraryService;
                this.scanner = scanner;
        }

    @Override
    public void execute() {
        System.out.println("Enter the title of the book you want to return:");
        String title = scanner.nextLine();
        boolean bookReturned = libraryService.returnBook(title);
        if (bookReturned) {
            System.out.println("The book \"" + title + "\" was returned successfully.");
        } else {
            System.out.println("The book \"" + title + "\" could not be returned.");
        }
    }


    @Override
    public String getDescription() {
        return "Return a book";
    }
}
