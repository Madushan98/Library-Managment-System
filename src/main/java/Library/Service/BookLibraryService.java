package Library.Service;

import Library.Database.Interfaces.BookManager;
import Library.Entity.Book;
import Library.Entity.BookRecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookLibraryService implements LibraryService {

    private final BookManager dataHandler;

    public BookLibraryService(BookManager dataHandler) {
        this.dataHandler = dataHandler;
    }

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

        return dataHandler.GetBook(bookId);
    }

    @Override
    public List<Book> getAvailableBooks() {
        return dataHandler.GetAllBooks();
    }

    @Override
    public List<Book> getBorrowedBooks() {
        return dataHandler.GetAllBooks();
    }

    @Override
    public List<BookRecord> getOverdueBooks() {
        return new ArrayList<BookRecord>();
    }

    @Override
    public boolean returnBook(String title) {
        return false;
    }

    @Override
    public List<Book> searchBooks(String title) {
        return new ArrayList<Book>();
    }
}
