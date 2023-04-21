package Library.Service;

import Library.Database.Interfaces.DataHandler;
import Library.Entity.Book;

import java.time.LocalDate;
import java.util.List;

public class BookLibraryService implements LibraryService {

    private final DataHandler dataHandler;

    public BookLibraryService(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }


    @Override
    public boolean addBook(String title, String author) {
        dataHandler.CreateBook(new Book(title,author));
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
    public List<Book> getOverdueBooks() {
        return dataHandler.GetAllBooks();
    }
}
