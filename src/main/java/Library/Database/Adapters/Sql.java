package Library.Database.Adapters;

import java.util.List;

import Library.Database.Sqlite;
import Enums.SupportSqlDB;
import Library.Database.Interfaces.DataHandler;
import Library.Database.Interfaces.SqlDatabase;
import Library.Entity.Book;

public class Sql implements DataHandler {
  private SqlDatabase database;

  public Sql(SupportSqlDB name) {
    switch (name) {
      case SQLITE:
        database = Sqlite.getInstance();
        break;
      default:
        throw new IllegalArgumentException("Invalid database name");
    }
  }

  public void CreateBook(Book book) {
    database.ExecuteUpdate(
        "INSERT INTO BOOKS (TITLE, AUTHOR) VALUES ('%s', '%s')"
            .formatted(book.getTitle(), book.getAuthor()));
  }

  public Book GetBook(int id) {

    return database.ExecuteBookQuery("SELECT * FROM BOOKS WHERE ID = %d".formatted(id)).get(0);
  }

  public List<Book> GetAllBooks() {
    return database.ExecuteBookQuery("SELECT * FROM BOOKS");
  }

  public List<Book> GetAllAvailableBooks() {
    return database.ExecuteBookQuery("SELECT * FROM BOOKS WHERE AVAILABLE = 1");
  }

  public void UpdateBook(Book book) {
    database.ExecuteUpdate(
        "UPDATE BOOKS SET TITLE = '%s', AUTHOR = '%s', AVAILABLE = %d WHERE ID = %d"
            .formatted(book.getTitle(), book.getAuthor(), book.isAvailability() ? 1 : 0,
                book.getId()));
  }

  public void DeleteBook(int id) {
    database.ExecuteUpdate("DELETE FROM BOOKS WHERE ID = %d".formatted(id));
  }

  public List<Book> SearchByName(String name) {
    return database.ExecuteBookQuery("SELECT * FROM BOOKS WHERE TITLE LIKE '%%%s%%'".formatted(name));
  }
}
