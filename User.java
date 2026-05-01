import java.util.ArrayList;

public class User {
    protected String name;
    protected int ID;
    protected ArrayList<Book> books;

    public User(String name) {
        this.name = name;
        this.books = new ArrayList<Book>();
    }

    public void checkOut(Book book, String title) {
        if (book != null) {
            book.checkOut();
            books.add(book);
        }
    }

    public void checkIn(Book book, String title) {
        if (book != null) {
            book.checkIn();
            books.remove(book);
        }
    }
}
