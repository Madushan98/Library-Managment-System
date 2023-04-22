package Library.Service;

import Library.Database.Interfaces.BookManager;
import Library.Database.Interfaces.RecordManager;
import Library.Entity.Book;
import Library.Entity.BookRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class BookLibraryService implements LibraryService {

    private final BookManager dataHandler;
    // private RecordManager recordHandler;

    public BookLibraryService(BookManager dataHandler) {
        this.dataHandler = dataHandler;
    }
    // public BookLibraryService(RecordManager recordHandler) {
    // this.recordHandler = recordHandler;
    // }

    @Override
    public boolean addBook(String title, String author) {
        dataHandler.CreateBook(new Book(title, author));
        return true;
    }

    @Override
    public boolean removeBook(int id) {
        dataHandler.DeleteBook(id);
        return true;
    }

    @Override
    public List<Book> getAllBooks() {
        return dataHandler.GetAllBooks();
    }

    @Override
    public Book borrowBook(int bookId, String user, LocalDate date) {
        Optional<Book> bookOptional = dataHandler.GetAllBooks()
                .stream()
                .filter(book1 -> book1.getId() == bookId)
                .findFirst();
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            book.setAvailability(false);
            BookRecord bookRecord = book.getRecord();
            bookRecord.setDueDate(date);
            bookRecord.setUser(user);
            // recordHandler.CreateBookRecord(bookRecord);
            return book;
        }
        return null;
        // return dataHandler.GetBook(bookId);
    }

    @Override
    public List<Book> getAvailableBooks() {
        List<Book> books = dataHandler.GetAllBooks();
        return books.stream().filter(book -> !book.isAvailability()).toList();
    }

    @Override
    public List<Book> getBorrowedBooks() {
        return dataHandler.GetAllBooks();
    }

    @Override
    public List<Book> getOverdueBooks() {
        return dataHandler.GetAllBooks();
    }
}
