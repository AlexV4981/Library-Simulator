import java.util.ArrayList;

public class User {
    protected String name; // [cite: 70]
    protected int ID; // [cite: 70]
    protected ArrayList<Book> books; // 

    public User(String name) { // 
        this.name = name;
        this.books = new ArrayList<Book>();
    }

    public void checkOut(Book book, String title) { // [cite: 73]
        if (book != null) {
            book.checkOut();
            books.add(book);
        }
    }

    public void checkIn(Book book, String title) { // [cite: 74]
        if (book != null) {
            book.checkIn();
            books.remove(book);
        }
    }
}