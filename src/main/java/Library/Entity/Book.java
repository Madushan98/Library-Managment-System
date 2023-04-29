package Library.Entity;

public class Book {

    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean availability;

    public Book(String title, String author, String genre) {
        this.id = -1;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = true;
    }

    public Book(int id, String title, String author, String genre, boolean availability) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
