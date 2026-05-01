import java.util.ArrayList;

public class User {
    protected String name;
    protected int ID;
    protected ArrayList<Book> cart;
    protected ArrayList<Book> books;

    public User(String name) {
        this.name = name;
        this.cart = new ArrayList<Book>();
        this.books = new ArrayList<Book>();
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void checkOut(Book book) {
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

    public ArrayList<Book> getCart() {
        return cart;
    }

    public void addToCart(Book book) {
        cart.add(book);
    }

    public void clearCart() {
        cart.clear();
    }
}