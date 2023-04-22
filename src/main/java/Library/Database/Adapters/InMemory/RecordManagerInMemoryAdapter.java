package Library.Database.Adapters.InMemory;

import java.util.List;

import Library.Database.Enums.SupportInMemoryDB;
import Library.Database.Interfaces.RecordManager;
import Library.Entity.BookRecord;

public class RecordManagerInMemoryAdapter extends BaseInMemoryInitiator implements RecordManager {
  public RecordManagerInMemoryAdapter(SupportInMemoryDB name) {
    super(name);
  }

  public BookRecord CreateBookRecord(BookRecord record) {
    return database.CreateBookRecord(record);
  }

  public BookRecord GetBookRecord(int id) {
    return database.GetBookRecord(id);
  }

  public BookRecord GetLastBookRecordForBook(int bookId) {
    return database.GetLastBookRecordForBook(bookId);
  }

  public List<BookRecord> GetAllBookRecords() {
    return database.GetAllBookRecords();
  }

  public BookRecord UpdateBookRecord(BookRecord record) {
    return database.UpdateBookRecord(record);
  }

  public void DeleteBookRecord(int id) {
    database.DeleteBookRecord(id);
  }
}
