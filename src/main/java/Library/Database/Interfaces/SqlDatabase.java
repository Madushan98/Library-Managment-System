package Library.Database.Interfaces;

import java.sql.ResultSet;
import java.util.List;

import Library.Entity.Book;

public interface SqlDatabase {
  void ExecuteUpdate(String sqlString);

  ResultSet ExecuteQuery(String sqlString);

  List<Book> ExecuteBookQuery(String sqlString);
}
