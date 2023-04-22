package Library.Service;

import Library.Database.Interfaces.BookManager;
import Library.Database.Interfaces.RecordManager;
import Library.Entity.Book;
import Library.Entity.BookRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookLibraryService implements LibraryService {

    private BookManager bookManager;
    private RecordManager recordManager;

    public BookLibraryService(BookManager bookManager, RecordManager recordManager) {
        this.bookManager = bookManager;
        this.recordManager = recordManager;
    }

    @Override
    public boolean addBook(String title, String author) {
        Book book = new Book(title, author);
        book = bookManager.SaveBook(book);
        return book != null;
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
    public List<Book> getAllBooks() {
        return bookManager.GetAllBooks();
    }

    @Override
    public List<Book> getAvailableBooks() {
        return bookManager.GetAllAvailableBooks();
    }

    @Override
    public List<Book> getBorrowedBooks() {
        List<BookRecord> records = recordManager.GetBorrowedBooks();
        List<Book> books = new ArrayList<>();

        for (BookRecord record : records) {
            Book book = bookManager.GetBook(record.getBookId());
            books.add(book);
        }

        return books;
    }

    @Override
    public List<Book> getOverdueBooks() {
        List<BookRecord> records = recordManager.GetOverdueBooks();
        List<Book> books = new ArrayList<>();

        for (BookRecord record : records) {
            Book book = bookManager.GetBook(record.getBookId());
            books.add(book);
        }

        return books;
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
