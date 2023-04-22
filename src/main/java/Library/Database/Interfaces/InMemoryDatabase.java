package Library.Database.Interfaces;

import java.util.List;

import Library.Entity.Book;
import Library.Entity.BookRecord;

public interface InMemoryDatabase {
  public void CreateBook(Book book);

  public Book GetBook(int id);

  public List<Book> GetAllBooks();

  public List<Book> GetAllAvailableBooks();

  public void UpdateBook(Book book);

  public void DeleteBook(int id);

  public List<Book> SearchByName(String name);

  public void CreateBookRecord(BookRecord bookRecord);

  public BookRecord GetBookRecord(int id);

  public List<BookRecord> GetAllBookRecords();

  public void UpdateBookRecord(BookRecord bookRecord);

  public void DeleteBookRecord(int id);
}
