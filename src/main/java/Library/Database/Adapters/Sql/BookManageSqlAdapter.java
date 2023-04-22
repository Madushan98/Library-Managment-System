package Library.Database.Adapters.Sql;

import java.util.List;

import Library.Database.Enums.SupportSqlDB;
import Library.Database.Interfaces.BookManager;
import Library.Entity.Book;

public class BookManageSqlAdapter extends BaseSqlInitiator implements BookManager {

  public BookManageSqlAdapter(SupportSqlDB name) {
    super(name);
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
            .formatted(book.getTitle(), book.getAuthor(), book.getAvailability() ? 1 : 0,
                book.getId()));
  }

  public void DeleteBook(int id) {
    database.ExecuteUpdate("DELETE FROM BOOKS WHERE ID = %d".formatted(id));
  }

  public List<Book> SearchByName(String name) {
    return database.ExecuteBookQuery("SELECT * FROM BOOKS WHERE TITLE LIKE '%%%s%%'".formatted(name));
  }
}
