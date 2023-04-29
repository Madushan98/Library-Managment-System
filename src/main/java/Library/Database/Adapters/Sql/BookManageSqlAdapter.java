package Library.Database.Adapters.Sql;

import java.util.List;

import Library.Database.Enums.SupportSqlDB;
import Library.Database.Interfaces.BookManager;
import Library.Entity.Book;

public class BookManageSqlAdapter extends BaseSqlInitiator implements BookManager {

  public BookManageSqlAdapter(SupportSqlDB name) {
    super(name);
  }

  public Book SaveBook(Book book) {
    int id = database.ExecuteUpdate(
        "INSERT INTO BOOKS (TITLE, AUTHOR, GENRE) VALUES ('%s', '%s', '%s')"
            .formatted(book.getTitle(), book.getAuthor(), book.getGenre()));

    return new Book(id, book.getTitle(), book.getAuthor(), book.getGenre(), book.getAvailability());
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

  public Book UpdateBook(Book book) {
    int id = database.ExecuteUpdate(
        "UPDATE BOOKS SET TITLE = '%s', AUTHOR = '%s', GENRE = '%s', AVAILABLE = %d WHERE ID = %d"
            .formatted(book.getTitle(), book.getAuthor(), book.getGenre(), book.getAvailability() ? 1 : 0,
                book.getId()));

    return new Book(id, book.getTitle(), book.getAuthor(), book.getGenre(), book.getAvailability());
  }

  public void DeleteBook(int id) {
    database.ExecuteUpdate("DELETE FROM BOOKS WHERE ID = %d".formatted(id));
  }

  public List<Book> SearchByName(String name) {
    return database.ExecuteBookQuery("SELECT * FROM BOOKS WHERE TITLE LIKE '%%%s%%'".formatted(name));
  }
}
