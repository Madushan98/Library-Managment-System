package UI.CLI.CLICommands;

import Library.Entity.Book;
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
        System.out.println("Enter the Id of the book you want to return:");
        int bookId = Integer.parseInt(scanner.nextLine());
        Book bookReturned = libraryService.returnBook(bookId);
        if (bookReturned != null) {
            System.out.println("The book \"" + bookReturned.getTitle() + "\" was returned successfully.");
        } else {
            System.out.println("The book with \"" + bookId + "\" could not be returned.");
        }
    }


    @Override
    public String getDescription() {
        return "Return a book";
    }
}
