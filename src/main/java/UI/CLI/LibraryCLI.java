package UI.CLI;

import Library.Entity.Book;
import Library.Service.LibraryService;
import Utils.StringToDatetime;
import UI.UI;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class LibraryCLI implements UI {

    private final LibraryService libraryService;
    private final Scanner scanner;

    public LibraryCLI(LibraryService libraryService) {
        this.libraryService = libraryService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void show() {
        start();
    }

    public void start() {
        boolean running = true;
        displayMenu();
        while (running) {
            int choice = getChoice();
            switch (choice) {
                case 1 -> addBook();
                case 2 -> removeBook();
                case 3 -> displayAllBooks();
                case 4 -> displayAvailableBooks();
                case 5 -> borrowBook();
                case 6 -> returnBook();
                case 7 -> displayBorrowedBooks();
                case 8 -> displayOverdueBooks();
                case 9 -> displayMenu();
                case 10 -> running = false;
                default -> System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("");
        }
    }

    private void displayMenu() {
        System.out.println("\n-------------Welcome to the Library Management System.-----------");
        System.out.println("Please choose an option:");
        System.out.println("1. Add a new book to the library.");
        System.out.println("2. Remove a book from the library.");
        System.out.println("3. Display a list of all books.");
        System.out.println("4. Display a list of available books.");
        System.out.println("5. Borrow a book.");
        System.out.println("6. Return a book.");
        System.out.println("7. Display a list of borrowed books.");
        System.out.println("8. Display a list of overdue books.");
        System.out.println("9. Exit the program.");
        System.out.println("");
    }


    private void addBook() {
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

    private void removeBook() {
        System.out.print("Enter the book id: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean isSuccess = libraryService.removeBook(id);
        if (isSuccess) {
            System.out.println("The book has been removed from the library.");
        } else {
            System.out.println("The book is not in the library.");
        }
    }

    private void displayAllBooks() {
        List<Book> allBooks = libraryService.getAllBooks();
        System.out.format("%-30s %-20s %-10s\n", "Title", "Author", "Available");
        System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
        for (Book book : allBooks) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.isAvailability());
        }
    }

    private void displayAvailableBooks() {
        List<Book> availableBooks = libraryService.getAvailableBooks();
        System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
        for (Book book : availableBooks) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.isAvailability());
        }
    }

    private void displayOverdueBooks() {
        List<Book> overdueBooks = libraryService.getOverdueBooks();
        System.out.format("%-30s %-20s %-10s\n", "Title", "Author", "Available");
        System.out.format("%-5s %-30s %-20s %-10s\n", "ID", "Title", "Author", "Available");
        for (Book book : overdueBooks) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.isAvailability());
        }
    }

    private int getChoice() {
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private void borrowBook() {
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

    private void displayBorrowedBooks() {
        List<Book> borrowedBooks = libraryService.getBorrowedBooks();
        for (Book book : borrowedBooks) {
            System.out.format("%-5s %-30s %-20s %-10s\n", book.getId(), book.getTitle(),
                    book.getAuthor(), book.isAvailability());
        }
    }

    private void returnBook() {

    }

}
