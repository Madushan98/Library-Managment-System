package Library.Database;

import java.util.ArrayList;
import java.util.List;

import Library.Database.Interfaces.SearchStrategy;
import Library.Entity.Book;

public class TitleSearchStrategy implements SearchStrategy {
    @Override
    public List<Book> search(String keyword, List<Book> books) {
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().contains(keyword)) {
                results.add(book);
            }
        }
        return results;
    }
}
