package Library.Entity;

import Utils.IdGenerator;

public class BookFactory {

    public static Book createBook(String title, String author) {
        return new Book(title, author);
    }


}
