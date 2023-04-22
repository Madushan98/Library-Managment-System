package Library.Database.Interfaces;

import java.util.List;

import Library.Entity.BookRecord;

public interface RecordManager {
  public void CreateBookRecord(BookRecord bookRecord);

  public BookRecord GetBookRecord(int id);

  public List<BookRecord> GetAllBookRecords();

  public void UpdateBookRecord(BookRecord bookRecord);

  public void DeleteBookRecord(int id);
}
