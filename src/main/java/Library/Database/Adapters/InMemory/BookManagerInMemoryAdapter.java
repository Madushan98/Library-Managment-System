package Library.Database.Adapters.InMemory;

import java.util.List;

import Enums.SupportInMemoryDB;
import Library.Database.Interfaces.BookManager;
import Library.Entity.Book;

public class BookManagerInMemoryAdapter extends BaseInMemoryInitiator implements BookManager {
  public BookManagerInMemoryAdapter(SupportInMemoryDB name) {
    super(name);
  }

  public void CreateBook(Book book) {
    database.CreateBook(book);
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

  public void UpdateBook(Book book) {
    database.UpdateBook(book);
  }

  public void DeleteBook(int id) {
    database.DeleteBook(id);
  }

  public List<Book> SearchByName(String name) {
    return database.SearchByName(name);
  }

}
