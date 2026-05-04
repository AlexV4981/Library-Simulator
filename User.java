//Serves as the base class for all system actors, managing shared functionality for borrowing books and handling a temporary selection cart.
//CLASS DEFINITION
import java.util.ArrayList;

public class User {

    //Protected attributes allowing child classes (Librarian, Patron) access to user identity and book collections.
    //INSTANCE VARIABLES
    protected String name;
    protected int ID;
    protected ArrayList<Book> cart;
    protected ArrayList<Book> books;

    //Initializes a user with a name and instantiates empty lists for the cart and currently borrowed books.
    //CONSTRUCTOR
    public User(String name) {
        this.name = name;
        this.cart = new ArrayList<Book>();
        this.books = new ArrayList<Book>();
    }

    //Returns the list of books currently checked out by the user.
    //GET BORROWED BOOKS
    public ArrayList<Book> getBooks() {
        return books;
    }

    //Updates the book's status to unavailable and adds it to the user's personal collection.
    //CHECK OUT PROCESS
    public void checkOut(Book book) {
        if (book != null) {
            book.checkOut();
            books.add(book);
        }
    }

    //Restores the book's availability and removes it from the user's borrowed list.
    //CHECK IN PROCESS
    public void checkIn(Book book, String title) {
        if (book != null) {
            book.checkIn();
            books.remove(book);
        }
    }

    //Provides access to the user's current session cart.
    //GET CART
    public ArrayList<Book> getCart() {
        return cart;
    }

    //Adds a specific book to the user's temporary selection cart.
    //ADD TO CART
    public void addToCart(Book book) {
        cart.add(book);
    }

    //Wipes all entries from the cart, typically used after a successful checkout.
    //CLEAR CART
    public void clearCart() {
        cart.clear();
    }
}