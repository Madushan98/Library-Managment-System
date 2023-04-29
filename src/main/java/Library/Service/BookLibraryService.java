package Library.Service;

import Library.Database.Interfaces.BookManager;
import Library.Database.Interfaces.RecordManager;
import Library.Entity.Book;
import Library.Entity.BookRecord;

import java.time.LocalDate;
import java.util.List;

public class BookLibraryService implements LibraryService {

    private BookManager bookManager;
    private RecordManager recordManager;

    public BookLibraryService(BookManager bookManager, RecordManager recordManager) {
        this.bookManager = bookManager;
        this.recordManager = recordManager;
    }

    @Override
    public boolean addBook(String title, String author, String genre) {
        Book book = new Book(title, author, genre);
        book = bookManager.SaveBook(book);
        return book != null;
    }

    @Override
    public Book getBookById(int id) {
        return bookManager.GetBook(id);
    }

    @Override
    public Book borrowBook(int bookId, String user, LocalDate date) {
        Book book = bookManager.GetBook(bookId);
        BookRecord record = new BookRecord(book.getId(), user);
        record = recordManager.CreateBookRecord(record);
        if (record != null) {
            book.setAvailability(false);
            bookManager.UpdateBook(book);
            return book;
        }
        return null;
    }

    @Override
    public Book returnBook(int bookId) {
        Book book = bookManager.GetBook(bookId);
        BookRecord record = recordManager.GetLastBookRecordForBook(bookId);
        record.setReturned(true);
        recordManager.UpdateBookRecord(record);
        book.setAvailability(true);
        book = bookManager.UpdateBook(book);
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookManager.GetAllBooks();
    }

    @Override
    public List<Book> getAvailableBooks() {
        return bookManager.GetAllAvailableBooks();
    }

    @Override
    public List<BookRecord> getBorrowedBooks() {
        return recordManager.GetBorrowedBooks();
    }

    @Override
    public List<BookRecord> getOverdueBooks() {
        return recordManager.GetOverdueBooks();
    }

    @Override
    public boolean removeBook(int id) {
        bookManager.DeleteBook(id);

        // TODO: Check delete is success
        return true;
    }

    @Override
    public List<Book> searchBook(String title) {
        return bookManager.SearchByName(title);
    }

}
