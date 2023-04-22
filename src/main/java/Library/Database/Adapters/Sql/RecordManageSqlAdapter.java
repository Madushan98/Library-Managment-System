package Library.Database.Adapters.Sql;

import java.util.List;

import Library.Database.Enums.SupportSqlDB;
import Library.Database.Interfaces.RecordManager;
import Library.Entity.BookRecord;

public class RecordManageSqlAdapter extends BaseSqlInitiator implements RecordManager {
  public RecordManageSqlAdapter(SupportSqlDB name) {
    super(name);
  }

  @Override
  public int CreateBookRecord(BookRecord bookRecord) {
    return database.ExecuteUpdate("INSERT INTO RECORDS (BOOK_ID, USER, DUE_DATE) VALUES (%d, '%s', '%s')"
        .formatted(bookRecord.getBookId(), bookRecord.getUser(), bookRecord.getDueDate().toString()));

  }

  @Override
  public void DeleteBookRecord(int id) {
    database.ExecuteUpdate("DELETE FROM RECORDS WHERE ID = %d".formatted(id));
  }

  @Override
  public List<BookRecord> GetAllBookRecords() {
    return database.ExecuteBookRecordQuery("SELECT * FROM RECORDS");
  }

  @Override
  public BookRecord GetBookRecord(int id) {
    return database.ExecuteBookRecordQuery("SELECT * FROM RECORDS WHERE ID = %d".formatted(id)).get(0);
  }

  @Override
  public BookRecord GetLastBookRecordForBook(int bookId) {
    return database.ExecuteBookRecordQuery(
        "SELECT * FROM RECORDS WHERE BOOK_ID = %d AND RETURNED = 0 ORDER BY CREATED_AT DESC LIMIT 1"
            .formatted(bookId))
        .get(0);
  }

  @Override
  public void UpdateBookRecord(BookRecord bookRecord) {
    database.ExecuteUpdate(
        "UPDATE RECORDS SET BOOK_ID = %d, USER = '%s', DUE_DATE = '%s', RETURNED = %d WHERE ID = %d"
            .formatted(bookRecord.getBookId(), bookRecord.getUser(),
                bookRecord.getDueDate().toString(), bookRecord.isReturned() ? 1 : 0, bookRecord.getId()));
  }

}
