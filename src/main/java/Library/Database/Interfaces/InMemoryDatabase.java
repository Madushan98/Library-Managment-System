package Library.Database.Interfaces;

import java.util.List;

import Library.Entity.Book;
import Library.Entity.BookRecord;

public interface InMemoryDatabase {
  public Book CreateBook(Book book);

  public Book GetBook(int id);

  public List<Book> GetAllBooks();

  public List<Book> GetAllAvailableBooks();

  public Book UpdateBook(Book book);

  public void DeleteBook(int id);

  public List<Book> SearchByName(String name);

  public BookRecord CreateBookRecord(BookRecord bookRecord);

  public BookRecord GetBookRecord(int id);

  public List<BookRecord> GetAllBookRecords();

  public BookRecord GetLastBookRecordForBook(int bookId);

  public List<BookRecord> GetBorrowedBooks();

  public List<BookRecord> GetOverdueBooks();

  public BookRecord UpdateBookRecord(BookRecord bookRecord);

  public void DeleteBookRecord(int id);

  public List<Book> SearchByAuthor(String name);
}
