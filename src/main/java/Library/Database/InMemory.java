package Library.Database;

import java.time.LocalDate;
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

  public Book CreateBook(Book book) {
    books.add(book);
    return book;
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

  public Book UpdateBook(Book book) {
    // override the book with the same id
    DeleteBook(book.getId());
    books.add(book);
    return book;
  }

  public void DeleteBook(int id) {
    books.removeIf(book -> book.getId() == id);
  }

  public List<Book> SearchByName(String name) {
    return books.stream().filter(book -> book.getTitle().contains(name)).toList();
  }

  public BookRecord CreateBookRecord(BookRecord bookRecord) {
    bookRecords.add(bookRecord);
    return bookRecord;
  }

  public BookRecord GetBookRecord(int id) {
    return bookRecords.stream().filter(bookRecord -> bookRecord.getId() == id).findFirst().get();
  }

  public List<BookRecord> GetAllBookRecords() {
    return bookRecords;
  }

  public BookRecord GetLastBookRecordForBook(int bookId) {
    return bookRecords.stream().filter(bookRecord -> bookRecord.getBookId() == bookId).reduce((first, second) -> second)
        .get();
  }

  public BookRecord UpdateBookRecord(BookRecord bookRecord) {
    // override the book with the same id
    DeleteBookRecord(bookRecord.getId());
    bookRecords.add(bookRecord);
    return bookRecord;
  }

  public void DeleteBookRecord(int id) {
    bookRecords.removeIf(bookRecord -> bookRecord.getId() == id);
  }

  @Override
  public List<BookRecord> GetBorrowedBooks() {
    return bookRecords.stream().filter(bookRecord -> bookRecord.isReturned() == false).toList();
  }

  @Override
  public List<BookRecord> GetOverdueBooks() {
    return bookRecords.stream()
        .filter(bookRecord -> bookRecord.isReturned() == false && LocalDate.now().isAfter(bookRecord.getDueDate()))
        .toList();
  }

  @Override
  public List<Book> SearchByAuthor(String name) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'SearchByAuthor'");
  }

}
