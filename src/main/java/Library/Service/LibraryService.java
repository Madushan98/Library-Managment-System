package Library.Service;

import Library.Entity.Book;
import Library.Entity.BookRecord;

import java.time.LocalDate;
import java.util.List;

public interface LibraryService {

    boolean addBook(String title, String author, String genre);

    Book getBookById(int id);

    boolean removeBook(int id);

    List<Book> getAllBooks();

    Book borrowBook(int bookId, String user, LocalDate date);

    Book returnBook(int bookId);

    List<Book> getAvailableBooks();

    List<BookRecord> getBorrowedBooks();

    List<BookRecord> getOverdueBooks();

    List<Book> searchBook(String title);
}
