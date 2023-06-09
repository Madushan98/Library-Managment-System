package Library.Database.Interfaces;

import java.util.List;

import Library.Entity.BookRecord;

public interface RecordManager {
  public BookRecord CreateBookRecord(BookRecord bookRecord);

  public BookRecord GetBookRecord(int id);

  public BookRecord GetLastBookRecordForBook(int bookId);

  public List<BookRecord> GetAllBookRecords();

  public List<BookRecord> GetBorrowedBooks();

  public List<BookRecord> GetOverdueBooks();

  public BookRecord UpdateBookRecord(BookRecord bookRecord);

  public void DeleteBookRecord(int id);
}
