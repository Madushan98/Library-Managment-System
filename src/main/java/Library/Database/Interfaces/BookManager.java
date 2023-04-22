package Library.Database.Interfaces;

import java.util.List;

import Library.Entity.Book;

public interface BookManager {
  public Book SaveBook(Book book);

  public Book GetBook(int id);

  public List<Book> GetAllBooks();

  public List<Book> GetAllAvailableBooks();

  public Book UpdateBook(Book book);

  public void DeleteBook(int id);

  public List<Book> SearchByName(String name);
}
