package Library.Database.Interfaces;

import java.util.List;

import Library.Entity.Book;

public interface SearchStrategy {
    List<Book> search(String keyword, List<Book> books);
}
