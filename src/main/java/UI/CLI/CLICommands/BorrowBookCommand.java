package UI.CLI.CLICommands;

import Library.Entity.Book;
import Library.Service.LibraryService;
import UI.Interfaces.Command;
import Utils.StringToDatetime;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class BorrowBookCommand implements Command {

    private final LibraryService libraryService;
    private final Scanner scanner;

    public BorrowBookCommand(LibraryService libraryService, Scanner scanner) {
        this.libraryService = libraryService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.print("Enter the book id: ");
            int bookId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter the borrowing Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter the DueDate (yyyy-MM-dd):");
            String dueDateString = scanner.nextLine();
            LocalDate dueDate = StringToDatetime.getDate(dueDateString);

            Book book = libraryService.borrowBook(bookId, username, dueDate);

            if (book == null) {
                System.out.println("Book not found or already borrowed");
            } else {
                System.out.println("Book borrowed successfully: " + book.getTitle());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid book ID");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter a date in the format yyyy-MM-dd.");
        }
    }

    @Override
    public String getDescription() {
        return  "Borrow a book";
    }
}
