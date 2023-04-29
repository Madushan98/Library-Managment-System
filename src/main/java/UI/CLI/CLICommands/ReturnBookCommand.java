package UI.CLI.CLICommands;

import Library.Entity.Book;
import Library.Entity.BookRecord;
import Library.Service.LibraryService;
import UI.Interfaces.Command;

import java.time.LocalDate;
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
        BookRecord bookRecord = libraryService.returnBookRecord(bookId);
        LocalDate today = LocalDate.now();
        LocalDate dueDate = bookRecord.getDueDate();
        if (bookReturned != null && (dueDate.isBefore(today) || dueDate.isEqual(today))) {
            System.out.println("The book \"" + bookReturned.getTitle() + "\" was returned successfully.");
        } else if (dueDate.isAfter(today)) {
            System.out.println("The book \"" + today + "\" is passed the due date(" + dueDate + ").");
        } else {
            System.out.println("The book with \"" + bookId + "\" could not be returned.");
        }
    }

    @Override
    public String getDescription() {
        return "Return a book";
    }
}
