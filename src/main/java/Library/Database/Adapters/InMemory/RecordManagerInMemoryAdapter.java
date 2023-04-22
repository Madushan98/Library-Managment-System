package Library.Database.Adapters.InMemory;

import java.util.List;

import Library.Database.Enums.SupportInMemoryDB;
import Library.Database.Interfaces.RecordManager;
import Library.Entity.BookRecord;

public class RecordManagerInMemoryAdapter extends BaseInMemoryInitiator implements RecordManager {
  public RecordManagerInMemoryAdapter(SupportInMemoryDB name) {
    super(name);
  }

  public int CreateBookRecord(BookRecord record) {
    return database.CreateBookRecord(record);
  }

  public BookRecord GetBookRecord(int id) {
    return database.GetBookRecord(id);
  }

  public List<BookRecord> GetAllBookRecords() {
    return database.GetAllBookRecords();
  }

  public void UpdateBookRecord(BookRecord record) {
    database.UpdateBookRecord(record);
  }

  public void DeleteBookRecord(int id) {
    database.DeleteBookRecord(id);
  }
}
