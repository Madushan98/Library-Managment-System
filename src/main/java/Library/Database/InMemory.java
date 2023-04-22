package Library.Database;

import java.util.ArrayList;
import java.util.List;

import Library.Database.Interfaces.InMemoryDatabase;
import Library.Entity.Book;
import Library.Entity.BookRecord;

public class InMemory implements InMemoryDatabase {
  private static InMemory instance = null;
  private List<Book> books = new ArrayList<>();
  private List<BookRecord> bookRecords = new ArrayList<>();

  private InMemory() {
  }

  public static InMemory getInstance() {
    if (instance == null) {
      instance = new InMemory();
    }

    return instance;
  }

  public int CreateBook(Book book) {
    books.add(book);
    return books.size() - 1;
  }

  public Book GetBook(int id) {
    return books.stream().filter(book -> book.getId() == id).findFirst().get();
  }

  public List<Book> GetAllBooks() {
    return books;
  }

  public List<Book> GetAllAvailableBooks() {
    return books.stream().filter(book -> book.getAvailability()).toList();
  }

  public void UpdateBook(Book book) {
    // override the book with the same id
    DeleteBook(book.getId());
    books.add(book);
  }

  public void DeleteBook(int id) {
    books.removeIf(book -> book.getId() == id);
  }

  public List<Book> SearchByName(String name) {
    return books.stream().filter(book -> book.getTitle().contains(name)).toList();
  }

  public void CreateBookRecord(BookRecord bookRecord) {
    bookRecords.add(bookRecord);
  }

  public BookRecord GetBookRecord(int id) {
    return bookRecords.stream().filter(bookRecord -> bookRecord.getId() == id).findFirst().get();
  }

  public List<BookRecord> GetAllBookRecords() {
    return bookRecords;
  }

  public void UpdateBookRecord(BookRecord bookRecord) {
    // override the book with the same id
    DeleteBookRecord(bookRecord.getId());
    bookRecords.add(bookRecord);
  }

  public void DeleteBookRecord(int id) {
    bookRecords.removeIf(bookRecord -> bookRecord.getId() == id);
  }
}
