package Library.Entity;

import Utils.IdGenerator;

public class BookFactory {

    public static Book createBook(String title, String author, String genre) {
        return new Book(title, author, genre);
    }


}
