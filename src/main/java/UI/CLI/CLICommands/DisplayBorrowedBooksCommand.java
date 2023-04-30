package UI.CLI.CLICommands;

import Library.Entity.Book;
import Library.Entity.BookRecord;
import Library.Service.LibraryService;
import UI.Interfaces.Command;

import java.util.List;

public class DisplayBorrowedBooksCommand implements Command {

    private final LibraryService libraryService;

    public DisplayBorrowedBooksCommand(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public void execute() {
        List<BookRecord> borrowBookRecords = libraryService.getBorrowedBooks();
        System.out.format("%-5s %-35s %-20s %-20s %-10s\n", "ID", "Title", "Author", "User", "Overdue Date");
        for (BookRecord bookRecord : borrowBookRecords) {
            Book book = libraryService.getBookById(bookRecord.getBookId());
            System.out.format("%-5s %-30s %-20s %-20s %-10s\n", bookRecord.getId(), book.getTitle(),
                    book.getAuthor(), bookRecord.getUser(), bookRecord.getDueDate());
        }
    }

    @Override
    public String getDescription() {
        return "Display borrowed books";
    }
}
