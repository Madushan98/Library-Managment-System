package Library.Service;

import Library.Entity.Book;

import java.time.LocalDate;
import java.util.List;

public interface LibraryService {

    boolean addBook(String title, String author);

    boolean removeBook(int id);

    List<Book> getAllBooks();

    Book borrowBook(int bookId, String user, LocalDate date);

    List<Book> getAvailableBooks();

    List<Book> getBorrowedBooks();

    List<Book> getOverdueBooks();

    List<Book> searchBook(String title);
}
