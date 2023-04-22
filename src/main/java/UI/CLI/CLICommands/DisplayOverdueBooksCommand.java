package UI.CLI.CLICommands;

import Library.Entity.Book;
import Library.Entity.BookRecord;
import Library.Service.LibraryService;
import UI.Interfaces.Command;

import java.util.List;

public class DisplayOverdueBooksCommand implements Command {
    private final LibraryService libraryService;

    public DisplayOverdueBooksCommand(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public void execute() {
        List<BookRecord> overdueRecords = libraryService.getOverdueBooks();
        System.out.format("%-5s %-20s %-10s\n", "ID", "Title", "Due Date");
        for (BookRecord bookRecord : overdueRecords) {
            Book book = libraryService.getBookById(bookRecord.getBookId());
            System.out.format("%-5s %-20s %-10s\n", bookRecord.getBookId(), book.getTitle(), bookRecord.getDueDate());
        }
    }

    @Override
    public String getDescription() {
        return "Display overdue books";
    }
}

