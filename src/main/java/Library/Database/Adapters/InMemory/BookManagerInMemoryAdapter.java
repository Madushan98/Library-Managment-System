package Library.Database.Adapters.InMemory;

import java.sql.ResultSet;
import java.util.List;

import Library.Database.Enums.SupportInMemoryDB;
import Library.Database.Interfaces.BookManager;
import Library.Database.Interfaces.SearchStrategy;
import Library.Entity.Book;

public class BookManagerInMemoryAdapter extends BaseInMemoryInitiator implements BookManager {
  public BookManagerInMemoryAdapter(SupportInMemoryDB name) {
    super(name);
  }

  public Book SaveBook(Book book) {
    return database.CreateBook(book);
  }

  public Book GetBook(int id) {
    return database.GetBook(id);
  }

  public List<Book> GetAllBooks() {
    return database.GetAllBooks();
  }

  public List<Book> GetAllAvailableBooks() {
    return database.GetAllAvailableBooks();
  }

  public Book UpdateBook(Book book) {
    return database.UpdateBook(book);
  }

  public void DeleteBook(int id) {
    database.DeleteBook(id);
  }

  public List<Book> SearchByName(String name) {
    return database.SearchByName(name);
  }

  public List<Book> SearchByAuthor(String author) {
    return database.SearchByAuthor(author);
  }

  @Override
  public List<Book> search(SearchStrategy strategy, String keyword) {
    List<Book> books = database.SearchByName(keyword);
    return strategy.search(keyword, books);
  }

}
