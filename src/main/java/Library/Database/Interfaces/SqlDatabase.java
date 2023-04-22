package Library.Database.Interfaces;

import java.util.List;

import Library.Entity.Book;
import Library.Entity.BookRecord;

public interface SqlDatabase {
  int ExecuteUpdate(String sqlString);

  List<Book> ExecuteBookQuery(String sqlString);

  List<BookRecord> ExecuteBookRecordQuery(String sqlString);
}
