package Library.Database.Interfaces;

import java.util.List;

import Library.Entity.Book;

public interface DataHandler {
  public void CreateBook(Book book);

  public Book GetBook(int id);

  public List<Book> GetAllBooks();

  public List<Book> GetAllAvailableBooks();

  public void UpdateBook(Book book);

  public void DeleteBook(int id);

  public List<Book> SearchByName(String name);
}
